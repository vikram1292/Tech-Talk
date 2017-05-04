package com.atmecs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

public class DatabaseConnection 
{
	private static Connection connection;
	
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tech_tonics", "root", "root");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return connection;
	}
	public static void closeConnection()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
