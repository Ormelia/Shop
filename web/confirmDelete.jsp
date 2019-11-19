<%-- 
    Document   : confirmDelete
    Created on : May 29, 2019, 12:09:53 PM
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
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:if test="${emailCookie.value == null}">
            <a href="login.jsp">Please log in for access.</a>
        </c:if>
        <c:if test="${emailCookie.value != null}">
            <p>Welcome back ${emailCookie.value} ${user.firstName}</p>
            <a href="/Shop/membership?action=logout">Logout</a> <br /> <br /> 
            
            <h2>Are you sure you want to delete this product?</h2>


            <label for="code" class="labels"> Code: </label>
            <i name="code"><c:out value='${currentProduct["code"]}'/></i><br />
            <br/>
            <label for="desc" class="labels">Description:</label>  
            <i name="desc"><c:out value='${currentProduct["description"]}'/></i><br/>
            <br/>
            <label for="price" class="labels">Price:</label>  
            <i name="price"><c:out value='${currentProduct["price"]}'/></i></i>
        <br /><br />

        <a href="/Shop/productManagement?action=delete"><button type="button" name="yesDelete">Yes</button></a>
        <a href="/Shop/productManagement?action=displayProducts"><button type="button" name="noDelete">No</button></a>

    </c:if>
</body>
</html>
