<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consumer-Policy</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark text-white">
<nav class="navbar navbar-expand-sm bg-light navbar-light">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand disabled" href="#">PAM</a>
    </div>

</nav>
<div class="right-main pt-5">
<h1 class="text-center text-success">Policy Management System</h1>
			<div class="container login-section pt-3">
				<h5>LOGIN HERE</h5>
				<form:form action="/policy/login" method="post"
					modelAttribute="user">
					<div class="form-group">
						<form:label path="userName" for="username">Username</form:label>
						<form:input type="text" path="userName" class="form-control"
							id="username" name="name" placeholder="Enter Username"
							required="required" autocomplete="off" />

					</div>
					<div class="form-group">
						<form:label path="password" for="password">Password</form:label>
						<form:input type="password" path="password" class="form-control"
							id="password" name="password" placeholder="Enter Password"
							required="required" autocomplete="off" />

					</div>
					<form:button type="submit" class="btn btn-outline-success">Login</form:button>
					<button class="btn btn-outline-warning" type="reset">Reset</button>
				</form:form>
				<c:choose>
					<c:when test="${not empty errorMessage}">

						<div class="error">${errorMessage}</div>
					</c:when>
				</c:choose>
			</div>
		</div>

</body>
</html>