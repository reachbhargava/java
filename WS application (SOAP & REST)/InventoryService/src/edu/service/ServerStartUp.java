package edu.service;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class ServerStartUp {

	public static void main(String[] args) {
		try {
			Endpoint.publish("http://localhost:8090/ws/inventoryService", new InventoryServiceSOAPImpl());
			HttpServer server = HttpServerFactory.create("http://localhost:8080/rest/");
			server.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}