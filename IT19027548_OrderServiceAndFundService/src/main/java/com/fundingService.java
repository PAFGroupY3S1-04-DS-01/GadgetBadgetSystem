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

import Model.Fund;
import Model.Order;

@Path("/Funds")
public class fundingService {
	
	
/*
 *  To initialize single object from Fund class
 */

	Fund ordObj = new Fund();
	
/*
 *  Manage GET request and retrieve all the Funds details 
 */

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	 {
		return ordObj.readFunds(); 
	 } 
	
/*
 *  Manage GET request and retrieve all the Funds details according the  funderID ID
 */
	
	@GET
	@Path("/{funder}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readFundsFunder(@PathParam("funder") String funder)
	 {
		return ordObj.readFundsFunder(funder); 
	 }
	
	
/*
 *  Manage POST request and insert new Fund
 */
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunds(@FormParam("researchID") String researchID,
	 @FormParam("description") String description,
	 @FormParam("amount") String amount,@FormParam("funder") String funder)
	{
	try {
		
		String output = ordObj.insertFunds(researchID, description,Double.parseDouble(amount),funder);
		return output;
	}catch(NumberFormatException e){
		return "Enter Valid amount";
	}
	
	}
	
/*
 *  Manage PUT request from the client and update the record
 */
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFundAmount(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundID = itemObject.get("fundID").getAsString();
	 double amount = Double.parseDouble(itemObject.get("amount").getAsString());

	 String output = ordObj.updateFundAmount(fundID, amount);
	return output;
	}
	
/*
 *  Manage DELETE request from the client and delete the record
 */
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunds(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String fundID = doc.select("fundID").text();
	 String output = ordObj.deleteFund(fundID);
	return output;
	
	
	}

}
