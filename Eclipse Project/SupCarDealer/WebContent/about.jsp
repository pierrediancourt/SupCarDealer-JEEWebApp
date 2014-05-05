<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>A propos</title>
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
				<h1>A propos</h1>
			</div>
			
			<div class="jumbotron">
				<p>Nous contacter : <br><br>
					Campus de Supinfo <br>
					59300 Valenciennes
				</p>
			</div>
		</div>
		<%@ include file="template/footer.jsp" %>	
	</body>
</html>