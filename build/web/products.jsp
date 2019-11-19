<%-- 
    Document   : products
    Created on : May 29, 2019, 12:13:05 PM
    Author     : Meme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:if test="${emailCookie.value == null}">
            <a href="login.jsp">Please log in for access.</a>
        </c:if>
        <c:if test="${emailCookie.value != null}">
            <p>Welcome back ${emailCookie.value} ${sessionScope.user.firstName}</p>
            <a href="/Shop/membership?action=logout" >Logout</a> <br /> <br />
            
            <h2>Products</h2>
            <div> 
                <table >
                    <tr>
                        <th>Code</th>
                        <th>Description</th> 
                        <th>Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <c:forEach var="item" items="${products}">
                        <tr>
                            <td>${item.code}</td>
                            <td>${item.description}</td> 
                            <td>${item.priceCurrencyFormat}</td>


                            <td><a href="/Shop/productManagement?action=edit&newCode=${item.code}">Edit</a></td>
                            <td><a href="/Shop/productManagement?action=deleteProduct&newCode=${item.code}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br />
                <!--<form action = "/Shop/product.jsp">
                    <input type="submit" name="addProduct" value="Add Product"/> 
                </form>-->
                <a href="/Shop/productManagement?action=addProduct"><button type="button" name="addProduct">Add Product</button></a>


            </div>
        </c:if>
    </body>
</html>

