package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.crypto.Data;

public class Research {
	// model class definition
	
	// db method definition
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetsys", "root", "300495Ps@");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertResearch(String rID, String field, String subject, float fundTotal, String cmpl_stats) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into research(`rID`,`field`,`subject`, `fundTotal`, `cmpl_stats`)"+ "values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, rID);
			preparedStmt.setString(2, field);
			preparedStmt.setString(3, subject);
			preparedStmt.setFloat(4, fundTotal);
			preparedStmt.setString(5, cmpl_stats);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
}