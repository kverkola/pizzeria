<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div style="position: absolute; top: 400px; left: -200px">

	<div style='position: fixed;'>


		<a href="<c:url value='/ingredients/addIngredients/0'/>"> <img
			src="<c:url value='/resources/forward.png'/>" /></a>
	</div>
</div>

<body>

	<div class="pull-center">

		<c:forEach var="entry" items="${ingredients}">
			<h3>${entry.name}</h3>
			<dl class="dl-horizontal">


				<dt>weight:</dt>
				<dd>${entry.weight}</dd>
				<dt>price:</dt>
				<dd>${entry.price}&nbsp;&nbsp; $</dd>

			</dl>



			<img src="<c:url value='/resources/${entry.logo}'/>" />
			<td style="padding-left: 20px; vertical-align: top;"></td>


			<a href="<c:url value='/ingredients/addNewIngr/${entry.id}"'/>"
				class="btn btn-sm btn-success"> Add to pizza </a>

		</c:forEach>
	</div>


</body>

