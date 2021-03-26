package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	//A common method to connect to the DB  
		public static  Connection connecter() {
			Connection con = null;

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost/gadjetbadjet?useTimezone=true&serverTimezone=UTC",
							"root", "");
					// For testing
					System.out.print("Successfully connected");
				} catch (Exception e) {
					System.out.print("connection fail");
					e.printStackTrace();
					System.out.print(e);
				}

				return con;
			}

}
