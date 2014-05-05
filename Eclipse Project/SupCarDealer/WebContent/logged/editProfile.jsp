<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.supinfo.supcardealer.globals.Globals" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Editer mon profil</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/coverflow.css" />
		<script src="${pageContext.request.contextPath}/js/coverflow.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sticky-footer.css" />
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</head>
	<body>
		<%@ include file="../template/navbar.jsp" %>
		<div class="container">
			<div class="page-header">
				<h1>Edition de mon profil</h1>
			</div>
			
			<c:if test="${requestScope.infoMsg != null}">
				<div class="alert alert-info"><%= request.getAttribute("infoMsg") %></div>
			</c:if>
			
			<div class="alert alert-warning">
				Note : Si vous ne souhaitez pas modifier votre mot de passe, veuillez laisser les 3 derniers champs vides.
			</div>
			<form action="edit" method="POST">
				<label for="email">Email : </label>
					<input type="email" autofocus="autofocus" name="email" id="email" value="${user.email}"
					required>
					<br>
				<label for="firstname">Pr&eacute;nom : </label>
					<input type="text" name="firstname" id="firstname" value="${user.firstname}">
					<br>
				<label for="lastname">Nom : </label>
					<input type="text" name="lastname" id="lastname" value="${user.lastname}">
					<br>
					<label for="username">Ancien mot de passe : </label>
					<input type="password" name="oldPassword" id="oldPassword">
					<br>
				<label for="username">Nouveau mot de passe : </label>
					<input type="password" name="newPassword1" id="newPassword1">
					<br>
				<label for="username">Confirmation : </label>
					<input type="password" name="newPassword2" id="newPassword2">
					<br>
				<input type="submit" value="Enregistrer" class="btn btn-success btn-lg" style="margin-top:10px;">
			</form>
			
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger" style="margin-top:10px;"><%= request.getAttribute("errorMsg") %></div>			
			</c:if>
		</div>
		<%@ include file="../template/footer.jsp" %>	
	</body>
</html>