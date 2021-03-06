//IT19176666
//Aththanayake A.B.P.S


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
		
		
		//insert product 
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
				
				//inserted successfully
				output = "Inserted successfully";
			} catch (Exception e) {
				
				//error
				output = "Error while inserting the Product.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//read product
		public String readProduct() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Product ID</th>" + "<th>Product Name</th>" 
						+ "<th>Category</th>" + "<th>Description</th>" + "<th>Unit Price</th>" + "<th>Research ID</th></tr>";

				String query = "select * from product";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String productID = rs.getString("productID");
					String productName = rs.getString("ProductName");
					String category = rs.getString("category");
					String description = rs.getString("description");
					String unitPrice = Float.toString(rs.getFloat("unitPrice"));
					String rID = rs.getString("rID");

					// Add product details into the html table
					output += "<tr><td>" + productID + "</td>";
					output += "<td>" + productName + "</td>";
					output += "<td>" + category + "</td>";
					output += "<td>" + description + "</td>";
					output += "<td>" + unitPrice + "</td>";
					output += "<td>" + rID + "</td>";


					
				}
				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				
				//error
				output = "Error while reading the product.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		//update product
		public String updateProduct(String productID, String productName, String category, String description,
				float unitPrice, String rID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					//error
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE product SET productName=?, category=?, description=?, unitPrice=?, rID=? WHERE productID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				
				preparedStmt.setString(1, productName);
				preparedStmt.setString(2, category);
				preparedStmt.setString(3, description);
				preparedStmt.setFloat(4, unitPrice);
				preparedStmt.setString(5, rID);
				preparedStmt.setString(6, productID);

				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//updated successfully
				output = "Updated successfully";
			} catch (Exception e) {
				
				//error
				output = "Error while updating the Product details.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
		//delete product
	
		public String deleteProduct(String productID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from product where productID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, (productID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//deleted successfully
				output = "Deleted successfully";
			} catch (Exception e) {
				
				//error
				output = "Error while deleting the product.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
}