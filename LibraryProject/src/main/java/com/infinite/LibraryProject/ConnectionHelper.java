package com.infinite.LibraryProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHelper {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
		
		String driver = resourceBundle.getString("driver");
		String url = resourceBundle.getString("url");
		String user = resourceBundle.getString("user");
		String password = resourceBundle.getString("password");
		
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
