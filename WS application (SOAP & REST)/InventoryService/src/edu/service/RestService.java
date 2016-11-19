package edu.service;

public interface RestService {

	public InventoryState getProductList();

	public String showUsersCart(String userid);

	public String addProductToCart(String userid, String productName);

	public String buy(String userid);

}
