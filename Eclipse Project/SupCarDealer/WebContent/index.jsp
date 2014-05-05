<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accueil</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/coverflow.css" />
		<script src="${pageContext.request.contextPath}/js/coverflow.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sticky-footer.css" />
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</head>
	<body>
		<%@ include file="template/navbar.jsp" %>
		<div class="container">
		
			<div class="jumbotron">
				<h1>Bienvenue !</h1>
				<p>Sur SupCarDealer, vous pouvez louer vos voitures de particulier à particulier.</p>
				<a href="<%= request.getContextPath() %>/cars"><button type="button" class="btn btn-primary">Voir l'incroyable choix de voitures</button></a>
			</div>
			
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
// 								window.open(link, '_blank');
								window.location.href = link; 
							}
						});
					});
				}
				reset();
			</script>
		</div>	
		<%@ include file="template/footer.jsp" %>
	</body>
</html>