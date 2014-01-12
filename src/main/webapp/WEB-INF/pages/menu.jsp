<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1>Pizza</h1>
    <table>
        <c:forEach var="pizza" items="${menu}">
            <tr>
                <th colspan="2"><hr style="border-color: #68a9ff;"></th>
            </tr>
            <tr>
                <td><img src="<c:url value='/resources/${pizza.logo}'/>"/></td>
                <td style="padding-left: 20px; vertical-align: top;">
                    <h3>${pizza.name}</h3>
                    <p>${pizza.description}</p>
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="text-align: right;">
                    ${pizza.price}$ &nbsp;&nbsp;
                    <!--<a href="/add-pizza/${pizza.name}" class="btn btn-sm btn-success">Add to cart</a>-->
                        <a href="#" class="btn btn-sm btn-success">Add to cart</a></td>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>