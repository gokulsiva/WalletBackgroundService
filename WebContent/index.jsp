
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    
    	String email = (String) request.getParameter("email");
    	String message = (String) request.getParameter("message");
    	if(email == null){
    		email = "Enter User Email";
    	}
    	if(message == null){
    		message = "";
    	}
    
    %>
    
    
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  
  
  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
<div align="center" style="width: 100%; color: red; font-size: 20px;"><%= message %></div>

<form action="LogIn" method="post">
  <h4> Login Information </h4>
  <input class="name" name="email" type="text" placeholder="<%= email %>"/>
  <input class="pw" name="password" type="password" placeholder="Enter Password"/>
  <li><a href="userForm.jsp">Need to SignUp?</a></li>
  <input class="button" type="submit" value="Log in"/>
</form>
  
  
</body>
</html>