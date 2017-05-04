package com.atmecs.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atmecs.database.DatabaseConnection;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private Connection connection= null;
	
	public void init() throws ServletException 
	{
		connection=DatabaseConnection.getConnection();
		System.out.println(connection);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String email=request.getParameter("email");
		
		try{
			ResultSet rs=
					connection.createStatement().executeQuery("select * from users where EMAIL_ID="+"'"+email+"'");
			
			if(rs.next())
			{
				request.setAttribute("info", "User Already registered.");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			else
			{
				int empid=Integer.parseInt(request.getParameter("empid"));
				String name=request.getParameter("firstname") + " " + request.getParameter("lastname");
				String password=request.getParameter("password");
				String role="TRAINEE";
				
				PreparedStatement preparedStatement=connection.prepareStatement("insert into USERS values(?,?,?,?,?)");
				preparedStatement.setInt(1, empid);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, password);
				preparedStatement.setString(5, role);
				
				if(preparedStatement.executeUpdate()!=0)
				{
					request.setAttribute("info", "You have Successfully Registered! Move to LogIn Page");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
					 
				}
				else
				{
					System.out.println("Some problem in registration");
				}
			}
			
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() 
	{
		 DatabaseConnection.closeConnection();
	}

}
