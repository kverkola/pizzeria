<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>Pizza name: ${pizza.name}</p>
<p>Pizza price: ${pizza.price}</p>

<table class="table table-hover" style="width: 30%;">
    <thead>
        <tr>
            <th>Ingredient</th>
            <th>Weight</th>
            <th>Count</th>
            <th>Price</th>
        </tr>
    </thead>
    <c:forEach var="entry" items="${pizza.map}">
        <tr>
            <td>${entry.key.name}</td>
            <td>${entry.key.weight}</td>
            <td>${entry.value}</td>
            <td>${entry.key.price}</td>
        </tr>
    </c:forEach>
</table>

<b>Total price: ${totalPrice}</b>
