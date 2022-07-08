<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.bookmyseat.entity.User"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link href="resources/css/custom.css" rel="stylesheet">
<title>SelectCity</title>
<style type="text/css">
body{
	background: #ddd;
	color:#495057;
}
#searching input {
    width: 100%;
    height: 35px;
    padding: 10px;
    box-sizing: border-box;
    border-radius: 7px;
    border: 0;
    background: #eee;
    font-size: 14px;
    transition: all 0.5s;
}
#searching input:hover {
}
#searching input:focus {
    outline: none;
}
.searchBox{
	padding: 15px;
	background:#ccc;
}
.grid-box{
	padding: 20px;
}
.cityUl{
	list-style-type: none;
	
	background-color: white;
	width: 100%;
}
.cityUl > li{
	margin-left: -23px;
	padding: 5px;
	border-bottom: 1px solid #ccc;
	cursor: pointer;
}
.cityUl > li:last-child{
	border-bottom: none;
}
.citydiv{
	background: white;
	width: 100%;
}
</style>
</head>
<%
	User userdata = (User) session.getAttribute("LoginUser");
	String profileImg = (String) session.getAttribute("profile");
	pageContext.setAttribute("user", userdata);
%>	
<body>
<!-- 	popupbox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
  		<div class="modal-content">
    		<span class="close" onclick="closePopup('Cinemas');">&times;</span>
    		<p class="msg">Some text in the Modal..</p>
    		<div id="modal2" class="modal-content2 ">
    			<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button><button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('Cinemas');">Cancel</button>
    		</div>  
  		</div>
	</div>
	<!-- Insert-Update-popup -->
	<div id="InUpmyModal" class="InUpmodal">
		<!-- Modal content -->
  		<div class="InUpmodal-content" style="margin-top: -50px">
    		<span class="InUpclose" onclick="InUpclosePopup('SelectCity');">&times;</span>
    		<p class="InUpmsg">Reset Password</p>
    		<hr>
    		<div id="InUpmodal2" class="InUpmodal-content2">
    			<form action="" method="post">
					<div class="card-body row">
						<div class="col-12">
							<div class="mb-3">
								<label class="form-label">New password</label> <span class="errormsg"><c:if test="${not empty validErrorCinemaName}"><c:out value ="${validErrorCinemaName}"/></c:if></span>
 								<input type="hidden" value='<c:choose><c:when test = '${not empty afterCinemaData.getCinemaId()}'><c:out value ='${afterCinemaData.getCinemaId()}'/></c:when></c:choose>' name = "txtUserId" class="txtUserId"> 
								<input type="password" id="txtPwd" name="txtPwd" class="form-control" placeholder="Enter password...">
							</div>
							<div class="mb-3">
								<label class="form-label">Re-enter new password</label> <span class="errormsg"><c:if test="${not empty validErrorCinemaName}"><c:out value ="${validErrorCinemaName}"/></c:if></span>
 								<input type="hidden" value='<c:choose><c:when test = '${not empty afterCinemaData.getCinemaId()}'><c:out value ='${afterCinemaData.getCinemaId()}'/></c:when><c:otherwise>${cinemaDataById.getCinemasID()}</c:otherwise></c:choose>' name = "txtCinemaId" class="txtCinemaId"> 
								<input type="password" id="txtRePwd" name="txtRePwd" class="form-control" placeholder="Re-enter password...">
							</div>
						</div>
						<div class="col-12" style="margin-top: 15px;">
							<div class="md-3">
								<input type="submit" value="Change" class="btn btn-success btnAdd">
							</div>
						</div>
					</div>
				</form>
    		</div>  
  		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
    		<div class="col searchBox">
      			<form name="search" id="searching" method="post" action="">
        			<input class="gSearch" type="search" placeholder="Search for your city" onfocus="this.placeholder=''" onblur="this.placeholder='Search for your city'" name="search">
        		</form>
    		</div>
  		</div>
<!--   		<div class="row"> -->
<!--   			<div class="col grid-box"> -->
<!--   				<h5 class="h5">POPULAR <strong>CITIES</strong></h5> -->
<!--   				<div> -->
<!--   				</div> -->
<!--   			</div> -->
<!--   		</div> -->
  		<div class="row">
  			<div class="col grid-box">
  				<h5 class="h5">OTHER <strong>CITIES</strong></h5>
  			</div>
  			<div class="citydiv"> 
  				<ul class="cityUl">
  					<c:forEach var="city" items="${Citydata}">
  						<c:forEach var="state" items="${Statedata}">
  							<c:if test="${city.getStateId() eq state.getStateId()}">
  								<li onclick="selectCityHandle(${city.getCityId()});">${city.getCityName()}, ${state.getStateName()}</li>
  							</c:if>
  						</c:forEach>
  					</c:forEach>
  				</ul>
  			</div>
  		</div>
	</div>
</body>
<script src="assets/js/bootstrap.min.js"></script>
<script src="resources/js/custom.js"></script>
<script src="resources/js/action.js"></script>
<script src="resources/js/movies.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	<c:if test="${user.getCreatedTime() eq user.getUpdatedTime()}">
		var InUpmodal = document.getElementById("InUpmyModal");
		InUpmodal.style.display = "block";	
		$(".wrapper").addClass("blur");
	</c:if>
});
	function selectCityHandle(id){
		window.location.href="Home?cityId="+id;
	}
</script>
</html>
