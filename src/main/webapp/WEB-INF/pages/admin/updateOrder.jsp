<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>updateOrder</h1>



 <form method="GET" action="<c:url value='/adminSearchOrder'/>">
                
                    <div class="input-group text-center">
                        <input type="text" class="form-control" placeholder="Search order"
                            class="inp" name="param"      >
                      
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default" type="button">Search</button>
                        </span>
                        
                    </div><br>
                                               <select class="form-control">
  <option>Search Orders By Phone</option>
  <option>Search Order By Id</option>
  <option>Search Order By Status</option>
</select> 
        </form>
        
        
        
        
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
     <dt>Status:</dt>
        <dd>${order.status}</dd>
    </dl>
        
       </c:forEach> 
        
    </div>    
        
        
        
        
        
        
        
        
        
        
        
