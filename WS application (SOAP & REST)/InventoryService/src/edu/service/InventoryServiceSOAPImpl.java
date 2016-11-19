package edu.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.*;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.WebService;

@WebService(endpointInterface = "edu.service.InventoryServiceSOAP", targetNamespace = "http://tklab.com")
public class InventoryServiceSOAPImpl implements InventoryServiceSOAP {

	InventoryState state = null;

	@Override
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

	@Override
	public InventoryState buy(InventoryState state, String selectedProduct) {
		Map<String, Product> products = readFromFile();
		Product product = products.get(selectedProduct);
		if (product.getItem1AvailableNumbers() == 0) {
			throw new IllegalStateException();
		}

		// else have to write to the same file back with a reduced number as
		// though it is sold
		try {
			FileWriter writer = new FileWriter("file.txt", false);
			PrintWriter print = new PrintWriter(writer);
			StringBuffer sb = new StringBuffer();
			// write all products and details back.
			for (String productKey : products.keySet()) {
				Product newProduct = products.get(productKey);
				int availableNumber = newProduct.getItem1AvailableNumbers();
				if (selectedProduct.equalsIgnoreCase(newProduct.getItem1Name())) {
					availableNumber--;
				}
				sb.append(newProduct.getItem1Name() + " " + newProduct.getItem1Price() + " " + availableNumber + "\n");
				sb.append(System.lineSeparator());
			}
			print.print(sb.toString());
			print.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, Product> productsAgain = readFromFile();
		state.setProducts(productsAgain);
		return state;
	}

}
