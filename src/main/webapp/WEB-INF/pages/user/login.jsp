<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="well" style="margin: 0 auto; width: 450px; margin-top: 50px;">

    <c:if test="${not empty error}">
        <div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong>
        </div>
    </c:if>

    <form class="bs-example form-horizontal" action="<c:url value='j_spring_security_check' />" method="POST">
        <fieldset>

            <legend>Login</legend>
            <div class="form-group">
                <label for="login" class="col-lg-2 control-label">Login</label>

                <div class="col-lg-10">
                    <input type="text" value="" class="form-control input-sm"
                           name='j_username' id="login" placeholder="Login">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-lg-2 control-label">Password</label>

                <div class="col-lg-10">
                    <input type="password" value="" class="form-control input-sm"
                           name='j_password' id="password" placeholder="Password">
                </div>
            </div>
            <div class="text-right">
                <input class="btn btn-sm btn-success" name="submit" type="submit" value="Submit" />
            </div>

        </fieldset>
    </form>
</div>