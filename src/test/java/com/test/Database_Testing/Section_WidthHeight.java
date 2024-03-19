package com.test.Database_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.testng.annotations.Test;

public class Section_WidthHeight {
	
	@Test
	public void testDB() throws ClassNotFoundException, SQLException {
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("Driver loaded");

	    String url = "jdbc:mysql://apollo2.humanbrain.in:3306/HBA_V2";
	    String username = "root";
	    String password = "Health#123";
	    Connection connection = DriverManager.getConnection(url, username, password);
	    System.out.println("MYSQL database connect");
	    
	    executeAndPrintQuery(connection);
	    connection.close();
	}

	private void executeAndPrintQuery(Connection connection) throws SQLException {
	    Statement statement = connection.createStatement();
	    String query = "SELECT * \r\n"
	    		+ "FROM HBA_V2.section \r\n"
	    		+ "ORDER BY width DESC, height DESC\r\n"
	    		+ "LIMIT 20;";

	    ResultSet resultSet = statement.executeQuery(query);
	    
	    int userIdWidth = 10;
	    int userNameWidth = 20;
	    int actionWidth = 15;
	    int infoWidth = 15;
	    int timestampWidth = 25;

	    // Printing header
	    System.out.printf("%-" + userIdWidth + "s %-"+ userNameWidth + "s %-"+ actionWidth + "s %-"+ infoWidth + "s %-" + timestampWidth + "s%n",
	            "User_Id", "User_name", "Action", "Info", "Timestamp");

	    // Printing separator line
	    String separatorLine = "-".repeat(userIdWidth + userNameWidth + actionWidth + infoWidth + timestampWidth);
	    System.out.println(separatorLine);
	    
	    while (resultSet.next()) {
	        Integer user_id = resultSet.getInt("user");
	        Timestamp timestamp = resultSet.getTimestamp("timestamp");
	        String action = resultSet.getString("action");
	        String info = resultSet.getString("info");
	        String user_name = resultSet.getString("user_name");
	        System.out.printf("%-" + userIdWidth + "d %-" + userNameWidth + "s %-" + actionWidth + "s %-" + infoWidth + "s %-" + timestampWidth + "s%n",
	                user_id, user_name, action, info, timestamp);
	    }

	    // Close the statement
	    resultSet.close();
	    statement.close();
	}

}
