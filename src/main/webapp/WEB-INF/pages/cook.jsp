<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="refresh" content="15"/>

<div>
    <table class="table">
        <th>Unsigned Pizzas</th>
        <th></th>
        <th>Pizzas assigned to you</th>
        <tr>
            <td>
                <table>
                    <th>Pizza</th>
                    <th style="text-align: center;">Cook</th>
                    <th></th>
                    <c:forEach var="pizza" items="${unsignedPizzas}">
                        <tr>
                            <td style="text-align: left; min-width: 150px;">
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
                            <td style="text-align: center; vertical-align: middle; min-width: 150px;">
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
            </td>
            <td style="border-left: thick solid #68a9ff;">

            </td>
            <td>
                <table>
                    <th>Pizza</th>
                    <c:forEach var="pizza" items="${assignedPizzas}">
                        <tr>
                            <td style="text-align: left; min-width: 200px;">
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
                                <a href="<c:url value='/cook/finish-pizza/${pizza.id}'/>"
                                   class="btn btn-warning">
                                    Finished
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>