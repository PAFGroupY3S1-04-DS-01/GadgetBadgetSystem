//IT19176666
//Aththanayake A.B.P.S

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
	
	//create object
	Product objProduct = new Product();
	
	//insert Product 
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
	
	//View product
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct()
	 {
		return objProduct.readProduct(); 
	 } 
	
	
	//Update Product
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(String productData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(productData).getAsJsonObject();
	//Read the values from the JSON object
	 String productID = itemObject.get("productID").getAsString();
	 String productName = itemObject.get("productName").getAsString();
	 String category = itemObject.get("category").getAsString();
	 String description = itemObject.get("description").getAsString();
	 String rID = itemObject.get("rID").getAsString();
	 float unitPrice = Float.parseFloat(itemObject.get("unitPrice").getAsString());

	
	 
	 String output = objProduct.updateProduct(productID, productName, category, description, unitPrice, rID);
	return output;
	}
	
	
	
	//delete product
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String productData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String productID = doc.select("productID").text();
	 String output = objProduct.deleteProduct(productID);
	return output;
	}
}

