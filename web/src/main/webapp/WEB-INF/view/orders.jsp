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

    <div>
        <h1>Manage Orders</h1>
        <p>showing ${orderListSize} orders: </p>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Invoice Id</th>
                    <th scope="col">Date of Purchase</th>
                    <th scope="col">Amount Due</th>
                    <th scope="col">See Items</th>
                        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                        <th scope="col">Purchaser Id</th>
                        <th scope="col">Purchaser Name</th>
                        </c:if>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${invoiceList}">
                    <tr>
                        <td>${invoice.id}</td>
                        <td>${invoice.dateOfPurchase}</td>
                        <td>${invoice.amountDue}</td>
                        <td>${invoice.purchasedItems}</td>
                        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                            <td>${invoice.purchaser.id}</td>
                            <td>${invoice.purchaser.firstName}</td>
                        </c:if>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</main>

<jsp:include page="footer.jsp" />