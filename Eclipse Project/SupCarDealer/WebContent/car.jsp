<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>D&eacute;tails de la voiture</title>
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
				<h1>${car.name} <small>${car.category.name} (${car.year})</small></h1>
			</div>
			
			<h3>D&eacute;tail de la voiture</h3>
			
			<c:if test="${infoMsg != null}">
				<div class="alert alert-info"><%= request.getAttribute("infoMsg") %></div>
			</c:if>
			
			<!-- Tableau récapitulatif des infos de la voiture -->
			<c:choose>
				<c:when test="${empty car}">
					<p>Cette voiture n'existe pas.</p>
				</c:when>
				<c:otherwise>
					<div class="row">
						<div class="col-md-4">
					  		<img src="${pageContext.request.contextPath}/img/cars/${car.imageUrl}" style="width:100%;" />
					  	</div>
					  	<div class="col-md-8">
						<table class="table table-bordered table-stripped">
								<tbody>
									<tr><td>Année</td><td>${car.year}</td></tr>
									<tr><td>Catégorie</td><td>${car.category.name}</td></tr>
									<tr><td>Siège(s)</td><td>${car.seats}</td></tr>
									<tr><td>Porte(s)</td><td>${car.doors}</td></tr>
									<tr>
										<td>Air Conditionné</td>
										<c:choose>
											<c:when test="${car.conditionalAir == true}"><td>Oui</td></c:when>
											<c:otherwise><td>Non</td></c:otherwise>
										</c:choose>
									</tr>
									<tr><td>Bagages</td><td>${car.baggage}</td></tr>
									<tr><td>Kilomètres au compteur</td><td>${car.kilometers}</td></tr>
									<c:if test="${sessionScope.email != null}">
										<td>Propriétaire</td><td>${car.owner.firstname} ${car.owner.lastname}</td>
									</c:if>
								</tbody>
							</table>
							<h1 style="float: right;margin: 0 0 5px 0;"><span class="label label-success">${car.pricePerDay}€ par jour</span></h1>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger" style="margin-top:10px;"><%= request.getAttribute("errorMsg") %></div>
			</c:if>
			<hr> 
			<c:choose>
				<c:when test="${sessionScope.email != null}">
			<!-- Si un utilisateur est connecté... -->
					<form action="logged/rent" method="POST">
						<!-- Choisir la date de début et date de fin de la location -->
						<table>
							<tr>
								<th><label for="startDate">Date de d&eacute;but de la location : </label></th>
								<th><input type="date" name="startDate" required/></th>
							</tr>
							<tr>
								<th><label for="endDate">Date de fin de la location : </label></th>
								<th><input type="date" name="endDate" required/></th>
							</tr>
						</table>
						<p style="color: red;font-weight:bold;">Note : La date doit &ecirc;tre saisie au format jj/mm/aaaa</p>
						<!-- Bouton "Louer" qui ne s'affiche que si un utilisateur est connecté  -->
						<input type="hidden" value="${car.id}" name="id">
						<input type="submit" value="Louer" class="btn btn-success btn-lg" style="margin-top:10px;">
						<a href="<%= request.getContextPath() %>/logged/car/edit?id=${car.id}"><button type="button" class="btn btn-default btn-sm" style="margin-top:10px;">Modifier</button></a>
					</form>
				</c:when>
				<c:otherwise>
			<!-- Si aucun utilisateur n'est connecté... -->
					<a href="<%= request.getContextPath() %>/login"><button type="button" class="btn btn-primary">Louer d&egrave;s maintenant</button></a>
				</c:otherwise>
			</c:choose>
		</div>
		<%@ include file="template/footer.jsp" %>	
	</body>
</html>