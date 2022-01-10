<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Chekout</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <H1>Shopping cart</H1>
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <c:forEach var="item" items="${shoppingCartItems}">

            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>TOTAL</td>
            <td>${shoppingcartTotal}</td>
        </tr>
    </table>

    <div class="row">
        <div class="col">

            
        </div>
        <hr>
        <div class="col">


            <form action="checkout" method="post">
                <H1>Payment Details</H1>
                <h3>Enter Your Card Details</h3>
                <div class="mb-2">
                    <label for="name">Name</label>
                    <input id="name" type="text" class="form-control" name="name1" value="">
                </div>
                <div class="mb-2">
                    <label for="enddate">End Date</label>
                    <input id="enddate" type="text" class="form-control" name="endDate1" value="">
                </div>
                <div class="mb-2">
                    <label for="cardnum">Card Number</label>
                    <input id="cardnum" type="text" class="form-control" name="cardnumber1" value="">
                </div>
                <div class="mb-2">
                    <label for="cvv">CVV</label>
                    <input id="cvv" type="text" class="form-control" name="cvv1" value="">
                </div>
                <div class="mb-2">
                    <label for="issuenum">Issuer Number</label>
                    <input id="issuenum" type="text" class="form-control" name="issueNumber1" value="">
                </div>
                
                <button type="submit" class="btn btn-primary">Pay ${shoppingcartTotal}</button>
            </form>
        </div>
    </div>


</main>
<jsp:include page="footer.jsp" />
