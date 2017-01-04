<%@ tag language="java" pageEncoding="UTF-8"%>

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
				<li class="${pagina == 'home' ? 'active' : ''}"><a
					href="<c:url value="/" />">Home</a></li>
				<sec:authorize access="isAuthenticated()">
					<li class="${pagina == 'produtos' ? 'active' : ''}"><a
						href="<c:url value="/produtos" />"><fmt:message
								key="menu.produtos" /></a></li>
				</sec:authorize>
				<li class="${pagina == 'carrinho' ? 'active' : ''}"><a
					href="<c:url value="/carrinho" />"><fmt:message
								key="menu.carrinho" />${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
			</ul>
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="usuarioLogado" />
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">
						<s:message code="menu.bem_vindo" arguments="${usuarioLogado.nome}" />
					</a></li>
					<li><a href="?language=pt_BR">Português</a></li>
					<li><a href="?language=en_US">Inglês</a></li>
				</ul>
			</sec:authorize>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>