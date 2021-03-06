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

import model.User;

@Path("/User")
public class UserService {
	
	User userObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	 {
		return userObj.readUser(); 
	 } 
	
	@GET
	@Path("/{UserType}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readUserType(@PathParam("UserType") String UserType)
	 {
		return userObj.readUserType(UserType); 
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
	 @FormParam("UserType") String UserType)

	{
	 String output = userObj.insertUser(UserID,Name,Email,Password,Address,Mobile,UserType);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String UserData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(UserData).getAsJsonObject();
	//Read the values from the JSON object
	 String userID = userObject.get("UserID").getAsString();
	 String Name = userObject.get("Name").getAsString();
	 String Email = userObject.get("Email").getAsString();
	 String Address = userObject.get("Address").getAsString();
	 int Mobile = Integer.parseInt(userObject.get("Mobile").getAsString());
	 String UserType = userObject.get("UserType").getAsString();

	 String output = userObj.updateUser(userID,Name,Email,Address,Mobile,UserType);
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
	 String output = userObj.deleteUser(UserId);
	return output;
	}
	
	@POST
	@Path("/logging_in")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String loginUser(@FormParam("Email") String  Email,  @FormParam("Password") String Password)
	 {
				String Output = userObj.loginUser(Email,Password); 
				return Output;
	 }
	
	@PUT
	@Path("/Approve")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String approveResearch(String rID)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(rID).getAsJsonObject();
	//Read the values from the JSON object
	 String rID1 = userObject.get("rID").getAsString();

	 String rd2="r0003";
	String output = userObj.approveResearch(rd2);
	return output;
	}
}
