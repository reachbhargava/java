package edu.service;

public class Product {

	public Product() {
		super();
	}

	public Product(String item1Name, int item1Price, int item1AvailableNumbers) {
		super();
		this.item1Name = item1Name;
		this.item1Price = item1Price;
		this.item1AvailableNumbers = item1AvailableNumbers;
	}

	private String item1Name = "A";

	private int item1Price = 100;

	private int item1AvailableNumbers = 20;

	public String getItem1Name() {
		return item1Name;
	}

	public void setItem1Name(String item1Name) {
		this.item1Name = item1Name;
	}

	public int getItem1Price() {
		return item1Price;
	}

	public void setItem1Price(int item1Price) {
		this.item1Price = item1Price;
	}

	public int getItem1AvailableNumbers() {
		return item1AvailableNumbers;
	}

	public void setItem1AvailableNumbers(int item1AvailableNumbers) {
		this.item1AvailableNumbers = item1AvailableNumbers;
	}

}
