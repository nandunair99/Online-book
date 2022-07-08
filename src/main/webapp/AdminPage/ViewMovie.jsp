<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.bookmyseat.entity.User"%>
<%
	User user = (User) session.getAttribute("LoginUser");
	pageContext.setAttribute("user", user);
%>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />
	<title>View</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/icons/icon-48x48.png" />
	<link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/customm.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body style="margin: 0px;height: 100%">
	<div class="wrapper">
		<div class="main">
			<div class="bannerBox" style="background-image: url(data:image/png;base64,${imgBannerById}); background-repeat: no-repeat; background-size: cover ">
				<div class="layer"> 
    			</div>	
    			<div class="movieData row">
    				<div class="col-3">
    					<img class="posterImg" src="data:image/png;base64,${imgPosterById}">
    				</div>
    				<div class="col-9 dataContains">
    					<c:choose>
							<c:when test="${movieDataById.getStatus() == 0}">
							  	<span class="badge bg-success">Active</span>
							</c:when>
				   		    <c:when test="${movieDataById.getStatus() == 1}">
							    <span class="badge bg-warning">Inactive</span>
		           			</c:when>
							<c:otherwise>
								<span class="badge bg-primary">Upcoming</span>
							</c:otherwise>
						</c:choose>	
						<br>
    					<h1>${movieDataById.getMovieTitle()}</h1>
    					<br>
    					<h5>Releasing on <fmt:formatDate dateStyle = "long" value = "${movieDataById.getReleaseDate()}" /></h5>
    					<br>
    					<h6>
    						<c:forEach var="language" items="${languageDataById}">
								<span>${language.getLanguage()}</span>
							</c:forEach>
						</h6>
						<br>
						<h5>
							<c:forEach var="genre" items="${genreDataById}">
								&#8226; ${genre.getGenre()}
							</c:forEach>
						</h5>
						<br>
						<h5>
							Duration: ${movieDataById.getDuration()} hrs &#8226; ${movieDataById.getCensorRating()}
						</h5>
    				</div>
    			</div>
			</div>
			<div class="container-fluid movieDes">
				<h1 class="h3 mb-3">About the <strong>Movie</strong></h1>
				<p>${movieDataById.getDescription()}</p>
			</div>
			<div class="container-fluid movieDes">
				<h1 class="h3 mb-3"><strong>Movie</strong> Cast</h1>
				<div style=" display: flex;">
					<c:set var="i" value="0" scope="page" />
					<c:forEach var="castCrew" items="${castCrewDataById}">
						<c:if test="${castCrew.getType()==0}">
							<div style="width:min-content;margin:0px 5px 0px 5px">
								<img class="imgPoster" style="object-fit:  cover;" src="data:image/png;base64,${imgCastCrewById.get(i)}">
								<h4 style="color:black;text-align:center;margin-top: 5px;">${castCrew.getCastCrewName()}</h4>
								<h5 style="color:gray;text-align:center;margin-top: 5px;">Actor</h5>
							</div>
						</c:if>
						<c:set var="i" value="${i + 1}" scope="page" />
					</c:forEach>
				</div>
			</div>
			<div class="container-fluid movieDes">
				<h1 class="h3 mb-3"><strong>Movie</strong> Crew</h1>
				<div style=" display: flex;">
					<c:set var="i" value="0" scope="page" />
					<c:forEach var="castCrew" items="${castCrewDataById}">
						<c:if test="${castCrew.getType()==1}">
							<div style="width:min-content;margin:0px 5px 0px 5px">
								<img class="imgPoster" style="object-fit: cover;" src="data:image/png;base64,${imgCastCrewById.get(i)}">
								<h4 style="color:black;text-align:center;margin-top: 5px;">${castCrew.getCastCrewName()}</h4>
								<h5 style="color:gray;text-align:center;margin-top: 5px;">Director</h5>
							</div>
						</c:if>
						<c:set var="i" value="${i + 1}" scope="page" />
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//window.history.replaceState({}, '', 'ViewMovie');
</script>
</html>