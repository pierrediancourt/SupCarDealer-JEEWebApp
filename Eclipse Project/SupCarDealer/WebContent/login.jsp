<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Se connecter</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/coverflow.css" />
		<script src="${pageContext.request.contextPath}/js/coverflow.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sticky-footer.css" />
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</head>
	<body>
		<%@ include file="template/navbar.jsp" %>
		<div class="container">
			<div class="page-header">
				<h1>Se connecter</h1>
			</div>
			
			<form action="login" method="POST">
				<div class="input-group">
					<span class="input-group-addon">E-mail</span>
				  	<input type="email" autofocus="autofocus" name="email" id="email" class="form-control" placeholder="E-mail"
						<c:if test="${requestScope.email != null}">
							value="<%= request.getAttribute("email") %>"
						</c:if> 
					>
				</div>
				<div class="input-group">
				  	<span class="input-group-addon">Mot de passe</span>
				  	<input type="password" class="form-control" placeholder="Mot de passe" name="password">
				</div>
				<input type="submit" value="Se connecter" class="btn btn-success btn-lg" style="margin-top:10px;">
				<a href="<%= request.getContextPath() %>/register"><button type="button" class="btn btn-default btn-sm" style="margin-top:10px;">Pas encore inscrit ?</button></a>
			</form>
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger" style="margin-top:10px;"><%= request.getAttribute("errorMsg") %></div>
			</c:if>
		</div>
		<%@ include file="template/footer.jsp" %>			
	</body>
</html>