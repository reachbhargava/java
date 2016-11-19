package edu.service;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class MyShoppingCart extends JFrame {

	
	public MyShoppingCart() {
		// TODO Auto-generated constructor stub
		
		
		this.setBounds(150, 300, 750, 450);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("RESTful Client");
		JButton main = new JButton("Product List");
		main.setBounds(0, 0, 150, 35);
		this.add(main);
		main.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RestClient show = new RestClient();
				dispose();
				
			}
		});
		
		JButton checkouts = new JButton("Buy");
		checkouts.setBounds(150, 0, 100, 35);
		this.add(checkouts);
		checkouts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ClientConfig config = new DefaultClientConfig();
		        Client client = Client.create(config);
		        WebResource service = client.resource("http://localhost:8080/rest/");
		         
		        WebResource addService = service.path("shoppingCart/checkoutCart").path(UserInfo.getUserid());
		        String msg =  addService.accept(MediaType.TEXT_PLAIN).get(String.class);
				
				System.out.println(msg);
				JOptionPane.showMessageDialog(null, msg, "Status", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				MyShoppingCart k = new MyShoppingCart();
				
				
				
			}
		});
		
		JLabel label = new JLabel("Product Name");
		JLabel label2 = new JLabel("Price");
		JLabel label3 = new JLabel("Quantity");
		
		
		label.setBounds(120, 50, 150, 35);
		label2.setBounds(350, 50, 60, 35);
		label3.setBounds(400, 50, 150, 35);
		
		this.add(label);
		//this.add(label2);
		this.add(label3);
		 HashMap<String, Integer> productsDatabase = 
				new HashMap<String , Integer>();


		 	ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        WebResource service = client.resource("http://localhost:8080/rest/");
	         
	        WebResource addService = service.path("shoppingCart/showCart").path(UserInfo.getUserid());
	        String msg =  addService.accept(MediaType.TEXT_PLAIN).get(String.class);
			System.out.println(msg);
			
			int h = 50;
			if( msg != null && !msg.equals(""))
			{
				String items[] = msg.split("-");
				for (int i = 0; i < items.length; i++) {
					System.out.println(items[i]);
					String columns[] = items[i].split("#");
					
					String productName = columns[0];
					
					JLabel labelL = new JLabel();
					JLabel labelL2 = new JLabel();
					JLabel labelL3 = new JLabel();
					labelL.setText(productName);

					labelL3.setText(columns[1]);
					
					h+=50;
					
					labelL.setBounds(120, h, 150, 35);
					labelL2.setBounds(350, h, 60, 35);
					labelL3.setBounds(400, h, 150, 35);
					
					this.add(labelL);

					this.add(labelL3);
				}

			}
		
	}
	
	
}
