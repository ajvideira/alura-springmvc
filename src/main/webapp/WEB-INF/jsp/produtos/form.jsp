<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produtos -> Form</title>
</head>
<body>

	<h1>Cadastro de Produtos</h1>
	<!-- //pageContext.request.contextPath -->
	<form action="<c:url value="/produtos" />" method="post" enctype="multipart/form-data">
		<div>
			<form:label path="produto.titulo" title="Título">Título</form:label>
			<form:input path="produto.titulo" />
			<form:errors path="produto.titulo" />
		</div>

		<div>
			<form:label path="produto.descricao" title="Descrição">Descrição</form:label>
			<form:textarea path="produto.descricao" rows="10" cols="30" />
			<form:errors path="produto.descricao" />
		</div>

		<div>
			<form:label path="produto.paginas" title="Páginas">Páginas</form:label>
			<form:input path="produto.paginas" />
			<form:errors path="produto.paginas" />
		</div>
		
		<div>
			<form:label path="produto.dataLancamento" title="Data de Lançamento">Data de Lançamento</form:label>
			<form:input path="produto.dataLancamento" />
			<form:errors path="produto.dataLancamento" />
		</div>

		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<form:label path="produto.precos[${status.index}].valor" title="${tipoPreco}">${tipoPreco}</form:label>
				<form:input path="produto.precos[${status.index}].valor" />
				<form:hidden path="produto.precos[${status.index}].tipo" value="${tipoPreco}" />
			</div>
		</c:forEach>

		<div>
			<label title="Imagem">Imagem</label>
			<input type="file" name="imagem" />
		</div>

		<div>
			<button>Enviar</button>
		</div>
	</form>
</body>
</html>