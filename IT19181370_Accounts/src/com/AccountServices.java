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

import Model.Account;


@Path("/Account")

public class AccountServices {

	Account accObj = new Account();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	 {
		return accObj.readUser(); 
	 } 
	
	
	@GET
	@Path("/{UserType}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readUserType(@PathParam("UserType") String UserType)
	 {
		return accObj.readUserType(UserType); 
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("UserID") String UserID,
	 @FormParam("Name") String Name,
	 @FormParam("Email") String Email,
	 @FormParam("Password") String Password,
	 @FormParam("Address") String Address,
	 @FormParam("Mobile") int Mobile,
	 @FormParam("Status") String Status,
	 @FormParam("UserType") String UserType)

	{
	 String output = accObj.insertUser(Name,Email,Password,Address,Mobile,Status,UserType);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String UserID)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(UserID).getAsJsonObject();
	//Read the values from the JSON object
	 String Name = userObject.get("Name").getAsString();
	 String Email = userObject.get("Email").getAsString();
	 String Address = userObject.get("Address").getAsString();
	 String Mobile = userObject.get("Mobile").getAsString();
	 String UserType = userObject.get("UserType").getAsString();

	 String output = accObj.updateUser(Name,Email,Address,Mobile,UserType);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String UserID)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserID, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String UserId = doc.select("UserID").text();
	 String output = accObj.deleteUser(UserId);
	return output;
	}
	
}
