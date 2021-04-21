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

import Model.Research;
import Model.Product;

@Path("/Research")
public class Research_Service {
	 
	Research objResearch = new Research();
	
	@POST
	@Path("/rs")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("rID") String rID,
	 @FormParam("field") String field, 
	 @FormParam("subject") String subject,
	 @FormParam("fundTotal") Float fundTotal,
	 @FormParam("cmpl_stats") String cmpl_stats)
	{
	 String output = objResearch.insertResearch(rID, field, subject, fundTotal,cmpl_stats);
	return output;
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrders()
	 {
		return objResearch.readResearch(); 
	 } 
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String researchData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(researchData).getAsJsonObject();
	//Read the values from the JSON object
	 String rID = itemObject.get("rID").getAsString();
	 String field = itemObject.get("field").getAsString();
	 String subject = itemObject.get("subject").getAsString();
	 float fundTotal = Float.parseFloat(itemObject.get("fundTotal").getAsString());
	 String cmpl_stats = itemObject.get("cmpl_stats").getAsString();
	 

	
	 
	 String output = objResearch.updateResearch(rID, field, subject, fundTotal, cmpl_stats);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String researchData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(researchData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String rID = doc.select("rID").text();
	 String output = objResearch.deleteResearch(rID);
	return output;
	}
	
	
	
}