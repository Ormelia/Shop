<%-- 
    Document   : login
    Created on : May 29, 2019, 12:12:12 PM
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
        <h2>Login</h2>
        <form action="/Shop/membership?action=login" method="post">  
            <label for="email" class="labels">Email</label>  
            <input class="inputs" type="text" name="email" required/>  <br /><br />
            <label for="password" class="labels">Password</label>  
            <input class="inputs" type="text" name="password" required/>  <br /><br />

            <input class="rightButtons" type="submit" name="login" value="Login" /> 
        </form>  
        <br />
        <a href="signup.jsp">New user? Click here to register</a> <br /> <br />
    </body>
</html>