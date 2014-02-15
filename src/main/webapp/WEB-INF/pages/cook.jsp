<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="refresh" content="15" />

<div>
    <table class="table">
        <th>Pizza</th>
        <th>Cook</th>
        <c:forEach var="pizza" items="${pizzas}">
            <tr>
                <td style="text-align: left;">
                        ${pizza.name}
                    <p style="padding-top: 10px;">
                        <span class="label label-info">contains:</span><br>
                        <c:forEach var="ingredient" items="${pizza.map}">
                            <p>
                                ${ingredient.key.name} - <span class="badge">${ingredient.value}</span>&nbsp;
                            </p>
                        </c:forEach>
                    </p>
                </td>
                <td style="vertical-align: middle;">
                    ${pizza.cook}
                </td>
                <td style="vertical-align: middle;">
                    <a href="<c:url value='/cook/assign/${pizza.id}'/>"
                       class="btn btn-success"
                       <c:if test="${pizza.cook != 'Empty'}">
                            disabled
                       </c:if>>
                        Assign To Me
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>