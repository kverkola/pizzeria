<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1>${pizza.name}</h1>
<tr>
	<td><img src="<c:url value='/resources/${pizza.logo}'/>" /></td>


</tr>

<table class="table " style="width: 80%;">
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
			<td>${entry.key.name}
			<td>${entry.key.weight}</td>
			<td>
					 <a href="<c:url value='/ingredients/count/${entry.key.name}/{up}'/>">
                        <img src="<c:url value='/resources/up.png'/>"/>
                    </a>
					&nbsp ${entry.value} &nbsp
     		  <a href="<c:url value='/ingredients/count/${entry.key.name}/{down}'/>">
                        <img src="<c:url value='/resources/down.png'/>"/>
                    </a>


				</td>
			<td>${entry.key.price}</td>
		</tr>

	</c:forEach>

</table>