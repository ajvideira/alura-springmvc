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
					<li><a href="<c:url value="/" />">Home</a></li>
					<li><a href="<c:url value="/produtos" />">Produtos</a></li>
					<li class="active"><a href="<c:url value="/carrinho" />">Carrinho${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Carrinho de Compras</h1>
		</div>
	</div>

	<div class="container">
		<c:if test="${mensagem != null}">
			<div class="row">
				<div class="alert alert-${tipoMensagem} alert-dismissible fade in"
					role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					${mensagem}
				</div>
			</div>
		</c:if>
	
		<table class="table">
			<thead>
				<tr>
					<th width="10%"></th>
					<th width="50%">Item</th>
					<th width="15%">Preço Unitário</th>
					<th width="13%">Quantidade</th>
					<th width="7%">Total</th>
					<th width="5%"></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${carrinhoCompras.itens}" var="item">
				<tr>
					<td><img src="${contextPath+=item.produto.imagemPath}" class="img-responsive" /></td>
					<td class="text-center">${item.produto.titulo} (${item.tipoPreco})</td>
					<td class="numeric-cell"><fmt:formatNumber type="currency" value="${item.preco}" /></td>
					<td class="quantity-input-cell">
						<form action="<c:url value="/carrinho/update-amount" />" method="post">
							<input type="hidden" name="produtoId" value="${item.produto.id}" />
							<input type="hidden" name="tipoPreco" value="${item.tipoPreco}" />
							<div class="input-group">
						    	<input class="form-control" type="number" min="1" name="quantidade" value="${carrinhoCompras.getQuantidade(item)}" />
						      	<span class="input-group-btn">
						        	<button class="btn btn-success" title="Atualizar"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></button>
						      	</span>
						    </div>
						</form>
					</td>
					<td class="numeric-cell"><fmt:formatNumber type="currency" value="${carrinhoCompras.getTotal(item)}" /></td>
					<td class="remove-item">
						<form action="<c:url value="/carrinho/remove" />" method="post">
							<input type="hidden" name="produtoId" value="${item.produto.id}" />
							<input type="hidden" name="tipoPreco" value="${item.tipoPreco}" />
				 			<button class="btn btn-danger" title="Excluir">Remover</button>
						</form>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${carrinhoCompras.itens == null || fn:length(carrinhoCompras.itens) == 0}">
				<tr>
					<td colspan="6" class="text-center">Nenhum produto disponível no carrinho.</td>
				</tr>
			</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<c:if test="${carrinhoCompras.itens != null && fn:length(carrinhoCompras.itens) > 0}">
							<form action="<c:url value="/pagamento/finalizar" />">
								<button class="btn btn-primary" title="Finalizar Compra">Finalizar Compra</button>
							</form>
						</c:if>
					</td>
					<td><c:if test="${carrinhoCompras.itens != null && fn:length(carrinhoCompras.itens) > 0}">
						<fmt:formatNumber type="currency" value="${carrinhoCompras.total}" />
					</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>