package edu.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://tklab.com")
@SOAPBinding(style = Style.RPC)
public interface InventoryServiceSOAP {

	@ResponseWrapper(targetNamespace = "http://tklab.com/types", className = "edu.service.InventoryState")
	@WebResult(targetNamespace = "http://tklab.com/types", name = "InventoryState")
	InventoryState loadInventory();

	@WebMethod
	InventoryState buy(InventoryState state, String selectedProduct);
}
