<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ajouter une voiture</title>
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
					<h1>Ajout d'une voiture &agrave; louer</h1>
				</div>
				
				<c:if test="${requestScope.infoMsg != null}">
					<div class="alert alert-info"><%= request.getAttribute("infoMsg") %></div>
				</c:if>
				
				<form method="post" action="addCar">
				
					<div class="input-group">
					  	<span class="input-group-addon">Modèle</span>
					  	<input type="text" class="form-control" placeholder="Modèle" name="name" required>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Année</span>
					  	<input type="text" class="form-control" placeholder="Année" name="year" required>
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">Catégorie</span>
						<select name="category" class="form-control">
							<c:forEach items="${categories}" var="cat">
								<option value="${cat.id}">${cat.name}</option>
							</c:forEach>
						</select>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Nombre de sièges</span>
					  	<input type="text" class="form-control" placeholder="Nombre de sièges" name="seats" required>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Stockage</span>
					  	<input type="text" class="form-control" placeholder="Stockage" name="baggage" required>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Nombre de portes</span>
					  	<input type="text" class="form-control" placeholder="Nombre de portes" name="doors" required>
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">Boite de vitesse</span>
						<div class="form-control">
							<div class="radio">
								<label>
									<input type="radio" name="gearbox" value="automatic" checked>
									Automatique
								</label>
							</div>
							<div class="radio">
								<label>
									<input type="radio" name="gearbox" value="manual">
									Manuelle
								</label>
							</div>
						</div>
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">Air conditionné</span>
						<div class="form-control">
							<div class="radio">
								<label>
									<input type="radio" name="conditionnalAir" value="true" checked>
									Oui
								</label>
							</div>
							<div class="radio">
								<label>
									<input type="radio" name="conditionnalAir" value="false">
									Non
								</label>
							</div>
						</div>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Kilomètres au compteur</span>
					  	<input type="text" class="form-control" placeholder="Kilomètres au compteur" name="kilometers" required>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Prix à la journée</span>
					  	<input type="text" class="form-control" placeholder="Prix à la journée" name="pricePerDay" required>
					</div>
					<br />
					<div class="input-group">
					  	<span class="input-group-addon">Image de votre voiture</span>
					  	<input type="file" class="form-control" name="imageUrl">
					</div>
					<br />
					<input type="submit" value="Enregistrer" class="btn btn-success btn-lg" style="margin-top:10px;">
				
				</form>
				
				<c:if test="${requestScope.errorMsg != null}">
					<div class="alert alert-danger" style="margin-top:10px;"><%= request.getAttribute("errorMsg") %></div>
				</c:if>
			</div>
		<%@ include file="../template/footer.jsp" %>	
	</body>
</html>