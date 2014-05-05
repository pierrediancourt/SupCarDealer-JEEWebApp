<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Modifier une voiture</title>
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
				<h1>Edition des informations de la voiture</h1>
			</div>
			
			<c:if test="${requestScope.infoMsg != null}">
				<div class="alert alert-info"><%= request.getAttribute("infoMsg") %></div>
			</c:if>
			
			<form method="post" action="">
			
				<div class="input-group">
				  	<span class="input-group-addon">Modèle</span>
				  	<input type="text" class="form-control" placeholder="Modèle" name="name" value="${car.name}" required>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Année</span>
				  	<input type="text" class="form-control" placeholder="Année" name="year" value="${car.year}" required>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Catégorie</span>
					<select name="category" class="form-control">
						<c:forEach items="${categories}" var="cat">
							<option value="${cat.id}" <c:if test="${car.category.id} = ${cat.id}">selected</c:if>>${cat.name}</option>
						</c:forEach>
					</select>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Nombre de sièges</span>
				  	<input type="text" class="form-control" placeholder="Nombre de sièges" name="seats" value="${car.seats}" required>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Stockage</span>
				  	<input type="text" class="form-control" placeholder="Stockage" name="baggage" value="${car.baggage}" required>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Nombre de portes</span>
				  	<input type="text" class="form-control" placeholder="Nombre de portes" name="doors" value="${car.doors}" required>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon">Boite de vitesse</span>
					<div class="form-control">
						<div class="radio">
							<label>
								<input type="radio" name="gearbox" value="automatic" <c:if test="${car.gearbox == 'automatic'}">checked</c:if>>
								Automatique
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="gearbox" value="manual" <c:if test="${car.gearbox == 'manual'}">checked</c:if>>
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
								<input type="radio" name="conditionnalAir" value="true" <c:if test="${car.conditionalAir == true}">checked</c:if>>
								Oui
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="conditionnalAir" value="false" <c:if test="${car.conditionalAir == false}">checked</c:if>>
								Non
							</label>
						</div>
					</div>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Kilomètres au compteur</span>
				  	<input type="text" class="form-control" placeholder="Kilomètres au compteur" name="kilometers" value="${car.kilometers}" required>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Prix à la journée</span>
				  	<input type="text" class="form-control" placeholder="Prix à la journée" name="pricePerDay" value="${car.pricePerDay}" required>
				</div>
				<br />
				<div class="input-group">
				  	<span class="input-group-addon">Image de votre voiture</span>
				  	<input type="file" class="form-control" name="imageUrl">
				</div>
				<br />
				<input type="hidden" name="carId" value="${car.id}">
				<input type="submit" value="Enregistrer" class="btn btn-success btn-lg" style="margin-top:10px;">
			
			</form>
			
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger" style="margin-top:10px;"><%= request.getAttribute("errorMsg") %></div>
			</c:if>
		</div>
		<%@ include file="../template/footer.jsp" %>
	</body>
</html>