package com.atmecs.businesslogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atmecs.database.DatabaseConnection;
import com.atmecs.pojo.Event;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private Connection connection= null;
    private ResultSet rs;
    private List<Event>	al = null;
    private int empid;
    private String role = null;
	
	public void init() throws ServletException 
	{
		connection=DatabaseConnection.getConnection();
		System.out.println(connection);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		try
		{
			al = new ArrayList<>();
			
			if(session.getAttribute("empid") != null) 
			{
				empid = Integer.parseInt((String)session.getAttribute("empid"));
				role = (String)session.getAttribute("role");
				
				List<Integer> applied = new ArrayList<Integer>();
				rs=connection.createStatement().
						executeQuery("select * from register_event where USER_LIST="+empid);
				
				while(rs.next())
				{
					applied.add(rs.getInt(1));
				}
				
				PreparedStatement statement=connection.prepareStatement("select * from techtalks");
				rs = statement.executeQuery();
				
				while(rs.next())
				{
					if(!applied.contains(rs.getInt(1))) {
						Event e = new Event();
						e.setEvent_id(rs.getInt(1));
						e.setName(rs.getString(2));
						e.setType(rs.getString(3));
						e.setDescription(rs.getString(4));
						
						e.setPresenter(rs.getString(5));
						al.add(e);
					}
				}
				
				
				if(al==null || al.isEmpty())
					request.setAttribute("info", "No Records Found in DB.");
				else
					request.setAttribute("list", al);

				if(role.equals("TRAINEE"))
					request.getRequestDispatcher("event_user.jsp").forward(request, response);
				else if(role.equals("admin"))
					request.getRequestDispatcher("event_hr.jsp").forward(request, response);
				
			} 
			else 
			{			
				PreparedStatement statement=connection.prepareStatement("select * from techtalks");
				rs = statement.executeQuery();
				
				while(rs.next())
				{
					Event e = new Event();
					e.setEvent_id(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setType(rs.getString(3));
					e.setDescription(rs.getString(4));
					
					e.setPresenter(rs.getString(5));
					al.add(e);
				}	
				
				request.setAttribute("list", al);
				if(al==null ||  al.isEmpty()) 
				{
					request.setAttribute("info", "No Records Found in DB.");
				}
				request.getRequestDispatcher("event.jsp").forward(request, response);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
