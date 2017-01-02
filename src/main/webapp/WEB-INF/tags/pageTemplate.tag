<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="pagina" %>
<%@ attribute name="showMenu" %>
<%@ attribute name="extraScripts" fragment="true" %>

<%@include file="/WEB-INF/jsp/import-taglibs.jsp" %>

<%@include file="/WEB-INF/jsp/cabecalho.jsp" %>

<c:if test="${showMenu}">
	<%@include file="/WEB-INF/jsp/menu.jsp" %>
</c:if>

<jsp:doBody></jsp:doBody>

<<jsp:invoke fragment="extraScripts" />

<%@include file="/WEB-INF/jsp/rodape.jsp" %>