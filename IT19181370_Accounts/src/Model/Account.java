package Model;

import java.sql.*; 

public class Account {

	//A common method to connect to the DBprivate 
	Connection connect() { 
		Connection con = null; 
		try{   Class.forName("com.mysql.jdbc.Driver"); 
		//Provide the correct details: DBServer/DBName, username, password 
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/account", "root", "jinimaya97");
		} catch (Exception e) {
			e.printStackTrace();} 
		return con; } 

	//insert
	public String insertUser(String UserID, String Name, String Email,String Password, String Address, int Mobile, String Status, String UserType) {
		String output = ""; 
		try{ Connection con = connect();
			if (con == null) 
				{return"Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into items  (`UserID`,`Name`,`Email`,`Password`,'Address','Mobile','Status','UserType')"+ " values (?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, UserID);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Password);
			preparedStmt.setString(5, Address);
			preparedStmt.setInt(6, Mobile);
			preparedStmt.setString(7, Status);
			preparedStmt.setString(8, UserType);

			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
		output = "Inserted successfully"; }
		catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage()); } 
		return output; } 
	
	//read
	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Order ID</th><th>Buyer ID</th>" + "<th>Product ID</th>"
					+ "<th>Quantity</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from account.user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String UserID = rs.getString("UserID");
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				//String Password = rs.getString("Password");
				String Address  = rs.getString("Address");
				String Mobile = Integer.toString(rs.getInt("Mobile"));
				String Status = rs.getString("Status");
				String UserType = rs.getString("UserType");
				

				// Add into the html table
				output += "<tr><td>" + UserID + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Email + "</td>";
				//output += "<td>" + Password + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Mobile + "</td>";
				output += "<td>" + Status + "</td>";
				output += "<td>" + UserType + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='UserID' type='hidden' value='" + UserID + "'>" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the user details";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//find details of a certail user type
	public String readUserType(String UserType) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Order ID</th><th>Buyer ID</th>" + "<th>Product ID</th>"
					+ "<th>Quantity</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from account.user where UserType =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(8, UserType);
			ResultSet rs = preparedStmt.executeQuery();

			// iterate through the rows in the result set
			while (rs.next()) {
				String UserID = rs.getString("UserID");
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				//String Password = rs.getString("Password");
				String Address  = rs.getString("Address");
				String Mobile = Integer.toString(rs.getInt("Mobile"));
				String Status = rs.getString("Status");
				
				

				// Add into the html table
				output += "<tr><td>" + UserID + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Email + "</td>";
				//output += "<td>" + Password + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Mobile + "</td>";
				output += "<td>" + Status + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='userID' type='hidden' value='" + UserID + "'>" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the user details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//update user info
	public String updateUserDetails(String UserID, String Name, String Email, String Address, String Mobile, String UserType) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE account.user SET Name=?, Email=?,Address=?,Mobile = ?, UserType = ? WHERE UserID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(5, Address);
			preparedStmt.setInt(6, Integer.parseInt(Mobile));
			preparedStmt.setString(8, UserType);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating user details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete
	public String deleteUser(String UserID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from account.user where UserID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, UserID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user details";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
	

