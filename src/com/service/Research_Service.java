package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConnection;
import com.model.Research;

public class Research_Service {
	
	Connection conn;
	
	
		 
	public Research_Service() {
		
		 conn = DBConnection.connecter();
		
	}
		

	//method to insert data
	public String insertResearch(Research research) {
		  String query = " insert into research(`research_id`,`authors `,`field_of_study`,`key_words`,`description`)"
				  + " values (?, ?, ?, ?, ?)";
		  
	 String output;
		try {	
				PreparedStatement preparedStatement = conn.prepareStatement(query); 
				
				preparedStatement.setInt(1, research.getResearch_id());
				preparedStatement.setString(2,  research.getAuthors());
				preparedStatement.setString(3,  research.getField_of_study());
				preparedStatement.setString(4,  research.getKey_words());
				preparedStatement.setString(5,  research.getDescription());

				preparedStatement.execute();
				 conn.close();
			  output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the Appoiment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
