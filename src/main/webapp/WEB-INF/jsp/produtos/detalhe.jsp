<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
					<li><a href="#">Home</a></li>
					<li class="active"><a href="<c:url value="/produtos" />">Produtos</a></li>
					<li><a href="<c:url value="/carrinho" />">Carrinho${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Detalhe do Produto </h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<img src="${contextPath+=produto.imagemPath}" class="img-responsive" />
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-6"><h1>${produto.titulo}</h1></div>
				</div>
				<div class="row">
					<div class="col-md-6"><p>${produto.descricao}</p></div>
				</div>
				<div class="row">
					<div class="col-md-6"><p><strong>Número de páginas: </strong><span>${produto.paginas}</span>
					</p></div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<p><strong>Data de publicação: </strong><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time}" /></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<form action="<c:url value="/carrinho/add" />" method="post">
							<input type="hidden" name="produtoId" value="${produto.id}" />
							<div class="form-group">
								<select class="form-control" name="tipoPreco">
									<c:forEach items="${produto.precos}" var="preco">								
										<option value="${preco.tipo}">${preco.tipo} => <fmt:formatNumber type="currency" value="${preco.valor}" /> </option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary form-control" title="Compre Agora" title="Compre Agora">Compre Agora</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>