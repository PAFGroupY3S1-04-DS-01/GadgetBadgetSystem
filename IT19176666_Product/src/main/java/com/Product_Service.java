package com;

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

import Model.Product;
import Model.Research;

@Path("/Product")
public class Product_Service {
	
	Product objProduct = new Product();
	
	@POST
	@Path("/ps")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("productID") String productID,
	 @FormParam("productName") String productName, 
	 @FormParam("category") String category,
	 @FormParam("description") String description,
	 @FormParam("unitPrice") Float unitPrice,
	@FormParam("rID") String rID ) 
	{
	 String output = objProduct.insertProduct(productID, productName, category, description,unitPrice, rID);
	return output;
	}
	

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrders()
	 {
		return objProduct.readProduct(); 
	 } 
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFundAmount(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String rID = itemObject.get("rID").getAsString();
	 double amount = Double.parseDouble(itemObject.get("amount").getAsString());

	 String output = ordObj.updateFundAmount(fundID, amount);
	return output;
	}
	
}

