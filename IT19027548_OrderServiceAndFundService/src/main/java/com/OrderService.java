package com; // IT19027548 Maduwantha W.W.A.K.

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Model.Order;

@Path("/Order")
public class OrderService {
	

/*
 *  To initialize single object from Order class
 */
	Order ordObj = new Order();

/*
 *  Manage GET request and retrieve all the order details 
 */
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrders()
	 {
		return ordObj.readOrders(); 
	 } 
	
	
/*
 *  Manage GET request and retrieve all the order details according the  buyer ID
 */
	
	
	@GET
	@Path("/{buyerID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readOredrsBuyer(@PathParam("buyerID") String buyerID)
	 {
		return ordObj.readOrdersbuyer(buyerID); 
	 }
	
/*
 *  Manage POST request and insert new Order
 */
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrder(@FormParam("buyerID") String buyerID,
	 @FormParam("productID") String productID,
	 @FormParam("qty") int qty)
	{
	 String output = ordObj.insertOrder(buyerID, productID, qty);
	return output;
	}
	
/*
 *  Manage PUT request from the client and update the record
 */
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String orderID = itemObject.get("orderID").getAsString();
	 int qty = Integer.parseInt(itemObject.get("qty").getAsString());

	 String output = ordObj.updateOrderQuantity(orderID, qty);
	return output;
	}
	
/*
 *  Manage DELETE request from the client and DELETE the record
 */
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String orderID = doc.select("orderID").text();
	 String output = ordObj.deleteOrders(orderID);
	return output;
	}
}
