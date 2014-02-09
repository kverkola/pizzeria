<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>Order</h1>

<div>

	<c:forEach var="order" items="${orders}">
	
<dl class="dl-horizontal">
    <dt>start time:</dt>
    <dd>${order.starttime}</dd>
     <dt>End Time:</dt>
    <dd>${order.endtime}</dd>
     <dt>Phone:</dt>
    <dd>${order.customer.phone}</dd>
     <dt>Name customer:</dt>
    <dd>${order.customer.name}</dd>
     <dt>adress:</dt>
        <dd>${order.customer.address}</dd>
     <dt>Price:</dt>
        <dd>${order.price}&nbsp;$</dd>
     <dt>Status:</dt>
        <dd>${order.status}</dd>
    </dl>



<c:forEach var="product" items="${order.products}">

<dl class="dl-horizontal">
    <dt>Products:</dt>
    <dd><a href="#" class="big-link"
					data-reveal-id="${product.id}"> ${product.name} </a></dd>    
    </dl>
     
    <div id="${product.id}" class="reveal-modal">
				<h1>${product.name}</h1>
				<p>${product.price}</P>
				<img src="<c:url value='/resources/${product.logo}'/>" /> <a
					class="close-reveal-modal">&#215;</a>
			</div>
    
    
</c:forEach>

</c:forEach>

</div>



   
