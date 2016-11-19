package edu.service;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InventoryState {

	public InventoryState() {
		super();
	}

	private Map<String, Product> products;

	public Map<String, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}

}
