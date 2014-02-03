<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="well" style="margin: 0 auto; width: 550px; margin-top: 50px;">

    <form:form class="bs-example form-horizontal" modelAttribute="user" method="POST" action="/register">
        <fieldset>

            <legend>Registration</legend>

            <div class="form-group">
                <label for="firstName" class="col-lg-3 control-label">First Name</label>

                <div class="col-lg-8">
                    <form:errors path="firstName" cssStyle="color: red;"/>
                    <form:input class="form-control input-sm" path="firstName" id="firstName"
                                 placeholder="first name"/>
                </div>
            </div>

            <div class="form-group">
                <label for="lastName" class="col-lg-3 control-label">Last Name</label>

                <div class="col-lg-8">
                    <form:errors path="lastName" cssStyle="color: red;"/>
                    <form:input class="form-control input-sm" path="lastName" id="lastName"
                                 placeholder="last name"/>
                </div>
            </div>

            <div class="form-group">
                <label for="login" class="col-lg-3 control-label">Login</label>

                <div class="col-lg-8">
                    <form:errors path="login" cssStyle="color: red;"/>
                    <form:input class="form-control input-sm" path="login" id="login"
                                 placeholder="login"/>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-lg-3 control-label">Password</label>

                <div class="col-lg-8">
                    <form:errors path="password" cssStyle="color: red;"/>
                    <form:input class="form-control input-sm" path="password" id="password"
                                type="password" placeholder="password"/>
                </div>
            </div>

            <div class="text-right">
                <input class="btn btn-sm btn-success" name="submit" type="submit" value="Submit" />
            </div>
        </fieldset>
    </form:form>
 </div>
