package edu.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shoppingCart")
public class RestServiceImpl implements RestService {

	static InventoryState state = null;

	@GET
	@Path("/listproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public InventoryState getProductList() {
		state = loadInventory();
		return state;
	}

	@GET
	@Path("/showCart/{uid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String showUsersCart(@PathParam("uid") String userid) {

		if (User.usersCart.containsKey(userid)) {
			HashMap<String, Integer> items = User.usersCart.get(userid);
			Iterator<String> product = items.keySet().iterator();
			String productsString = "";
			while (product.hasNext()) {
				String productName = product.next();
				productsString += productName + "#" + items.get(productName) + "-";
			}
			return productsString;
		}

		return "";
	}

	@GET
	@Path("/checkoutCart/{uid}")
	@Produces(MediaType.TEXT_PLAIN)
	public synchronized String buy(@PathParam("uid") String userid) {
		String response = "";
		short flag = 0;
		state = loadInventory();
		if (User.usersCart.containsKey(userid)) {

			HashMap<String, Integer> shoppingCartItems = User.usersCart.get(userid);

			Iterator<String> product = shoppingCartItems.keySet().iterator();
			while (product.hasNext()) {
				String item = product.next();
				if ((int) shoppingCartItems.get(item) > (int) state.getProducts().get(item)
						.getItem1AvailableNumbers()) {
					flag = 1;

					response = "Sorry, product you requested is over! :( \n";
					shoppingCartItems.clear();
					return response;
				}
			}

			if (flag != 1) {
				Iterator<String> itt = shoppingCartItems.keySet().iterator();
				while (itt.hasNext()) {
					String productName = itt.next();

					Integer qty = shoppingCartItems.get(productName);

					Product productDetails = state.getProducts().get(productName);
					Integer prevQuantity = (Integer) productDetails.getItem1AvailableNumbers();
					productDetails.setItem1AvailableNumbers((int) prevQuantity - (int) qty);
					Map<String, Product> products = state.getProducts();
					try {
						FileWriter writer = new FileWriter("file.txt", false);
						PrintWriter print = new PrintWriter(writer);
						StringBuffer sb = new StringBuffer();
						// write all products and details back.
						for (String productKey : products.keySet()) {
							Product newProduct = products.get(productKey);
							int availableNumber = newProduct.getItem1AvailableNumbers();
							sb.append(newProduct.getItem1Name() + " " + newProduct.getItem1Price() + " "
									+ availableNumber + "\n");
							sb.append(System.lineSeparator());
						}
						print.print(sb.toString());
						print.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// usersCart.put(emailAddress, new HashMap<String,Integer>());
				User.usersCart.remove(userid);
				response = "Order Success!";
				flag = 2;

			}
		}
		if (flag == 0)
			return "Please add some products to shopping cart !";
		return response;
	}

	@GET
	@Path("/addProductToCart/{uid}/{pid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProductToCart(@PathParam("uid") String userid, @PathParam("pid") String productName) {

		if (User.usersCart.containsKey(userid)) {
			HashMap<String, Integer> items = User.usersCart.get(userid);

			if (items.containsKey(productName)) {
				Integer qty = (int) items.get(productName) + 1;
				items.put(productName, qty);
				User.usersCart.put(userid, items);
			} else {
				HashMap<String, Integer> cartItem = User.usersCart.get(userid);
				cartItem.put(productName, 1);
				User.usersCart.put(userid, cartItem);

			}

		} else {
			HashMap<String, Integer> newItem = new HashMap<String, Integer>();
			newItem.put(productName, 1);
			User.usersCart.put(userid, newItem);
		}
		return "Requested product Added!";
	}

	public InventoryState loadInventory() {
		InventoryState state = new InventoryState();

		Map<String, Product> products = readFromFile();

		state.setProducts(products);

		return state;
	}

	private Map<String, Product> readFromFile() {
		Map<String, Product> products = new TreeMap<String, Product>();

		try {
			for (String line : Files.readAllLines(Paths.get("file.txt"), StandardCharsets.UTF_8)) {
				Product product = new Product();
				String[] details = line.split(" ");
				if (details.length < 3) {
					continue;
				}
				product.setItem1Name(details[0]);
				product.setItem1Price(Integer.valueOf(details[1]));
				product.setItem1AvailableNumbers(Integer.valueOf(details[2]));
				products.put(details[0], product);
			}
		} catch (Exception e) {
			System.out.println("Cant read from file");
			e.printStackTrace();
			Product laptop = new Product("Laptop", 1000, 10);
			Product harddisk = new Product("Harddisk", 200, 5);
			Product mouse = new Product("Mouse", 10, 20);
			products.put("Laptop", laptop);
			products.put("Harddisk", harddisk);
			products.put("Mouse", mouse);
		}

		return products;
	}

}