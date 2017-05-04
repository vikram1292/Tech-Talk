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
import javax.servlet.http.HttpSession;

import com.atmecs.database.DatabaseConnection;

@WebServlet("/EventApplyServlet")
public class EventApplyServlet extends HttpServlet 
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
		HttpSession session = request.getSession();
		int empid = Integer.parseInt((String)session.getAttribute("empid"));
		
		try
		{				
				PreparedStatement preparedStatement=
						connection.prepareStatement("insert into register_event values(?,?)");
				
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, empid);
				
				if(preparedStatement.executeUpdate()!=0)
				{
					request.setAttribute("info", "You have Successfully Applied for the Event.");	
					request.getRequestDispatcher("EventServlet").forward(request, response);
				}
				else
				{
					System.out.println("Cant Apply for the Event..");
				}
			
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}
}
