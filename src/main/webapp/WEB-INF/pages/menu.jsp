<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<h1>Pizza</h1>
	<table>
		<c:forEach var="pizza" items="${menu}">
			<tr>
				<th colspan="2"><hr style="border-color: #68a9ff;"></th>
			</tr>
			<tr>
				<td><img src="<c:url value='/resources/${pizza.logo}'/>" /></td>
				<td style="padding-left: 20px; vertical-align: top;">
					<h3>${pizza.name}</h3>
					<p>${pizza.description}</p> <a
					href="<c:url value='/order/addIngredients/${pizza.id}'/>"
					class="btn btn-sm btn-success">Ingredients</a>
				</td>

			</tr>
			<tr>
				<td></td>



				<td style="text-align: right;">${pizza.price}$ &nbsp;&nbsp; <a
					href="<c:url value='/order/add-pizza/${pizza.name}'/>"
					class="btn btn-sm btn-success"> Add to cart </a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>