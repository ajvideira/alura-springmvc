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
				<li class="${pagina == 'home' ? 'active' : ''}"><a href="<c:url value="/" />">Home</a></li>
				<sec:authorize access="isAuthenticated()">
					<li class="${pagina == 'produtos' ? 'active' : ''}"><a href="<c:url value="/produtos" />">Produtos</a></li>
				</sec:authorize>
				<li class="${pagina == 'carrinho' ? 'active' : ''}"><a href="<c:url value="/carrinho" />">Carrinho${carrinhoCompras.quantidade>0 ? '('+=carrinhoCompras.quantidade+=')':''}</a></li>
			</ul>
			<sec:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Bem vindo <strong><sec:authentication
									property="principal.nome" /></strong></a></li>
				</ul>
			</sec:authorize>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>