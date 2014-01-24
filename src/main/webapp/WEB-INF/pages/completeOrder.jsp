<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    th, td {
        text-align: center;
        vertical-align: middle;
    }
</style>

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
    <c:forEach var="entry" items="${sessionScope[pizzasInOrder]}" varStatus="status">
        <tr>
            <td style="text-align: left;">
                ${entry.key.name}
            </td>
            <td>
                <div>
                    <form id="pizzas+${status.count}" method="POST" action="/order/change-pizzas-count" style="display: inline-block; text-align: center">
                        <input name="id" type="hidden" value="${entry.key.id}" />
                        <input name="value" type="text" class="form-control input-sm" value="${entry.value}"
                               style="width: 45px; text-align: center;"/>
                    </form>
                    <a style="cursor: pointer;" onclick="document.getElementById('pizzas+${status.count}').submit(); return false;">
                        <img src="<c:url value='/resources/button_ref.png'/>"/>
                    </a>
                </div>
            </td>
            <td style="font-size: 22;">
                 ${entry.key.price * entry.value}
            </td>
            <td>
                <a href="<c:url value='/order/remove-pizza/${entry.key.id}'/>">
                    <img src="<c:url value='/resources/del.png'/>"/>
                </a>
            </td>
        </tr>
    </c:forEach>

    <!-- Pizzas in order end -->

    <!-- Additions in order begin -->

    <c:set var="additionalInOrder" value="additionalInOrder"/>
    <c:forEach var="entry" items="${sessionScope[additionalInOrder]}" varStatus="status">
        <tr>
            <td style="text-align: left;">
                    ${entry.key.name}
            </td>
            <td>
                <div>
                    <form id="additions+${status.count}" method="POST" action="/order/change-additions-count"
                          style="display: inline-block; text-align: center">
                        <input name="name" type="hidden" value="${entry.key.name}" />
                        <input name="value" type="text" class="form-control input-sm" value="${entry.value}"
                               style="width: 45px; text-align: center;"/>
                    </form>
                    <a style="cursor: pointer;" onclick="document.getElementById('additions+${status.count}').submit(); return false;">
                        <img src="<c:url value='/resources/button_ref.png'/>"/>
                    </a>
                </div>
            </td>
            <td style="font-size: 22;">
                    ${entry.key.price * entry.value}
            </td>
            <td>
                <a href="<c:url value='/order/remove-additional/${entry.key.name}'/>">
                    <img src="<c:url value='/resources/del.png'/>"/>
                </a>
            </td>
        </tr>
    </c:forEach>

    <!-- Additions in order end -->

</table>