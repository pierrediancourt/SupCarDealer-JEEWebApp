<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>S'enregistrer</title>
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
				<h1>S'enregistrer</h1>
			</div>
			
			<form action="register" method="POST">
				<div class="input-group">
					<span class="input-group-addon">E-mail</span>
				  	<input type="email" autofocus="autofocus" name="email" id="email" class="form-control" placeholder="E-mail"
						<c:if test="${requestScope.email != null}">
							value="<%= request.getAttribute("email") %>"
						</c:if> 
					>
				</div>
				<div class="input-group">
				  	<span class="input-group-addon">Prénom</span>
				  	<input type="text" class="form-control" placeholder="Prénom" name="firstname" required>
				</div>
				<div class="input-group">
				  	<span class="input-group-addon">Nom</span>
				  	<input type="text" class="form-control" placeholder="Nom" name="lastname" required>
				</div>
				<div class="input-group">
				  	<span class="input-group-addon">Mot de passe</span>
				  	<input type="password" class="form-control" placeholder="Mot de passe" name="password1" required>
				</div>
				<div class="input-group">
				  	<span class="input-group-addon">Mot de passe</span>
				  	<input type="password" class="form-control" placeholder="Répéter le mot de passe" name="password2" required>
				</div>
				<input type="submit" value="S'inscrire" class="btn btn-success btn-lg" style="margin-top:10px;">
			</form>
			
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger"><%= request.getAttribute("errorMsg") %></div>
			</c:if>
		</div>
		<%@ include file="template/footer.jsp" %>			
	</body>
</html>