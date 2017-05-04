<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>Welcome to Events</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/component.css">
    <link rel="stylesheet" href="css/custom-styles.css">
    <link rel="stylesheet" href="css/font-awesome.css">
	
     
	 <link rel="stylesheet" href="css/demo.css">
    <link rel="stylesheet" href="css/font-awesome-ie7.css">

      <script src="js/jquery.mobilemenu.js"></script>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script>
    $(document).ready(function(){
        $('.menu').mobileMenu();
    });
  </script>
 
  </head>

  <body><br><br>
  				<jsp:include page="nav_user.jsp"/>
  		
  			
  				<h4 class="success" style="text-align:center;width:100%">${info}</h4>
  		
  	
  			<div class="container">
	  				<!-- <button class="btn btn-primary pull-right" type="button" onclick="window.location.href='addEvent.jsp'">Add Event</button> -->
	  				<br/><br/><h3 style="font-weight: bold">Welcome,<br>
  					<%=session.getAttribute("email") %>
	  				</h3></br>
	  				<table class="table table-striped">
					    <thead>
					      <tr style="font-weight: bold">
					        <th class="success" >Event Name</th>
					        <th class="success">Type</th>
					        <th class="success">Description</th>
					        
					        <th class="success">Presenter</th>
					        <th class="success"></th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${list}" var="e">	
					      <tr style="font-weight: bold">
					        <td><strong>${e.name}</strong></td>
					        <td>${e.type}</td>
					        <td>${e.description}</td>
					      					        <td>${e.presenter}</td>
					        <td><p><a class="btn btn-primary" href="EventApplyServlet?id=${e.event_id}" role="button">Apply</a></p></td>
					   
					     </tr>
					    </c:forEach>
					   </tbody>
	  				</table>
  				</div>
  	
  	
  	
    
    
  </body>
</html>
