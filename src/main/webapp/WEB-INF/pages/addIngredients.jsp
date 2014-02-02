<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1>${pizza.name} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	${pizza.price}&nbsp&nbsp $</h1>
<tr>
	<td><img src="<c:url value='/resources/${pizza.logo}'/>" /></td>

	<td style="text-align: right;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a
		href="<c:url value='/order/add-custom-pizza'/>"
		class="btn btn-sm btn-success"> Add to cart </a></td>
</tr>

<table class="table " style="width: 80%;">
	<thead>
		<tr>
			<th>Ingredient</th>
			<th>Weight</th>
			<th>Price</th>
			<th>Count</th>
		</tr>
	</thead>

	<c:forEach var="entry" items="${pizza.map}">

		<tr>
			<td>${entry.key.name}</td>
			<td>${entry.key.weight}</td>
			<td>${entry.key.price}</td>
			<td><a
				href="<c:url value='/ingredients/countPlus/${entry.key.id}'/>">
					<img src="<c:url value='/resources/up.png'/>" />
			</a> &nbsp ${entry.value} &nbsp <a
				href="<c:url value='/ingredients/countMinus/${entry.key.id}'/>">
					<img src="<c:url value='/resources/down.png'/>" />
			</a></td>
		
		</tr>

	</c:forEach>

</table>



<td style="text-align: right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<c:url value='/ingredients/reset/${pizza.id}'/>"
	class="btn btn-info"> Reset </a>
</td>

