<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
 <c:if test="${not empty nothing }">
        <div class="alert alert-dismissable alert-danger">
           <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${nothing}</strong>
        </div>
    </c:if>
    
<c:if test="${not empty illegalArgument }">
	<div class="alert alert-dismissable alert-danger">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>${illegalArgument}</strong>
	</div>
</c:if>
	<div>

		<c:forEach var="order" items="${orders}">
<form method="GET" action="<c:url value='/admin/updateOrder'/>">
			<table class="table table-condensed">


				<tr>
					<td>OrderId:</td>
					<td>${order.id}</td>
					<td>
					
					<div>
							<input type="hidden" class="inp" name="id" value="${order.id}">
						</div></td>
				</tr>
				<tr>
					<td>Start Time:</td>
					<td>${order.starttime}</td>
					<td>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<div>
							<input type="text" class="inp" name="newStarttime">
						</div>
						</sec:authorize>
					</td>
				</tr>
				<tr>
					<td>End Time:</td>
					<td>${order.endtime}</td>
					<td><div>
							<input type="text" class="inp"  name="newEndtime">
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
					<td><sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<div>
							<p><input name="newPrice" pattern="^[ 0-9]+$"></p>
						</div>
						</sec:authorize></td>
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
				</tr><c:forEach var="product" items="${order.products}">
<tr>

<td>product:</td>
<td><a href="#" class="big-link"
					data-reveal-id="${product.id}"> ${product.name} </a>
					
					
					
					 <div id="${product.id}" class="reveal-modal">
				<h1>${product.name}</h1>
				<c:if test="${product.getClass().toString().equals('class ua.opu.dl.pizzeria.model.Pizza')}">
				
				           
              <table class="table table-hover" style="width: 50%;">
	<thead>
		<tr>
			<th>Ingredient</th>
			<th>Weight</th>
			<th>Count</th>
			<th>Price</th>
		</tr>
	</thead>
	<c:forEach var="entry" items="${product.map}">
		<tr>
			<td>${entry.key.name}</td>
			<td>${entry.key.weight}</td>
			<td>${entry.value}</td>
			<td>${entry.key.price}&nbsp;$</td>
		</tr>
	</c:forEach>
	
</table>    
       <p>Cook:</p><p>${product.getCook().firstName}&nbsp;${product.getCook().lastName}</p>
                       </c:if>
				
				
				
				<h1>${product.price}&nbsp;$</h1>
				<img src="<c:url value='/resources/${product.logo}'/>" /> <a
					class="close-reveal-modal">&#215;</a>
				
				
				
				

				</div></td>

</tr></c:forEach>
			</table>
			
			
			
			
 
                            <button type="submit" class="btn btn-default" type="button">Update</button>
                        
                        <div class="pull-right">
                        <input class="btn btn-sm btn-info" type="reset" value="Reset!"></div><br>
                        
-----------------------------------------------------------------------------------------
</form>
		</c:forEach>

	</div>











