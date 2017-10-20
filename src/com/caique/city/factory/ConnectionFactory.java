package com.caique.city.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String ADDRESS = "jdbc:mysql://localhost/city_distance";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(ADDRESS,USER, PASSWORD);

			return cn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
