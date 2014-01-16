<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>Order</h1>

<table class="table " style="width: 50%;">
	<thead>
		<tr>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p class="text-info"><strong>id</strong></p></td>
			<td>${order.id}</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>Pizza</strong></p></td>
			
<td>
				<table class="table table-hover" style="width: 50%;">
    <thead>
        <tr>
            <th>Name</th>
            <th>Count</th>
            <th>Price</th>
        </tr>
    </thead>
    <c:forEach var="entry" items="${order.pizzas}">
        <tr>
            <td><a href="<c:url value='/order/showIngredient/${entry.key.id}'/>">${entry.key.name}<a></td>
            <td>${entry.value}</td>
            <td>${entry.key.price}</td>
        </tr>
    </c:forEach>
</table>
		</td>
			
		</tr>
		
		<tr>
			<td><p class="text-info"><strong>Additionals</strong></p></td>
			<td>
				<table class="table " style="width: 50%;">
    <thead>
        <tr>
            <th>Name</th>
            <th>Count</th>
            <th>Price</th>
        </tr>
    </thead>
    <c:forEach var="entry" items="${order.additional}">
        <tr>
            <td>${entry.key.name}</td>
            <td>${entry.value}</td>
            <td>${entry.key.price}</td>
        </tr>
    </c:forEach>
</table>
		</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>Status</strong></p></td>
			<td>${order.status}</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>Start time</strong></p></td>
			<td>${order.starttime}</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>End time</strong></p></td>
			<td>${order.endtime}</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>Phone</strong></p></td>
			<td>${order.phone}</td>
		</tr>
		<tr>
			<td><p class="text-info"><strong>Price</strong></p></td>
			<td>${order.price}</td>
		</tr>
	</tbody>

</table>



