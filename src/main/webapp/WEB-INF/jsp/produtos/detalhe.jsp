<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="produtos" showMenu="true">
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Detalhe do Produto</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<img src="${contextPath+=produto.imagemPath}" class="img-responsive" />
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-6">
						<h1>${produto.titulo}</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<p>${produto.descricao}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<p>
							<strong>Número de páginas: </strong><span>${produto.paginas}</span>
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<p>
							<strong>Data de publicação: </strong>
							<fmt:formatDate pattern="dd/MM/yyyy"
								value="${produto.dataLancamento.time}" />
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<form:form servletRelativeAction="/carrinho/add" method="post">
							<input type="hidden" name="produtoId" value="${produto.id}" />
							<div class="form-group">
								<select class="form-control" name="tipoPreco">
									<c:forEach items="${produto.precos}" var="preco">
										<option value="${preco.tipo}">${preco.tipo}=>
											<fmt:formatNumber type="currency" value="${preco.valor}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary form-control"
									title="Compre Agora" title="Compre Agora">Compre Agora</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</tags:pageTemplate>
