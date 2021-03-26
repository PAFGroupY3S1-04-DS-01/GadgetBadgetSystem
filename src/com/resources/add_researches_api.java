package com.resources;

import java.sql.Connection;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.service.Research_Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.model.Research;


@Path("/Research")
public class add_researches_api {

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return "Researches";
	}
	
	
	@POST
	@Path("/InsertResearch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppoiment(String researchData){
		JsonObject researchObj = new JsonParser().parse(researchData).getAsJsonObject();
		
		Research resObj = new Research();
		
		 int research_id = researchObj.get("research_Id").getAsInt();
		 String authors = researchObj.get("authors").getAsString();
		 String field_of_study = researchObj.get("field_of_study").getAsString();
		 String key_words = researchObj.get("key_words").getAsString();
		 String description = researchObj.get("description").getAsString();

		
		 Research_Service resService = new Research_Service();
		 
		 resObj.setResearch_id(research_id);
		 resObj.setAuthors(authors);
		 resObj.setField_of_study(field_of_study);
		 resObj.setKey_words(key_words);
		 resObj.setDescription(description);

	
		 String output = resService.insertResearch(resObj);
			return output;

		}
	
	
	

}
