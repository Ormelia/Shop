<%-- 
    Document   : signup
    Created on : May 29, 2019, 12:12:38 PM
    Author     : Meme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <h2>Sign up form</h2>
        
        <form action="/Shop/membership?action=signup" method="post">  
            
            <label for="firstname" class="labels">First Name</label>  
            <input class="inputs" type="text" name="firstname" required/>  <br /><br />
            <label for="lastname" class="labels">Last Name</label>  
            <input class="inputs" type="text" name="lastname" required/>  <br /><br />
            <label for="email" class="labels">Email</label>  
            <input class="inputs" type="email" name="email" required/>  <br /><br />
            <label for="password" class="labels">Password</label>  
            <input class="inputs" type="password" name="password" minlength="8" required/>  <br /><br />
            
            <input class="rightButtons" type="submit" name="signup" value="Sign up"/> 
        </form> 
        
    </body>
</html>

