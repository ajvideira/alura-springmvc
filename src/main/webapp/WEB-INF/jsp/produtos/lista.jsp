<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produtos -> Form</title>
</head>
<body>
	
	<h1>Lista de Produtos</h1>

	<c:if test="${mensagem != null}">
		<h4>${mensagem}</h4>
	</c:if>

	<table>
		<tr>
			<th>Título</th>
			<th>Descrição</th>
			<th>Páginas</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${produtos}" var="produto" varStatus="status">
			<tr>
				<td>${produto.titulo}</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
				<td><a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">Visualizar</a></td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>