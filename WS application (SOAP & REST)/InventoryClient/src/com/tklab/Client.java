package com.tklab;

import edu.service.RestClient;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SoapClient.load();
//			RestClient.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
