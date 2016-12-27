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
				<li><a class="active" href="<c:url value="/produtos" />">Produtos</a></li>
				<li><a href="<c:url value="/carrinho" />">Carrinho${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
			</ul>
		</div>
		<!--/.navbar-collapse -->
	</div>
</nav>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
	<div class="container">
		<h1>Cadastro de Produto</h1>
	</div>
</div>

<div class="container">


<form class="form-vertical" data-toggle="validator" role="form"
	action="<c:url value="/produtos" />" method="post"
	enctype="multipart/form-data">
	
	<div class="row">
		<s:bind path="produto.titulo">
			<div class="form-group${status.error ? ' has-error' : ''} col-md-6">
				<form:label path="produto.titulo" title="Título" cssClass="control-label">Título</form:label>
				<form:input path="produto.titulo" cssClass="form-control" placeholder="Informe o Título" />
				<form:errors path="produto.titulo" cssClass="help-block" />
			</div>
		</s:bind>
		
		<s:bind path="produto.paginas">
			<div class="form-group${status.error ? ' has-error' : ''} col-md-3">
				<form:label path="produto.paginas" title="Páginas" cssClass="control-label">Páginas</form:label>
				<form:input path="produto.paginas" cssClass="form-control" placeholder="Informe o Número de Páginas" />
				<form:errors path="produto.paginas" cssClass="help-block" />
			</div>
		</s:bind>
	
		<s:bind path="produto.dataLancamento">
			<div class="form-group${status.error ? ' has-error' : ''} col-md-3">
				<form:label path="produto.dataLancamento" title="Data de Lançamento" cssClass="control-label">Data de Lançamento</form:label>
				<form:input path="produto.dataLancamento" cssClass="form-control" placeholder="Informe a Data de Lançamento" />
				<form:errors path="produto.dataLancamento" cssClass="help-block" />
			</div>
		</s:bind>
	
	</div>

	<s:bind path="produto.descricao">
		<div class="form-group${status.error ? ' has-error' : ''}">
			<form:label path="produto.descricao" title="Descrição" cssClass="control-label">Descrição</form:label>
			<form:textarea path="produto.descricao" rows="10" cols="30" cssClass="form-control" placeholder="Informe a Descrição" />
			<form:errors path="produto.descricao" cssClass="help-block" />
		</div>
	</s:bind>

	<div class="row">
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div class="form-group col-md-4">
				<form:label path="produto.precos[${status.index}].valor"
					title="${tipoPreco}" cssClass="control-label">${tipoPreco}</form:label>
				<form:input path="produto.precos[${status.index}].valor" cssClass="form-control" placeholder="Informe o valor" />
				<form:hidden path="produto.precos[${status.index}].tipo"
					value="${tipoPreco}" />
			</div>
		</c:forEach>
	</div>

	<div class="form-group">
		<label title="Imagem" class="control-label">Imagem</label> 
		<input type="file" name="imagem" class="control-label" />
	</div>

	<div class="form-group">
		<button class="btn btn-primary">Enviar</button>
	</div>
</form>
</div>

</body>
</html>