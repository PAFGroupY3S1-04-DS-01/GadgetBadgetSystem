//IT19176666
//Aththanayake A.B.P.S



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
	
	//insert research
	public String insertResearch(String rID, String field, String subject, float fundTotal, String cmpl_stats, String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into research(`rID`,`field`,`subject`, `fundTotal`, `cmpl_stats`, `approval`)"+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			
			preparedStmt.setString(1, rID);
			preparedStmt.setString(2, field);
			preparedStmt.setString(3, subject);
			preparedStmt.setFloat(4, fundTotal);
			preparedStmt.setString(5, cmpl_stats);
			preparedStmt.setString(6, approval);
			
			// execute 
			preparedStmt.execute();
			con.close();
			
			//inserted successfully
			output = "Inserted successfully";
		} catch (Exception e) {
			
			//error
			output = "Error while inserting the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//read research
	public String readResearch() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Research ID</th>" + "<th>Field</th>" 
					+ "<th>Subject</th>" + "<th>Fund Total</th>" + "<th>Published Date</th>" + "<th>Complete Status</th>" + "<th>Approval</th></tr>";

			String query = "select * from research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String rID = rs.getString("rID");
				String field = rs.getString("field");
				String subject = rs.getString("subject");
				String fundTotal = Float.toString(rs.getFloat("fundTotal"));
				String publishedDate = rs.getString("publishedDate");
				String cmpl_stats = rs.getString("cmpl_stats");
				String approval = rs.getString("approval");

				// Add research details into  the html table
				output += "<tr><td>" + rID + "</td>";
				output += "<td>" + field + "</td>";
				output += "<td>" + subject + "</td>";
				output += "<td>" + fundTotal + "</td>";
				output += "<td>" + publishedDate + "</td>";
				output += "<td>" + cmpl_stats + "</td>";
				output += "<td>" + approval + "</td>";


				
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			
			//error
			output = "Error while reading the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update research
	public String updateResearch(String rID, String field, String subject, float fundTotal, String cmpl_stats, String approval) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE research SET  field=?, subject=?, fundTotal=?, cmpl_stats=?, approval=? WHERE rID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, field);
			preparedStmt.setString(2, subject);
			preparedStmt.setFloat(3, fundTotal);
			preparedStmt.setString(4, cmpl_stats);
			preparedStmt.setString(5, approval);
			preparedStmt.setString(6, rID);
			

			// execute
			preparedStmt.execute();
			con.close();
			
			//updated successfully
			output = "Updated successfully";
		} catch (Exception e) {
			
			//error
			output = "Error while updating the Research details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//delete research
	public String deleteResearch(String rID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from research where rID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, rID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//delete successfully message
			output = "Deleted successfully";
		} catch (Exception e) {
			
			//error
			output = "Error while deleting the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}


	
}