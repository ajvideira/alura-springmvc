<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="produtos" showMenu="true">
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Lista de Produtos</h1>
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
		<div class=row>
			<div class="col-md-12">
				<table class="table table-striped">
					<thead>
						<tr>
							<td colspan="7"><a href="<c:url value="produtos/form" />"
								class="btn btn-success">Novo Produto</a></td>
						</tr>
						<tr>
							<th width="10%">#</th>
							<th width="25%">Título</th>
							<th width="40%">Descrição</th>
							<th width="5%">Data Lançamento</th>
							<th width="5%">Páginas</th>
							<th width="18%">Preços</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${produtos == null || fn:length(produtos) == 0}">
							<tr>
								<td colspan="6">Nenhum produto cadastrado</td>
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
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</tags:pageTemplate>
