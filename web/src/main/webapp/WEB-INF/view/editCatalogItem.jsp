<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//    request.setAttribute("selectedPage","about");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Edit</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>


    <form action="catalog" method="post">
        <h3>Edit Shopping Item Details & Add Stock</h3>
        <div class="mb-2">
            <label for="name">Edit Name</label>
            <input id="itemName" type="text" class="form-control" name="itemName" value="${item.name}"}>
        </div>
        <div class="mb-2">
            <label for="enddate">Edit Cuantity</label>
            <input id="itemQuantity" type="number" class="form-control" name="itemQuantity" value="${item.quantity}">
        </div>
        <div class="mb-2">
            <label for="cardnum">Edit Price</label>
            <input id="itemPrice" type="number" class="form-control" name="itemPrice" value="${item.price}">
        </div>
        
        <h4>Product Id: ${item.id}</h4>
        
        <input type="hidden" name="itemId" value="${item.id}">
        <input type="hidden" name="action" value="editItemFromCatalog">

        <br>
        <button type="submit" class="btn btn-primary">Edit Info</button>
    </form>

</main>




<jsp:include page="footer.jsp" />
