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
    <H1>Catalog</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <H1>Available Items</H1>
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th></th>
        </tr>

        <c:forEach var="item" items="${availableItems}">

            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.id}</td>
                <td></td>
                <td>
                    <!-- post avoids url encoded parameters -->
                    <form action="./catalog" method="get">
                        <input type="hidden" name="itemId" value="${item.id}">
                        <input type="hidden" name="action" value="removeItemFromCatalog">
                        <button type="submit" >Remove Item</button>
                    </form> <br>
                    <form action="./catalog" method="get">
                        <input type="hidden" name="itemId" value="${item.id}">
                        <input type="hidden" name="action" value="showEditItemFromCatalog">
                        <button type="submit" >Edit Item/Add stock</button>
                    </form> 
                </td>
            </tr>

        </c:forEach>
    </table>
    <H1>Add Product</H1>
    <form action="catalog" method="post">
        <h3>Enter Shopping Item Details</h3>
        <div class="mb-2">
            <label for="name">Name</label>
            <input id="itemName" type="text" class="form-control" name="itemName" >
        </div>
        <div class="mb-2">
            <label for="enddate">Cuantity</label>
            <input id="itemQuantity" type="number" class="form-control" name="itemQuantity">
        </div>
        <div class="mb-2">
            <label for="cardnum">Price</label>
            <input id="itemPrice" type="number" class="form-control" name="itemPrice">
        </div>
        <input type="hidden" name="action" value="addItemToCatalog">

        <br>
        <button type="submit" class="btn btn-primary">Add item</button>
    </form>

</main>




<jsp:include page="footer.jsp" />
