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
	<link rel="shortcut icon" href="resources/img/icons/icon-48x48.png" />
	<link href="resources/css/app.css" rel="stylesheet">
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
<body style="margin: 0px;height: 100%">
	<div class="wrapper">
		<div class="main">
			<div class="bannerBoxCinema" style="background-image: url(resources/img/defaultImg/theatreBg.jpg);background-position: center;background-repeat: no-repeat;background-size: cover;">
				<br>
				<br>
				<h2>${cinemaDataById.getName()}</h2>
				<h4><i class="align-middle" data-feather="map-pin"></i> ${cinemaDataById.getAddressData().getAddressline1()} ,${cinemaDataById.getAddressData().getAddressline2()}, ${cinemaDataById.getAddressData().getLandmark()}, 
				<c:forEach var="city" items="${Citydata}">
					<c:forEach var = "i" begin = "0" end = "${Statedata.size()-1}">
          				<c:if test="${cinemaDataById.getAddressData().getCity_ID() eq city.getCityId() and Statedata.get(i).getStateId() eq city.getStateId()}">
          					${city.getCityName()}, ${Statedata.get(i).getStateName()}
          				</c:if>
          			</c:forEach>
				</c:forEach>
				- ${cinemaDataById.getAddressData().getPincode()}, India
				</h4>
				<c:set var="cinemaAF" value="${cinemaDataById.getFacilities()}"></c:set>
				<br>
				<h4>Available Facilities</h4>
				<h6>
				<%
					String str = (String) pageContext.getAttribute("cinemaAF");
					String[] af = str.split("[,]", 0);
					pageContext.setAttribute("af",af);
				%>
				<div style="display: flex;justify-content: center;">
					<c:forEach var="afdata" items="${af}">
						<div style="width:min-content;margin:0px 5px 0px 5px">
							<c:if test="${afdata eq 'Ticket Cancellation'}">
								<img style="width:34px;height: 34px;" src="resources/img/defaultImg/cancel-ticket.png"><br>
							</c:if>
							<c:if test="${afdata eq 'F&B'}">
								<img style="width:34px;height: 34px;" src="resources/img/defaultImg/welcome-drink.png"><br>
							</c:if>
							<c:if test="${afdata eq 'MTicket'}">
								<img style="width:34px;height: 34px;" src="resources/img/defaultImg/ticket.png"><br>
							</c:if>
							${afdata}
						</div>
					</c:forEach>
				</div>
				</h6>
			</div>
		</div>
	</div>
</body>
	<script src="https://unpkg.com/feather-icons"></script>
	<script>
      feather.replace()
    </script>
</html>