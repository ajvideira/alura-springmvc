<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<c:url value="/" var="contextPath" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description"
	content="Site desenvolvido no curso de SpringMVC da alura. ">
<meta name="author" content="ajvideira">
<!--<link rel="icon" href="../../favicon.ico">-->

<title>Alura - SpringMVC</title>

<!-- Bootstrap core CSS -->
<link href="${contextPath}resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${contextPath}resources/css/bootstrap-theme.min.css"
	rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="${contextPath}resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<link href="${contextPath}resources/css/style.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

<script src="${contextPath}resources/js/bootstrap.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
</head>
<body>
	<div class="container">

		<form:form class="form-signin" servletRelativeAction="/login" method="post">
			<h2 class="form-signin-heading">Identifique-se aqui</h2>
			<label for="inputEmail" class="sr-only">E-mail</label> 
			<input type="email" id="inputEmail" class="form-control"
				placeholder="Email address" required autofocus name="username" /> 
			<label for="inputPassword" class="sr-only">Senha</label> 
			<input type="password" id="inputPassword" class="form-control"
				placeholder="Password" required name="password" />
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Lembrar meus dados
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
		</form:form>

	</div>
	<!-- /container -->
</body>
</html>