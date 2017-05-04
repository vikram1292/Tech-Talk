<%@ page import="java.util.ArrayList"%>
<%@ page import="com.atmecs.pojo.Event"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>Events</title>

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
  				<div class="menu">
				      <div class="navbar">
				        <div class="container">
				          <div class="navbar-header">
				            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				              <i class="fw-icon-th-list"></i>
				            </button>
				          </div>
				          <div class="navbar-collapse collapse">
						<img style="float:left;margin-right:10px" src="img/logo.jpg" alt="" height="50" width="150">
				            <ul class="nav navbar-nav">
								<jsp:include page="nav.jsp"/>
				              <li><a href="signin.jsp">Sign In</a></li>
				              <li><a href="signup.jsp">Sign Up</a></li>
				            </ul>
				          </div><!--/.navbar-collapse -->
				        </div>
				      </div>
				      </div>
  		
      			
      			<div class="container">
      	
      				<%-- <h4 class="success" style="text-align:center;width:100%">${info}</h4> --%>
	  				<h3 style="font-weight:bold;text-align:center;width:100%">All upcoming Tech Talk!!</h3>
	  				<br/><br/><br/>
	  				<table class="table table-striped">
					    <thead>
					      <tr>
					        <th class="success">Event Name</th>
					        <th class="success">Type</th>
					        <th class="success">Description</th>
					        
					        <th class="success">Presenter</th>
					        <th class="success"></th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${list}" var="e">	
					      <tr style="font-weight: bold">
					        <td>${e.name}</td>
					        <td>${e.type}</td>
					        <td>${e.description}</td>
					       
					        <td>${e.presenter}</td>		   
					     </tr>
					    </c:forEach>
					   </tbody>
	  				</table>
  				</div>
  				
  				
</div>
    
    
  </body>
</html>
