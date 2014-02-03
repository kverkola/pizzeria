<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1>${pizza.name} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	${pizza.price}&nbsp&nbsp $</h1>

<body>
	<tr>
		<td><img src="<c:url value='/resources/${pizza.logo}'/>" /></td>

		<td> <a href="#myModal" role="button" class="btn btn-sm btn-success"
			data-reveal-id="myModal">Upgrade Pizza</a></td>
	</tr>

	<table class="table " style="width: 80%;">
		<thead>
			<tr>
				<th>Ingredient</th>
				<th>Weight</th>
				<th>Price</th>
				<th>Count</th>
			</tr>
		</thead>

		<c:forEach var="entry" items="${pizza.map}">

			<tr>
				<td>${entry.key.name}</td>
				<td>${entry.key.weight}</td>
				<td>${entry.key.price}</td>
				<td><a
					href="<c:url value='/ingredients/countPlus/${entry.key.id}'/>">
						<img src="<c:url value='/resources/up.png'/>" />
				</a> &nbsp ${entry.value} &nbsp <a
					href="<c:url value='/ingredients/countMinus/${entry.key.id}'/>">
						<img src="<c:url value='/resources/down.png'/>" />
				</a></td>

			</tr>

		</c:forEach>

	</table>


<div class="pull-left">
	
		<a href="<c:url value='/ingredients/reset/${pizza.id}'/>"
		class="btn btn-info   "> Reset </a></div>
	

	<div class="pull-right">
<a
			href="<c:url value='/order/add-custom-pizza'/>"
			class="btn btn-sm btn-success  pull-right"> Add to cart </a>
		
	</div>



	<div id="myModal" class="reveal-modal">

		<h1>add ingredients</h1>
		<p>This is a default modal in all its glory, but any of the styles
			here can easily be changed in the CSS.</p>
		<a class="close-reveal-modal">&#215;</a>
		<div class="modal-body">

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


				<a href="<c:url value='/ingredients/upgradePizza/${entry.id}"'/>"
					class="btn btn-sm btn-success"> Add to pizza </a>

			</c:forEach>



		</div>


	</div>



</body>


