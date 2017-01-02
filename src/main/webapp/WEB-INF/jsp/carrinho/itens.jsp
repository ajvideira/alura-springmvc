<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="carrinho" showMenu="true">
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
						<td><img src="${contextPath+=item.produto.imagemPath}"
							class="img-responsive" /></td>
						<td class="text-center">${item.produto.titulo}
							(${item.tipoPreco})</td>
						<td class="numeric-cell"><fmt:formatNumber type="currency"
								value="${item.preco}" /></td>
						<td class="quantity-input-cell">
							<form action="<c:url value="/carrinho/update-amount" />"
								method="post">
								<input type="hidden" name="produtoId" value="${item.produto.id}" />
								<input type="hidden" name="tipoPreco" value="${item.tipoPreco}" />
								<div class="input-group">
									<input class="form-control" type="number" min="1"
										name="quantidade"
										value="${carrinhoCompras.getQuantidade(item)}" /> <span
										class="input-group-btn">
										<button class="btn btn-success" title="Atualizar">
											<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
										</button>
									</span>
								</div>
							</form>
						</td>
						<td class="numeric-cell"><fmt:formatNumber type="currency"
								value="${carrinhoCompras.getTotal(item)}" /></td>
						<td class="remove-item">
							<form action="<c:url value="/carrinho/remove" />" method="post">
								<input type="hidden" name="produtoId" value="${item.produto.id}" />
								<input type="hidden" name="tipoPreco" value="${item.tipoPreco}" />
								<button class="btn btn-danger" title="Excluir">Remover</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<c:if
					test="${carrinhoCompras.itens == null || fn:length(carrinhoCompras.itens) == 0}">
					<tr>
						<td colspan="6" class="text-center">Nenhum produto disponível
							no carrinho.</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3"><c:if
							test="${carrinhoCompras.itens != null && fn:length(carrinhoCompras.itens) > 0}">
							<form action="<c:url value="/pagamento/finalizar" />">
								<button class="btn btn-primary" title="Finalizar Compra">Finalizar
									Compra</button>
							</form>
						</c:if></td>
					<td><c:if
							test="${carrinhoCompras.itens != null && fn:length(carrinhoCompras.itens) > 0}">
							<fmt:formatNumber type="currency"
								value="${carrinhoCompras.total}" />
						</c:if></td>
				</tr>
			</tfoot>
		</table>
	</div>
</tags:pageTemplate>