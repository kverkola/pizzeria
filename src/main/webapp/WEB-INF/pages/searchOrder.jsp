<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>Order</h1>

<table class="table table-hover" style="width: 50%;">
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
			<td><select>

					<c:forEach var="entry" items="${order.pizzas}">

						<option>
						${entry.key.name} &nbsp &nbsp &nbsp &nbsp
							${entry.value}X &nbsp&nbsp&nbsp ${entry.key.price}&nbsp$</option>

					</c:forEach>
			</select></td>
		</tr>
		<tr>
		<tr>
			<td><p class="text-info"><strong>Additionals</strong></p></td>
			<td><select>

					<c:forEach var="entry" items="${order.additional}">

						<option>${entry.key.name} &nbsp &nbsp &nbsp &nbsp
							${entry.value}X &nbsp&nbsp&nbsp ${entry.key.price}&nbsp$</option>

					</c:forEach>

			</select></td>
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



<dl class="dl-horizontal">