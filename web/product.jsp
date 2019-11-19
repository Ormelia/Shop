<%-- 
    Document   : product
    Created on : May 29, 2019, 12:12:25 PM
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
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:if test="${emailCookie.value == null}">
            <a href="login.jsp">Please log in for access.</a>
        </c:if>
        <c:if test="${emailCookie.value != null}">
            <p>Welcome back ${emailCookie.value} ${user.firstName}</p>
            <a href="/Shop/membership?action=logout" >Logout</a> <br /> <br />

            <h2>Product</h2>

            <form action="productManagement?action=addProduct" method="post">       
                <label for="code" class="labels">Code:</label>  
                <input class="inputs" type="text" name="code" value="${currentProduct.code}" required/>  <br /><br />
                <label for="desc" class="labels">Description:</label>  
                <textarea class="inputs" name="desc" id="desc" required>${currentProduct.description}</textarea>  <br /><br /><br /><br />
                <label for="price" class="labels">Price:</label>  
                <input class="inputs" type="number" step="0.01" name="price" value="${currentProduct.price}" required/>  <br /><br />

                <input class="productsButtons" type="submit" name="update" value="Update Product List" /> 
                <a href="/Shop/productManagement?action=displayProducts"><button type="button" name="viewProducts">View Products</button></a>
            </form>
                
        </c:if>       
       
    </body>
</html>

