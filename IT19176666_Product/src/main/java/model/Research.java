package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Research {
	// model class definition
	
	// db method definition
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadjetbadjetSys", "root", "300495Ps@");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertResearch(String rID, String field, String subject, float fundTotal, date publishedDate, String cmpl_stats) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into research(`rID`,`field`,`subject`, `fundTotal`, `publishedDate`, `cmpl_stats`)"+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, rID);
			preparedStmt.setString(2, field);
			preparedStmt.setString(3, subject);
			preparedStmt.setFloat(4, fundTotal);
			preparedStmt.setDate(2, publishedDate);
			preparedStmt.setString(3, cmpl_stats);
			
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
	
	public String readResearch() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>PaymentID</th><th>OrderID</th>" 
					+ "<th>Payment Date and Time</th>" + "<th>Total Amount</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String payID = Integer.toString(rs.getInt("payID"));
				String orderID = Integer.toString(rs.getInt("orderID"));
				Timestamp datets = rs.getTimestamp("payDateTime");
				String payDate = datets.toString();
				String amount = Float.toString(rs.getFloat("totalAmount"));
				


				// Add into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + orderID + "</td>";
				output += "<td>" + payDate + "</td>";
				output += "<td>" + amount + "</td>";

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
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
}