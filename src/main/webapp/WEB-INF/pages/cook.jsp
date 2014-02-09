<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div clas="table">
        <c:forEach var="order" items="${orders}">
            <tr>
                <c:forEach var="pizza" items="${order.products}">
                    <tr>
                        <td>${pizza.name}</td>
                        <td>${pizza.price}</td>
                    </tr>
                </c:forEach>
            </tr>
        </c:forEach>
    </div>
</div>