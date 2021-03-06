<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Pizzeria</title>
<link rel="stylesheet" href="<c:url value='/resources/reveal.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap.min.css'/>" />
</head>
<body>

	<div style="min-height: 30px;"></div>
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header"
				style="margin-top: -25px; max-height: 40px;">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="<c:url value='/'/>"> <img
					src="<c:url value='/resources/logo.png'/>" />
				</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li
						<c:if test="${pageContext.request.requestURI == '/pizzeria/'}">
                    class="active"
                </c:if>>
						<a href="<c:url value='/'/>">Home</a>
					</li>
					<li
						<c:if test="${pageContext.request.requestURI == '/pizzeria/menu'}">
                    class="active"
                </c:if>>
						<a href="<c:url value='/menu'/>">Menu</a>

					</li>
					<li
						<c:if test="${pageContext.request.requestURI == '/pizzeria/additional'}">
                    class="active"
                </c:if>>
						<a href="<c:url value='/additional'/>">Additional</a>

					</li>
					<li
						<c:if
                        test="${pageContext.request.requestURI == '/pizzeria/feedback'}">
                    class="active"
                </c:if>>
						<a href="<c:url value='/feedback'/>">Feedback</a>
					</li>

					<li
						<c:if test="${pageContext.request.requestURI == '/pizzeria/about'}">
                    class="active"
                </c:if>>
						<a href="<c:url value='/about'/>">About</a>
					</li>

					<sec:authorize access="hasRole('ROLE_COOK')">
						<li
							<c:if test="${pageContext.request.requestURI == '/pizzeria/cook'}">
                        class="active"
                    </c:if>>
							<a href="<c:url value='/cook'/>">Pizzas query</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MANAGER')">
						<li
							<c:if test="${pageContext.request.requestURI == '/pizzeria/adminPanel'}">
                        class="active"
                    </c:if>>
							<a href="<c:url value='/adminPanel'/>">Admin Panel</a>
						</li>
					</sec:authorize>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="isAnonymous()">
						<li
							<c:if
                            test="${pageContext.request.requestURI == '/pizzeria/login'}"> class="active" </c:if>>
							<a href="<c:url value='/login'/>">Log in</a>
						</li>
						<li
							<c:if
                            test="${pageContext.request.requestURI == '/pizzeria/register'}"> class="active" </c:if>>
							<a href="<c:url value='/register'/>">Register</a>
						</li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a style="font-weight: bold"><sec:authentication
									property="principal.username" /></a></li>
						<li><a href="<c:url value='/j_spring_security_logout' />">Log
								out</a></li>
					</sec:authorize>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container-fluid" style="min-height: 70%;">
		<div class="container">
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-7">
					<div class="bs-example">
						<jsp:include page="${partial}" />
					</div>
				</div>

				<sec:authorize access="isAnonymous() or hasRole('ROLE_USER')">
					<div class="col-lg-3" style="padding-top: 50px;">
						<div class="bs-example">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title" style="text-align: center;">Order</h3>
								</div>
								<div class="panel-body">
									<p>----------------------------------------------</p>
									<table>
										<c:set var="pizzasInOrder" value="pizzasInOrder" />
										<c:forEach var="pizza" items="${sessionScope[pizzasInOrder]}">
											<tr>
												<td style="text-align: left;">${pizza.name}
													<div style="width: 235px;">
														${pizza.quantity} x
														<div style="float: right;">${pizza.price *
															pizza.quantity} $</div>
													</div>
												</td>
											</tr>
										</c:forEach>
										<c:set var="additionalInOrder" value="additionalInOrder" />
										<c:forEach var="addition"
											items="${sessionScope[additionalInOrder]}">
											<tr>
												<td style="text-align: left;">${addition.name}
													<div style="width: 235px;">
														${addition.quantity} x
														<div style="float: right;">${addition.price *
															addition.quantity} $</div>
													</div>
												</td>
											</tr>
										</c:forEach>
									</table>
									<p>----------------------------------------------</p>

									<div style="width: 235px;">
										<b>Total price:</b>

										<div style="float: right;">
											<c:set var="orderName" value="order" />
											<b>${sessionScope[orderName].price} $</b>
										</div>
									</div>
									<p></p>

									<p style="text-align: center;">
										<c:choose>
											<c:when test="${fn:length(sessionScope[pizzasInOrder]) > 0}">
												<a href="<c:url value='/order/make-order'/>"
													class="btn btn-sm btn-warning"> Make order </a>
											</c:when>
											<c:otherwise>
												<a href="<c:url value='/order/make-order'/>"
													class="btn btn-sm btn-warning disabled"> Make order </a>
											</c:otherwise>
										</c:choose>
									</p>

									<p>Courier is obliged to issue a check.</p>
								</div>
							</div>
						</div>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>


	<div class="navbar-bottom text-center">
		<hr>
		<footer>
			<p>&copy; NetCracker Courses 2013-2014</p>
		</footer>
	</div>


	<!-- Bootstrap core JavaScript
================================================== -->
	<script src="<c:url value='/resources/jquery-1.8.3.min.js'/>"></script>
	<script src="<c:url value='/resources/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/bootswatch.js'/>"></script>
	<script src="<c:url value='/resources/jquery.reveal.js'/>"></script>

</body>
</html>