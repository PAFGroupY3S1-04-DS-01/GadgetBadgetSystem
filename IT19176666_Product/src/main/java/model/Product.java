package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
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
		
		public String insertProduct(String productID, String productName, String category, String description, Float unitPrice, String rID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into product(`productID`,`productName`,`category`, `description`, `unitPrice`, rID)"+ "values (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				
				preparedStmt.setString(1, productID);
				preparedStmt.setString(2, productName);
				preparedStmt.setString(3, category);	
				preparedStmt.setString(4, description);
				preparedStmt.setFloat(5, unitPrice);
				preparedStmt.setString(6, rID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the Product.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
		
}