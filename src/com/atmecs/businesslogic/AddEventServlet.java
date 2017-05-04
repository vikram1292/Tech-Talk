package com.atmecs.businesslogic;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atmecs.database.DatabaseConnection;

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private Connection connection= null;
	
	public void init() throws ServletException 
	{
		connection=DatabaseConnection.getConnection();
		System.out.println(connection);
		System.out.println("added1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String name=request.getParameter("name"); 
		String type=request.getParameter("type"); 
		String description=request.getParameter("description"); 
		 
		String presenter=request.getParameter("presenter"); 
		System.out.println("added2");
		try
		{				
				PreparedStatement preparedStatement=
				connection.prepareStatement
				("insert into techtalks(NAME, TYPE, DESCRIPTION, PRESENTER) values(?,?,?,?)");
				
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, type);
				preparedStatement.setString(3, description);
				
				preparedStatement.setString(7, presenter);
				
				if(preparedStatement.executeUpdate()!=0)
				{
					request.setAttribute("info", "New Event Successfully Added");	
					System.out.println("added");
				}
				else
				{
					System.out.println("Unable to Update Data");
					request.setAttribute("info", "Unable to Update Data");
				}
				
				request.getRequestDispatcher("EventServlet").forward(request, response);
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}

}
