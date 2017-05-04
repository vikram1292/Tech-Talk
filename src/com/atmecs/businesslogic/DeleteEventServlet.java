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

@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private Connection connection= null;
	
	public void init() throws ServletException 
	{
		connection=DatabaseConnection.getConnection();
		System.out.println(connection);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		try{				
			PreparedStatement preparedStatement=
					connection.prepareStatement("delete from techtalks where EVENT_ID=?");
			
			preparedStatement.setInt(1, id);
			if(preparedStatement.executeUpdate()!=0)
			{
				request.setAttribute("info", "You have Deleted Event Successfully.");	
				request.getRequestDispatcher("EventServlet").forward(request, response);			
			}
			else
			{
				System.out.println("Some problem in deletion of event.");
			}
		
	} catch (SQLException e)
		{			
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
