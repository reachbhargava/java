package edu.service;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class RestClient extends JFrame {

	public RestClient() {
		this.setBounds(150, 150, 1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		JLabel label = new JLabel("Product Name");
		JLabel label2 = new JLabel("Price");
		JLabel label3 = new JLabel("Amount Available");

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource("http://localhost:8080/rest/");

		WebResource addService = service.path("shoppingCart/listproducts");
		InventoryState inventoryState = addService.accept(MediaType.APPLICATION_JSON).get(InventoryState.class);
		
		Map<String, Product> products = inventoryState.getProducts();
		Set<String> productNames = products.keySet();
		//System.out.println(str);
		int h = 50;

		//String records[] = str.split("-");
		//for (int i = 0; i < records.length; i++) {
			for (String productName : productNames) {
			//String columns[] = records[i].split("#");
			//String productName = columns[0];
				
			JLabel labelL = new JLabel();
			JLabel labelL2 = new JLabel();
			JLabel labelL3 = new JLabel();

			labelL.setText(productName);
			labelL2.setText(""+products.get(productName).getItem1Price());
			labelL3.setText(""+products.get(productName).getItem1AvailableNumbers());

			h += 50;

			labelL.setBounds(120, h, 150, 35);
			labelL2.setBounds(350, h, 60, 35);
			labelL3.setBounds(400, h, 150, 35);

			this.add(labelL);
			this.add(labelL2);
			this.add(labelL3);

			JButton add = new JButton("Add");
			add.setName(productName);
			add.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseExited(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {

				}

				@Override
				public void mouseClicked(MouseEvent e) {

					ClientConfig config = new DefaultClientConfig();
					Client client = Client.create(config);

					WebResource service = client.resource("http://localhost:8080/rest/");

					WebResource addProductService = service.path("shoppingCart/addProductToCart")
							.path(UserInfo.getUserid() + "/" + e.getComponent().getName());

					String str = addProductService.accept(MediaType.TEXT_PLAIN).get(String.class);

					System.out.println(str);
					JOptionPane.showMessageDialog(null, str, "Wait", JOptionPane.INFORMATION_MESSAGE);

				}
			});

			label.setBounds(120, 50, 150, 35);
			label2.setBounds(350, 50, 60, 35);
			label3.setBounds(400, 50, 150, 35);
			add.setBounds(450, h, 120, 35);

			this.add(label);
			this.add(label2);
			this.add(label3);
			this.add(add);

			this.setVisible(true);

		}

		// to show users cart
		JButton b2 = new JButton("My Shopping Cart");

		b2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				MyShoppingCart c = new MyShoppingCart();
				dispose();

			}
		});
		// button.setBounds(205, 100, 200, 35);
		b2.setBounds(400, 400, 200, 35);

		this.add(b2);

	}

	public static void load() {

		UserInfo.setUserid("user2");

		new RestClient();
	}

}
