package com.tklab;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.soap.SOAPFaultException;

import com.tklab.InventoryState.Products.Entry;

public class SoapClient extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JButton b1;
	static String selectedItem;

	public SoapClient() {
		setPreferredSize(new Dimension(600, 300));
	}

	@WebServiceRef(wsdlLocation = "http://localhost:8090/ws/inventoryService?wsdl")
	static InventoryServiceSOAPImplService service;

	static InventoryServiceSOAP port;
	static InventoryState response;
	static JFrame frame;

	public static void load() {
		service = new InventoryServiceSOAPImplService();
		port = service.getInventoryServiceSOAPImplPort();
		response = port.loadInventory();
		SoapClient client = new SoapClient();
		JScrollPane scrollPane;

		scrollPane = setupTable(response);

		b1 = new JButton("<html></br><center><b>Buy</b><br>");
		b1.setActionCommand("buy");
		b1.addActionListener(client);

		client.add(scrollPane);
		client.add(b1);

		frame = new JFrame("Buy products. ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(client);
		frame.pack();
		frame.setVisible(true);
	}

	private static JScrollPane setupTable(InventoryState response) {
		JScrollPane scrollPane;
		List<InventoryState.Products.Entry> list = response.getProducts().entry;
		int numberOfItems = list.size();
		final Object[][] dataArray = new Object[numberOfItems][3];
		int currentRow = 0;
		Iterator<Entry> listIt = list.iterator();
		while (listIt.hasNext()) {
			Entry nextItem = listIt.next();
			Product value = nextItem.getValue();
			dataArray[currentRow][0] = value.getItem1Name();
			dataArray[currentRow][1] = value.getItem1Price();
			dataArray[currentRow][2] = value.getItem1AvailableNumbers();
			System.out.println("value.getItem1Name()" + value.getItem1Name() + "AvailableNumbers "
					+ value.getItem1AvailableNumbers());
			currentRow++;
		}

		String[] columnNames = { "Product ", "Unit Price", "Available Numbers" };
		JTable table = new JTable(dataArray, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (lsm.isSelectionEmpty()) {
					System.out.println("No rows are selected.");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					selectedItem = (String) dataArray[selectedRow][0];
				}
			}
		});
		return scrollPane;
	}

	public void actionPerformed(ActionEvent e) {
		SoapClient client = new SoapClient();
		JScrollPane scrollPane = null;
		try {
			if ("buy".equals(e.getActionCommand())) {
				response = port.buy(response, selectedItem);
			} else if ("back".equals(e.getActionCommand())) {
				response = port.loadInventory();
			}
			scrollPane = setupTable(response);
			client.add(scrollPane);

			b1 = new JButton("<html></br><center><b>Buy</b><br>");
			b1.setActionCommand("buy");
		} catch (SOAPFaultException exception) {
			JLabel label1 = new JLabel("No buy");
			label1.setText("Selected product unavailable! Please try another product :) ");
			client.add(label1);

			b1 = new JButton("<html></br><center><b>Go Back</b><br>");
			b1.setActionCommand("back");
		}

		b1.addActionListener(client);
		client.add(b1);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(client);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		/***
		 * Seriously do not know what the below line does, but if I remove this,
		 * things go for a toss and after the buy, the panel is not updated.
		 */
		setFont(new Font("Arial", Font.BOLD, 35));
	}

}
