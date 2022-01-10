<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <div class="well well-sm text-center text-capitalize">
        <H1>CATALOG 2022</H1>
    </div>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <div id="products" class="row list-group">
        <c:forEach var="item" items="${availableItems}">
            <div class="item col-xs-4 col-lg-4">
                <div class="thumbnail">
                    <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                    <div class="caption">
                        <div class="row">
                            <div class="col-xs-12 col-md-12 group inner list-group-item-heading" style="text-transform: capitalize">
                                <h1> ${fn:toUpperCase(item.name)}</h1>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <p class="group inner list-group-item-text">
                                    Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                                    sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-6">
                                <p class="lead">${item.price} €</p>
                            </div>
                            <div class="col-xs-12 col-md-6 text-right">
                                <p class="lead">${item.quantity} items</p>
                            </div>

                        </div>
                        <div class="row" style="margin-bottom: 3px">
                            <div class="col-xs-12 col-md-3">
                                <i class="btn btn-success fas fa-cart-plus"></i>
                            </div>
                            <div class="col-xs-12 col-md-3">
                                <i class="btn btn-info fas fa-pen"></i>
                            </div>
                            <div class="col-xs-12 col-md-3">
                                <i class="btn btn-danger fas fa-trash-alt"></i>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="item col-xs-4 col-lg-4">
            <div class="thumbnail">
                <img height="150%" src="./resources/img/addItem.png" data-bs-toggle="modal" data-bs-target="#exampleModal"/>
                <div class="caption text-center ">
                    <h1>Click on this picture to add a new item into</h1>
                    <h1>this catalog 2022.</h1>
                </div>
            </div>
        </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="catalog" method="post">
                <div class="modal-body">
                    <h3>Enter Shopping Item Details</h3>
                    <div class="mb-2">
                        <label for="name">Name2</label>
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
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Add item</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</main>




<jsp:include page="footer.jsp" />
