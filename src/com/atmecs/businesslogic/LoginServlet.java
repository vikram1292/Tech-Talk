package com.atmecs.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atmecs.database.DatabaseConnection;

@WebServlet("/signin")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private static Connection connection;   
    
	
	public void init() throws ServletException {
		connection=DatabaseConnection.getConnection();
		System.out.println(connection);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		try
		{
			PreparedStatement statement=
					connection.prepareStatement("select * from users where EMAIL_ID=? and PASSWORD=?");
			
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next())
			{
				String role = resultSet.getString(5);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("empid", resultSet.getString(1));
				httpSession.setAttribute("email", email);
				httpSession.setAttribute("role", role);
				
				if(role.equals("employee"))//changes role_user
					request.getRequestDispatcher("home_user.jsp").forward(request, response);
				
				else if(role.equals("admin"))
					request.getRequestDispatcher("home_hr.jsp").forward(request, response);
			}
			else {
				request.setAttribute("info", "Invalid Username or Password.");
				request.getRequestDispatcher("signin.jsp").forward(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
 
}
