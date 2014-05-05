<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="main" style="padding-bottom:90px;position:relative;min-height:100%;">

	<nav class="navbar navbar-inverse" role="navigation">
	
		<div class="container">
	
			<div class="navbar-header">
			    <a class="navbar-brand" href="<%= request.getContextPath() %>/">SupCarDealer</a>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			    <ul class="nav navbar-nav">
			      <li><a href="<%= request.getContextPath() %>/">Accueil</a></li>
			      <li><a href="<%= request.getContextPath() %>/cars">Les voitures</a></li>
			      <c:choose>
					<c:when test="${sessionScope.email == null}">
						<li><a href="<%= request.getContextPath() %>/login">Se connecter</a></li>
						<li><a href="<%= request.getContextPath() %>/register">S'enregistrer</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<%= request.getContextPath() %>/logged/addCar">Mettre en location</a></li>
						<li><a href="<%= request.getContextPath() %>/logged/profile">Mon profil</a></li>
						<li><a href="<%= request.getContextPath() %>/logout">Se d&eacute;connecter</a></li>
					</c:otherwise>
				</c:choose>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			    	<li><a href="<%= request.getContextPath() %>/about.jsp">About</a></li>
			    </ul>
		  </div>
	  
	  </div>
	
	</nav>