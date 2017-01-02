<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="produtos" showMenu="true">
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
						<form:label path="produto.titulo" title="Título"
							cssClass="control-label">Título</form:label>
						<form:input path="produto.titulo" cssClass="form-control"
							placeholder="Informe o Título" />
						<form:errors path="produto.titulo" cssClass="help-block" />
					</div>
				</s:bind>

				<s:bind path="produto.paginas">
					<div class="form-group${status.error ? ' has-error' : ''} col-md-3">
						<form:label path="produto.paginas" title="Páginas"
							cssClass="control-label">Páginas</form:label>
						<form:input path="produto.paginas" cssClass="form-control"
							placeholder="Informe o Número de Páginas" />
						<form:errors path="produto.paginas" cssClass="help-block" />
					</div>
				</s:bind>

				<s:bind path="produto.dataLancamento">
					<div class="form-group${status.error ? ' has-error' : ''} col-md-3">
						<form:label path="produto.dataLancamento"
							title="Data de Lançamento" cssClass="control-label">Data de Lançamento</form:label>
						<form:input path="produto.dataLancamento" cssClass="form-control"
							placeholder="Informe a Data de Lançamento" />
						<form:errors path="produto.dataLancamento" cssClass="help-block" />
					</div>
				</s:bind>

			</div>

			<s:bind path="produto.descricao">
				<div class="form-group${status.error ? ' has-error' : ''}">
					<form:label path="produto.descricao" title="Descrição"
						cssClass="control-label">Descrição</form:label>
					<form:textarea path="produto.descricao" rows="10" cols="30"
						cssClass="form-control" placeholder="Informe a Descrição" />
					<form:errors path="produto.descricao" cssClass="help-block" />
				</div>
			</s:bind>

			<div class="row">
				<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
					<div class="form-group col-md-4">
						<form:label path="produto.precos[${status.index}].valor"
							title="${tipoPreco}" cssClass="control-label">${tipoPreco}</form:label>
						<form:input path="produto.precos[${status.index}].valor"
							cssClass="form-control" placeholder="Informe o valor" />
						<form:hidden path="produto.precos[${status.index}].tipo"
							value="${tipoPreco}" />
					</div>
				</c:forEach>
			</div>

			<div class="form-group">
				<label title="Imagem" class="control-label">Imagem</label> <input
					type="file" name="imagem" class="form-control" />
			</div>

			<div class="form-group">
				<button class="btn btn-primary">Enviar</button>
			</div>
		</form>
	</div>
</tags:pageTemplate>