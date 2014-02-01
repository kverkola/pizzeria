<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <form method="get" action="order/searchOrder">
            <form class="form-search">
            <input type="text" class="form-control col-lg-8" placeholder="Search" type="text" class="inp" name="orderId" >
            <button type="submit" class="btn btn-sm btn-default">Search order</button>
            </form>
    </form>

    <c:if test="${showResult == 'success'}">
        <div class="alert alert-dismissable alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Well done!</strong> You successfully send your order. Wait for courier.
        </div>
    </c:if>
</div>
              