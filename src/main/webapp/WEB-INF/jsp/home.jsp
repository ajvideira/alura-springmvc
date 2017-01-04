<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="home" showMenu="true">

	<jsp:attribute name="extraScripts">
		<script>
			console.log("extra-scripts");
		</script>
	</jsp:attribute>
	<jsp:body>
		<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Bem vindo!</h1>
			<p>Esse site é o projeto final desenvolvido no curso de SpringMVC
				da Alura. Diversas funcionalidades do framework são utilizadas.
				Sinta-se a vontade para navegar.</p>
		</div>
	</div>
	<div class="container">
		<div class=row>
			<div class="col-md-12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="10%">#</th>
							<th width="25%">Título</th>
							<th width="40%">Descrição</th>
							<th width="5%">Data Lançamento</th>
							<th width="5%">Páginas</th>
							<th width="18%">Preços</th>
							<th width="2%">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${produtos == null || fn:length(produtos) == 0}">
							<tr>
								<td colspan="7">Nenhum produto cadastrado</td>
							</tr>
						</c:if>
						<c:forEach items="${produtos}" var="produto" varStatus="status">
							<tr>
								<td><img src="${contextPath+=produto.imagemPath}"
										class="img-responsive" /></td>
								<td>${produto.titulo}</td>
								<td>${produto.descricao}</td>
								<td><fmt:formatDate value="${produto.dataLancamento.time}"
											pattern="dd/MM/yyyy" /></td>
								<td>${produto.paginas}</td>
								<td><c:forEach items="${produto.precos}" var="preco">
										<span>${preco.tipo}: <fmt:formatNumber
													value="${preco.valor}" type="currency" /></span>
										<br />
									</c:forEach></td>
								<td><a class="btn btn-primary btn-lg"
										href="<c:url value="/produtos/detalhe/${produto.id}" />"
										role="button">Visualizar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</jsp:body>
</tags:pageTemplate>