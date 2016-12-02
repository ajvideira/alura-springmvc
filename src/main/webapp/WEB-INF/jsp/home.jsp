<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Alura - SpringMVC</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="<c:url value="/produtos" />">Produtos</a></li>
					<li><a href="<c:url value="/carrinho" />">Carrinho${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Bem vindo!</h1>
			<p>Esse site é o projeto final desenvolvido no curso de SpringMVC
				da Alura. Diversas funcionalidades do framework são utilizadas.
				Sinta-se a vontade para navegar.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="<c:url value="/produtos" />"
					role="button">Comece &raquo;</a>
			</p>
		</div>
	</div>
</body>
</html>