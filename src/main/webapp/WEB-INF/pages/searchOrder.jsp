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
				<td>id</td>
				<td>${order.id}</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td>Start time</td>
				<td>${order.starttime}</td>
			</tr>
			<tr>
				<td>End time</td>
				<td>${order.endtime}</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>${order.phone}</td>
			</tr>
			<tr>
				<td>Price</td>
				<td>${order.price}</td>
			</tr>
		</tbody>





	</table>



	<dl class="dl-horizontal">