<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Additional</h1>

<table class="table table-hover" style="width: 50%;">
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



<form method="get" action="/order/searchOrder">
	<input type="hidden" class="inp" name="orderId" value="${order.id}">
	<button type="submit" class="btn btn-sm btn-success">Back</button>
</form>

