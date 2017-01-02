<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp"%>

<tags:pageTemplate pagina="login" showMenu="false">
	<div class="container">

		<form:form class="form-signin" servletRelativeAction="/login"
			method="post">
			<h2 class="form-signin-heading">Identifique-se aqui</h2>
			<label for="inputEmail" class="sr-only">E-mail</label>
			<input type="email" id="inputEmail" class="form-control"
				placeholder="Email address" required autofocus name="username" />
			<label for="inputPassword" class="sr-only">Senha</label>
			<input type="password" id="inputPassword" class="form-control"
				placeholder="Password" required name="password" />
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Lembrar meus dados
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
		</form:form>

	</div>
</tags:pageTemplate>