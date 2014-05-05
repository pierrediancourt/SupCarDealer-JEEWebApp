<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Voitures par cat&eacute;gories</title>
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
				<h1>Nos voitures</h1>
			</div>
			
			<c:if test="${requestScope.infoMsg != null}">
				<div class="alert alert-info"><%= request.getAttribute("infoMsg") %></div>
			</c:if>
			
			<!-- Checkbox pour choisir les catégories -->
			<p>Choisissez les cat&eacute;gories pour lesquelles vous voulez afficher les voitures :</p>
			<form action="cars" method="POST">
				<c:forEach items="${categories}" var="c">
					<c:set var="isSelected" scope="request" value="false"/>
					<c:forEach items="${selectedCategories}" var="sc">
						<c:if test="${c.id == sc}">
							<c:set var="isSelected" scope="request" value="true"/>
						</c:if>
					</c:forEach>
					<div class="input-group">
						<span class="input-group-addon">
					    	<input type="checkbox" name="categories" value="${c.id}" <c:if test="${isSelected}">checked</c:if>/>
					    </span>
					    <span class="form-control">${c.name}</span>
				    </div>
				</c:forEach>
				<input type="submit" value="Filtrer" class="btn btn-success" style="width:200px;margin:10px 0;" />
			</form>
		
			<c:choose>
				<c:when test="${carsdata == '[]'}">
					<p>Aucune voiture ne correspond à votre recherche. Veuillez modifier vos filtres.</p>
				</c:when>
				<c:otherwise>
					<div id="coverflow-container" class="coverflow" style="height:400px;"></div>
		
					<div class="row" style="margin-top:10px;">
						<div class="col-md-4">
						</div>
						<div class="col-md-4">
							<div class="btn-group btn-group-lg">
							  <a href="javascript:;" onclick="coverflow().prev();" class="btn btn-primary" style="width:180px;">Précédent</a>
							  <a href="javascript:;" onclick="coverflow().next();" class="btn btn-primary" style="width:180px;">Suivant</a>
							</div>
						</div>
						<div class="col-md-4">
						</div>
					</div>
					
					<script>
						function reset() {
							
							coverflow('coverflow-container').setup({
									width: '100%',
									height: '400px',
									item: 1,
									playlist: ${carsdata},
									coverwidth: 360,
									coverheight: 300,
									fixedsize: true,
									textoffset: 50
							}).on('ready', function() {
								this.on('click', function(index, link) {
									if (link) {
// 										window.open(link, '_blank');
										window.location.href = link; 
									}
								});
							});
						}
						reset();
					</script>
			
				</c:otherwise>
			</c:choose>
			<c:if test="${requestScope.errorMsg != null}">
				<div class="alert alert-danger"><%= request.getAttribute("errorMsg") %></div>
			</c:if>
		</div>
		<%@ include file="template/footer.jsp" %>	
	</body>
</html>