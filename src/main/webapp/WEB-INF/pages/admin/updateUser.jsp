<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>updateUser</h1>
<form method="GET" action="<c:url value='/admin/adminSearchUser'/>">

	<div class="input-group text-center">
		<input type="text" class="form-control" placeholder="Search user"
			class="inp" name="param"> <span class="input-group-btn">
			<button type="submit" class="btn btn-default" type="button">Search</button>
		</span>

	</div>
	<br> <select class="form-control" name="typeSearch">
		<option>Search User By login</option>
		<option>Search User By Id</option>
		<option>Search Users By Role</option>
	</select>
</form>

<div style="position: absolute; top: 400px; left: -200px">

	<div style='position: fixed;'>

		<a href="<c:url value='/adminPanel'/>"> <img
			src="<c:url value='/resources/forward.png'/>" /></a>
	</div>
</div>



<form method="GET" action="<c:url value='/admin/updateUser'/>">
	<div>

		<c:forEach var="user" items="${users}">

			<table class="table table-condensed">


				<tr>
					<td>UserId:</td>
					<td>${user.id}</td>
					<td><div>
							<input type="hidden" class="inp" name="id" value="${user.id}">
						</div></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td>${user.firstName}</td>
					<td>
						<div>
							<input type="text" class="inp" name="newfirstName">
						</div>
					</td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td>${user.lastName}</td>
					<td><div>
							<input type="text" class="inp" name="newlastName">
						</div></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td>${user.role}</td>
					<td><select class="control" name="newRole">
							<option>COOK</option>
							<option>MANAGER</option>
							<option>ADMIN</option>
							<option>CUSTOMER</option>
					</select></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td>${user.login}</td>
					<td><div>
							<input type="text" class="inp" name="newLogin">
						</div></td>
				</tr>
				<tr>
					<td>password:</td>
					<td>${user.password}</td>
					<td><div>
							<input type="text" class="inp" name="newpassword">
						</div></td>
				</tr>
				

			</table>
 
                            <button type="submit" class="btn btn-default" type="button">Update</button>
                        
                        <div class="pull-right">
                        <input class="btn btn-sm btn-info" type="reset" value="Reset!"></div><br>
                        
-----------------------------------------------------------------------------------------
		</c:forEach>

	</div>


</form>
