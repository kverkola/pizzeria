<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>Orders</h1>

<div>

	<c:forEach var="order" items="${orders}">
	
<dl class="dl-horizontal">
    <dt>start time:</dt>
    <dd>${order.starttime}</dd><br>
     <dt>End Time:</dt>
    <dd>${order.endtime}</dd><br>
     <dt>Phone:</dt>
    <dd>${order.customer.phone}</dd><br>
     <dt>Name customer:</dt>
    <dd>${order.customer.name}</dd><br>
     <dt>adress:</dt>
        <dd>${order.customer.address}</dd><br>
     <dt>Status:</dt>
        <dd>${order.status}</dd><br>
    </dl>



<c:forEach var="product" items="${order.products}">

<dl class="dl-horizontal">
    <dt>Products:</dt>
    <dd><a href="#" class="big-link"
					data-reveal-id="${product.id}"> ${product.name} </a></dd>    
    </dl>
     
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
                       </c:if>
				
				
				
				<h1>${product.price}&nbsp;$</h1>
				<img src="<c:url value='/resources/${product.logo}'/>" /> <a
					class="close-reveal-modal">&#215;</a>
			</div>
    
   
    
    
</c:forEach>
 <dt>Price:</dt>
        <dd>${order.price}&nbsp;$</dd>
------------------------------------------------------------------

</c:forEach>

         <c:if test="${not empty nothing }">
        <div class="alert alert-dismissable alert-danger">
           <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${nothing}</strong>
        </div>
    </c:if>
</div>



   
