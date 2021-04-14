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

					// Add into the html table
					output += "<tr><td>" + productID + "</td>";
					output += "<td>" + productName + "</td>";
					output += "<td>" + category + "</td>";
					output += "<td>" + description + "</td>";
					output += "<td>" + unitPrice + "</td>";
					output += "<td>" + rID + "</td>";


					// buttons
					/*output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='itemID' type='hidden' value='" + payID + "'>" + "</form></td></tr>";*/
				}
				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the product.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
		
}