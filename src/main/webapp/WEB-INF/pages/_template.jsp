<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Pizzeria</title>
    <link rel="stylesheet" href="<c:url value='/resources/bootstrap.min.css'/>"/>
</head>
<body>

<div style="min-height: 30px;"></div>
<div class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header" style="margin-top: -25px; max-height: 40px;">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="<c:url value='/'/>">
                <img src="<c:url value='/resources/logo.png'/>"/>
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/'}">
                    class="active"
                </c:if> >
                    <a href="<c:url value='/'/>">Home</a>
                </li>
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/menu'}">
                    class="active"
                </c:if> >
                    <a href="<c:url value='/menu'/>">Menu</a>
                    
                </li>
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/Additional'}">
                    class="active"
                </c:if> >
                    <a href="<c:url value='/Additional'/>">Additional</a>
                    
                </li>
                <li <c:if
                        test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/feedback'}">
                    class="active"
                </c:if> >
                    <a href="<c:url value='/feedback'/>">Feedback</a>
                </li>
               
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/about'}">
                    class="active"
                </c:if> >
                    <a href="<c:url value='/about'/>">About</a>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>

<div class="container" style="min-height: 70%;">
    <div class="row">
        <div class="col-lg-2">
            <div class="bs-example">

            </div>
        </div>
        <div class="col-lg-7">
            <div class="bs-example">
                <jsp:include page="${partial}"/>
            </div>
        </div>
        <div class="col-lg-3" style="float: right; position: fixed; right:20px; top:150px;">
            <div class="bs-example">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="text-align: center;">Order</h3>
                    </div>
                    <div class="panel-body">
                        <p>-------------------------------------------------------</p>
                        <table>
                            <c:set var="pizzasInOrder" value="pizzasInOrder"/>
                            <c:forEach var="pizza" items="${sessionScope[pizzasInOrder]}">
                                <tr>
                                    <td style="text-align: left;">${pizza.name}
                                        <div style="width: 270px;">
                                                ${pizza.quantity} x
                                            <div style="float: right;">
                                                    ${pizza.price * pizza.quantity} $
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:set var="additionalInOrder" value="additionalInOrder"/>
                            <c:forEach var="addition" items="${sessionScope[additionalInOrder]}">
                                <tr>
                                    <td style="text-align: left;">${addition.name}
                                        <div style="width: 270px;">
                                                ${addition.quantity} x
                                            <div style="float: right;">
                                                    ${addition.price * addition.quantity} $
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>-------------------------------------------------------</p>
                        <div style="width: 270px;">
                            <b>Total price:</b>
                            <div style="float: right;">
                                <c:set var="orderName" value="order"/>
                                <b>${sessionScope[orderName].price} $</b>
                            </div>
                        </div>
                        <p></p>
                        <p style="text-align: center;">
                            <c:choose>
                                <c:when test="${fn:length(sessionScope[pizzasInOrder]) > 0}">
                                    <a href="<c:url value='/order/make-order'/>" class="btn btn-sm btn-warning">
                                        Make order
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/order/make-order'/>" class="btn btn-sm btn-warning disabled">
                                        Make order
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p>
                            Courier is obliged to issue a check.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="navbar-bottom" style="text-align: center;">
    <hr>
    <footer>
        <p>&copy; NetCracker Courses 2013-2014</p>
    </footer>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<script src="<c:url value='/resources/jquery-1.10.2.min.js'/>" ></script>
<script src="<c:url value='/resources/bootstrap.min.js'/>" ></script>
<script src="<c:url value='/resources/bootswatch.js'/>" ></script>

</body>
</html>