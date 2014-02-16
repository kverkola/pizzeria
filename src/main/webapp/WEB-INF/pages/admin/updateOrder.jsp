<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>updateOrder</h1>



<form method="GET" action="<c:url value='/admin/adminSearchOrder'/>">

	<div class="input-group text-center">
		<input type="text" class="form-control" placeholder="Search order"
			class="inp" name="param"> <span class="input-group-btn">
			<button type="submit" class="btn btn-default" type="button">Search</button>
		</span>

	</div>
	<br> <select class="form-control" name="typeSearch">
		<option>Search Orders By Phone</option>
		<option>Search Order By Id</option>
		<option>Search Order By Status</option>
	</select>
</form>

<div style="position: absolute; top: 400px; left: -200px">

	<div style='position: fixed;'>

		<a href="<c:url value='/adminPanel'/>"> <img
			src="<c:url value='/resources/forward.png'/>" /></a>
	</div>
</div>


	<div>

		<c:forEach var="order" items="${orders}">
<form method="GET" action="<c:url value='/admin/updateOrder'/>">
			<table class="table table-condensed">


				<tr>
					<td>OrderId:</td>
					<td>${order.id}</td>
					<td><div>
							<input type="hidden" class="inp" name="id" value="${order.id}">
						</div></td>
				</tr>
				<tr>
					<td>Start Time:</td>
					<td>${order.starttime}</td>
					<td>
						<div>
							<input type="text" class="inp" name="newStarttime">
						</div>
					</td>
				</tr>
				<tr>
					<td>End Time:</td>
					<td>${order.endtime}</td>
					<td><div>
							<input type="text" class="inp" name="newEndtime">
						</div></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td>${order.status}</td>
					<td><select class="control" name="newStatus">
							<option>PRE_ORDER</option>
							<option>DELIVERY</option>
							<option>IN_WORK</option>
							<option>CLOSE</option>
					</select></td>
				</tr>
				<tr>
					<td>Price:</td>
					<td>${order.price}</td>
					<td><div>
							<input type="text" class="inp" name="newPrice">
						</div></td>
				</tr>
				<tr>
					<td>Customer name:</td>
					<td>${order.customer.name}</td>
					<td><div>
							<input type="text" class="inp" name="newCustomerName">
						</div></td>
				</tr>
				<tr>
					<td>Customer address:</td>
					<td>${order.customer.address}</td>
					<td><div>
							<input type="text" class="inp" name="newCustomerAddress">
						</div></td>
				</tr>
				<tr>
					<td>Customer phone:</td>
					<td>${order.customer.phone}</td>
					<td><div>
							<input type="text" class="inp" name="newPhone">
						</div></td>
				</tr>





			</table>
 
                            <button type="submit" class="btn btn-default" type="button">Update</button>
                        
                        <div class="pull-right">
                        <input class="btn btn-sm btn-info" type="reset" value="Reset!"></div><br>
                        
-----------------------------------------------------------------------------------------
</form>
		</c:forEach>

	</div>











