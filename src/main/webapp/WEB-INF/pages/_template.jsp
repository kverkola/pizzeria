<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Pizzeria</title>
    <!-- Bootstrap theme -->
    <!-- Change theme name before /bootstrap.min.css, example flatly, spacelab, united etc. -->
    <link rel="stylesheet" href="http://bootswatch.com/flatly/bootstrap.min.css">
</head>
<body>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/">Pizzeria</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/'}">
                        class="active"
                    </c:if> >
                    <a href="${pageContext.servletContext.contextPath}/">Home</a>
                </li>
                <li <c:if test="${pageContext.request.requestURI == '${pageContext.servletContext.contextPath}/about'}">
                        class="active"
                    </c:if> >
                    <a href="${pageContext.servletContext.contextPath}/about">About</a>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div class="container" style="margin-top: 40px;">

    <jsp:include page="${partial}" />

    <div>
        <hr>
        <footer>
            <p>&copy; NetCracker Courses 2013-2014</p>
        </footer>
    </div>
</div>
</div>
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="http://getbootstrap.com/assets/js/jquery.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

</body>
</html>