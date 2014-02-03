<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h4>Your order</h4>

<hr style="border-color: #68a9ff;">

<table class="table">
    <tr>
        <th style="width: 65%; text-align: left;">Name</th>
        <th>Count</th>
        <th>Price, $</th>
        <th></th>
    </tr>

    <!-- Pizzas in order begin -->

    <c:set var="pizzasInOrder" value="pizzasInOrder"/>
    <c:forEach var="pizza" items="${sessionScope[pizzasInOrder]}" varStatus="status">
        <tr>
            <td style="text-align: left;">
                    ${pizza.name}
                <p>
                    <span class="label label-info">contains:</span>
                    <c:forEach var="ingredient" items="${pizza.map}">
                        ${ingredient.key.name} - <span class="badge">${ingredient.value}</span>&nbsp;
                    </c:forEach>
                </p>
            </td>
            <td>
                <div>
                    <form id="pizzas + ${status.count}" method="POST"
                          action="<c:url value='/order/change-product-count'/>"
                          style="display: inline-block; text-align: center">
                        <input name="productId" type="hidden" value="${pizza.productId}"/>
                        <input name="value" type="text" class="form-control input-sm" value="${pizza.quantity}"
                               style="width: 45px; text-align: center;"/>
                    </form>
                    <a style="cursor: pointer;"
                       onclick="document.getElementById('pizzas + ${status.count}').submit(); return false;">
                        <img src="<c:url value='/resources/button_ref.png'/>"/>
                    </a>
                </div>
            </td>
            <td style="font-size: 22;">
                    ${pizza.price * pizza.quantity}
            </td>
            <td>
                <a href="<c:url value='/order/remove-product/${pizza.productId}'/>">
                    <img src="<c:url value='/resources/del.png'/>"/>
                </a>
            </td>
        </tr>
    </c:forEach>

    <!-- Pizzas in order end -->

    <!-- Additions in order begin -->

    <c:set var="additionalInOrder" value="additionalInOrder"/>
    <c:forEach var="addition" items="${sessionScope[additionalInOrder]}" varStatus="status">
        <tr>
            <td style="text-align: left;">
                    ${addition.name}
            </td>
            <td>
                <div>
                    <form id="additions + ${status.count}" method="POST"
                          action="<c:url value='/order/change-product-count'/>"
                          style="display: inline-block; text-align: center">
                        <input name="productId" type="hidden" value="${addition.productId}"/>
                        <input name="value" type="text" class="form-control input-sm" value="${addition.quantity}"
                               style="width: 45px; text-align: center;"/>
                    </form>
                    <a style="cursor: pointer;"
                       onclick="document.getElementById('additions + ${status.count}').submit(); return false;">
                        <img src="<c:url value='/resources/button_ref.png'/>"/>
                    </a>
                </div>
            </td>
            <td style="font-size: 22;">
                    ${addition.price * addition.quantity}
            </td>
            <td>
                <a href="<c:url value='/order/remove-product/${addition.productId}'/>">
                    <img src="<c:url value='/resources/del.png'/>"/>
                </a>
            </td>
        </tr>
    </c:forEach>
    <!-- Additions in order end -->
</table>

<tr>
    <hr style="border-color: #68a9ff;">
</tr>

<div class="well">

    <c:if test="${errorMessage != null}">
        <div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${errorMessage}</strong>
        </div>
    </c:if>

    <form class="bs-example form-horizontal" method="POST"
            <sec:authorize access="isAnonymous()">
                action="<c:url value='/order/send-order'/>"
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                action="<c:url value='/user/send-order'/>"
            </sec:authorize>>
        <fieldset>

            <sec:authorize access="isAnonymous()">
                <legend>Order form</legend>
                <div class="form-group">
                    <label for="name" class="col-lg-2 control-label">Name</label>

                    <div class="col-lg-10">
                        <input type="text" value="${guestUser.name}" class="form-control input-sm"
                               name="name" id="name" placeholder="Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-lg-2 control-label">Address</label>

                    <div class="col-lg-10">
                        <input type="text" value="${guestUser.address}" class="form-control input-sm"
                               name="address" id="address" placeholder="Address">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-lg-2 control-label">Phone</label>

                    <div class="col-lg-10">
                        <input type="text" value="${guestUser.phone}" class="form-control input-sm"
                               name="phone" id="phone" placeholder="Phone">
                    </div>
                </div>
            </sec:authorize>

            <p class="text-center text-info" style="font-size: 25px; padding-left: 30%; padding-right: 30%">
                <b>Total price:</b> &nbsp;&nbsp;&nbsp;
                <c:set var="orderName" value="order"/>
                <b>${sessionScope[orderName].price} $</b>
                <button
                        <c:if test="${fn:length(sessionScope[pizzasInOrder]) == 0}">
                            disabled
                        </c:if>
                   type="submit" class="btn btn-large btn-block btn-primary">
                    Send
                </button>
            </p>
        </fieldset>
    </form>
</div>
