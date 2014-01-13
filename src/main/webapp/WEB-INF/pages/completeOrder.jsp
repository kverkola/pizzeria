<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    th, td {
        text-align: center;
    }
</style>

<h4>Your order</h4>

<hr style="border-color: #68a9ff;">

<table class="table">
    <tr>
        <th style="width: 70%; text-align: left;">Name</th>
        <th>Count</th>
        <th>Price, $</th>
    </tr>
    <c:set var="pizzasInOrder" value="pizzasInOrder"/>
    <c:forEach var="entry" items="${sessionScope[pizzasInOrder]}">
        <tr>
            <td style="text-align: left;">
                ${entry.key.name}
            </td>
            <td>
                <div>
                    <form method="POST" action="changeCount" style="display: inline-block; text-align: center">
                        <input type="text" class="form-control input-sm" value="${entry.value}"
                               style="width: 45px; text-align: center;"/>
                    </form>
                    <a href="#"><img src="<c:url value='/resources/button_ref.png'/>"/></a>
                </div>
            </td>
            <td>
                 ${entry.key.price * entry.value}
            </td>
        </tr>
    </c:forEach>
</table>