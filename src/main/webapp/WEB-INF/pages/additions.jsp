<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1>Additional</h1>
    <table style="min-width: 100%;">
        <c:forEach var="add" items="${add}">
            <tr>
                <th colspan="2"><hr style="border-color: #68a9ff;"></th>
            </tr>
            <tr>
                <td><img src="<c:url value='/resources/${add.logo}'/>"/></td>
                <td style="padding-left: 20px; vertical-align: top;">
                    <h3>${add.name}</h3>

                </td>
            </tr>
            <tr>
                <td></td>
                <td style="text-align: right;">
                        ${add.price}$ &nbsp;&nbsp;
                    <a href="<c:url value='/order/add-additional/${add.id}'/>" class="btn btn-sm btn-success">
                        Add to cart
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>