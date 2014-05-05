<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Mon profil</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/coverflow.css" />
		<script src="${pageContext.request.contextPath}/js/coverflow.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sticky-footer.css" />
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<style>
			table td:first-child{text-align:right;}
			table td:last-child{text-align:left;padding-left:30px;font-weight:bold;}
		</style>
	</head>
	<body>
		<%@ include file="../template/navbar.jsp" %>
		<div class="container">
			<div class="page-header">
				<h1>Informations de mon profil</h1>
			</div>
			<table>
				<tr>
					<td>Prénom</td>
					<td>${user.firstname}</td>
				</tr>
				<tr>
					<td>Nom</td>
					<td>${user.lastname}</td>
				</tr>
				<tr>
					<td>E-mail</td>
					<td>${user.email}</td>
				</tr>
			</table>
			<a href="<%= request.getContextPath() %>/logged/profile/edit"><button type="button" class="btn btn-primary" style="margin-top:10px;">Editer mon profil</button></a>
		</div>
		<%@ include file="../template/footer.jsp" %>	
	</body>
</html>