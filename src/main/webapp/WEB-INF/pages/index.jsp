<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="padding-top: 50px;">

    <p class="well">
        It's simple web application based on Spring Web Framework + JDBC +
        Twitter Bootstrap styles intended for pizzeria and can be useful
        for people who like to create own pizzas. Enjoy:)
    </p>

    <c:if test="${showResult == 'success'}">
        <div class="alert alert-dismissable alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Well done!</strong> You successfully send your order. Wait for courier.
        </div>
    </c:if>

    <div>
        <h4>Find your order and check status</h4>

        <form method="GET" action="<c:url value='order/searchOrder'/>">
                <form>
                    <div class="input-group text-center">
                        <input type="text" class="form-control" placeholder="Search order"
                               class="inp" name="phone" >
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default" type="button">Search</button>
                        </span>
                    </div>
                </form>
        </form>
    </div>
</div>
              