<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.bookmyseat.utility.Constant"%>
<%@page import="java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="resources/img/icons/icon-48x48.png" />
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />

	<title>Screen</title>
	<link href="resources/css/app.css" rel="stylesheet">
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="resources/css/cinemaAdmin.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

<body>
<!-- 	popupbox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
  		<div class="modal-content">
    		<span class="close" onclick="closePopupInup('ManageScreen');">&times;</span>
    		<p class="msg">Some text in the Modal..</p>
    		<div id="modal2" class="modal-content2 ">
    			<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button><button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('Cinemas');">Cancel</button>
    		</div>  
  		</div>
	</div>
<!-- Add type popupbox -->
	<div id="myModalCastCrew" class="myModalCastCrew">
		<!-- Modal content -->
  		<div class="modal-contentScreen">
    		<span class="closeCastCrew" onclick="closePopupAddSeat();">&times;</span>
    		<p class="msgCastCrew">Add Seat Type</p>
    			<div class="row">
    				<div class="col-6">
			    		<div class="mb-3">
							<label class="form-label">Seat type</label> <span id="errorMsgSeatType" class="errormsg"></span>
			 				<input type="text" id="txtSeatType" name="txtSeatType" class="form-control" placeholder="Enter seat type...">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label class="form-label">No of row</label> <span id="errorMsgRow" class="errormsg"></span>
			 				<input type="text" id="txtNoOfRow" name="txtNoOfRow" class="form-control" placeholder="Enter no of row in seat type...">
						</div>
					</div>
				</div>
    		<div id="modal2CastCrew" class="modal-content2CastCrew">
    			<button style="width:100%" type="button" class="yesBtnCC btn btn-success" id="addType">Add</button>
    		</div>
  		</div>
	</div>
<!-- Insert-Update-popup -->
	<div id="InUpmyModal" class="InUpmodal">
		<!-- Modal content -->
  		<div class="InUpmodal-contentScreen">
    		<span class="InUpclose" onclick="closePopup('ManageScreen');">&times;</span>
    		<p class="InUpmsg">Insert Screen</p>
    		<hr>
    		<div id="InUpmodal2" class="InUpmodal-content2Movie ">
				 <form id="regForm" action="InsertScreenServlet" method="post">
				  <!-- One "tab" for each step in the form: -->
				  <div class="tab">
				  	<div class="mb-3">
						<label class="form-label">Screen name</label> <span id="errorMsgScreenName" class="errormsg"><c:if test="${not empty validErrorScreenName}"><c:out value ="${validErrorScreenName}"/></c:if></span>
						<input type="text" id="txtScreenName" name="txtScreenName" class="form-control" placeholder="Enter screen name..." value="<c:choose><c:when test = '${not empty afterScreenData.getScreenName()}'><c:out value ='${afterScreenData.getScreenName()}'/></c:when></c:choose>">
					</div>
					<div class="mb-3">
						<label class="form-label">Screen Seat Type</label>&nbsp;&nbsp;<span style="float: right;cursor: pointer;" onclick="addCastCrew();">Add new&nbsp;<i class="align-middle" data-feather="plus-square" onclick=""></i></span><span id="errorMsgMovieName" class="errormsg"><c:if test="${not empty validErrorScreenType}"><c:out value ="${validErrorScreenType}"/></c:if></span>
				  		<ul class="addSeatType" id="addSeatType">
				  			<c:choose>
								<c:when test="${not empty afterSeatTypeData.getSeatType()}">
									<c:forEach var = "i" begin = "0" end = "${fn:length(afterSeatTypeData.getSeatType())-1}">
										<div id="${afterSeatTypeData.getSeatType()[i]}">
											<input type="hidden" id="txtSeatType" name="txtSeatType" value="${afterSeatTypeData.getSeatType()[i]}">
											<input type="hidden" id="txtnoOfRow" name="txtnoOfRow" value="${afterSeatTypeData.getNoOfRow()[i]}">
											<li>${afterSeatTypeData.getSeatType()[i]},${afterSeatTypeData.getNoOfRow()[i]}<span class="close" onclick="removeSeatType('${afterSeatTypeData.getSeatType()[i]}');">&times;</span></li>
										</div>
									</c:forEach>
								</c:when>
							</c:choose>
				  		</ul>
				  	</div>
				  </div>
				  <br>
				  <div class="mb-3">
				  	<input style="width: 100%" type="submit" id="nextBtn" value="Add" class="btn btn-success">
				  </div>
				</form>
    		</div>  
  		</div>
	</div>
	<div class="wrapper">
		<nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="">
          			<span class="align-middle">Inox VR Mall</span>
      			</a>
				<ul class="sidebar-nav">
					<li class="sidebar-item ">
						<a class="sidebar-link" href="CinemaAdminDashboard">
              				<i class="align-middle " data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
            			</a>
				 	</li>
					<li class="sidebar-item active">
						<a class="sidebar-link" href="ManageScreen">
              				<i class="align-middle elementToFadeInAndOut" data-feather="airplay"></i> <span class="align-middle">Screen</span>
            			</a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="ManageShow">
              				<i class="align-middle" data-feather="video"></i> <span class="align-middle">Show</span>
            			</a>
					</li>
				</ul>
			</div>
		</nav>
		
		<div class="main">
			<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle js-sidebar-toggle">
          			<i class="hamburger align-self-center"></i>
        		</a>	
	        	<div class="navbar-collapse collapse">
					<ul class="navbar-nav navbar-align">
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown">
	                			<i class="align-middle" data-feather="settings"></i>
	              			</a>
	
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
	               	 			<img src="resources/img/avatars/user.png" class="avatar img-fluid rounded me-1" alt="Charles Hall" /> <span class="text-dark">Charles Hall</span>
	              			</a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="pages-profile.html"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
								<a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="pie-chart"></i> Analytics</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="help-circle"></i> Help Center</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Log out</a>
							</div>
						</li>
					</ul>
				</div>		
			</nav>	
			<main class="content">
				<div class="container-fluid p-0">
					<h1 class="h3 mb-3"><strong>Manage</strong> Screen</h1>
					<div>
						<ul class="dataviewtop">
							<li>
								<button class="btn btn-secondary d-inline" onclick="addNewCC();">Add <i class="align-middle" data-feather="plus"></i></button>
							</li>
						</ul>
					</div>
					<br><br>
					<div class="my-0" style=" display: flex;">
						<c:set var="i" value="0"></c:set>
						<c:forEach var="screen" items="${ScreenData}">
							<div class="screenBox" onclick="viewScreen(${screen.getScreenID()});">
								<img class="screenImg" src="resources/img/defaultImg/screen.png">
								<h4 style="color:#545454;text-align:center;margin-top: 5px;">${screen.getScreenName()}</h4>
									<c:if test="${chkLayout[i]==0}">
										<span class="callout">Add Layout</span>
									</c:if>
							</div>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="resources/js/app.js"></script>
	<script src="resources/js/custom.js"></script>
	<script src="resources/js/cinemaAdmin.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script>
      feather.replace();
    </script>
    <script type="text/javascript">
    $(document).ready(function(){
    	 $(".dropdown-toggle").on('click',function(){
 	    	$(".dropdown-menu").toggle(100);		
 	    });
    	//hide error msg and remove error after 3sec page load
    	window.setTimeout(function(){
    		$(".errormsg").fadeOut();
    	}, 3000);
    	
    	function isNumber(n) {
    		return !isNaN(parseFloat(n)) && !isNaN(n - 0);
    	}
		$("#addType").on('click',function(){

			var seatType = $( "#txtSeatType" ).val();
    		var noOfRow = $( "#txtNoOfRow" ).val();
    		if(typeof(seatType) !== "undefined" && seatType){
    			if(typeof(noOfRow) !== "undefined" && noOfRow){
    				if(isNumber(noOfRow)){
    					$("#addSeatType").append('<div id="'+seatType+'"><input type="hidden" id="txtSeatType" name="txtSeatType" value="'+seatType+'"><input type="hidden" id="txtnoOfRow" name="txtnoOfRow" value="'+noOfRow+'"><li>'+seatType+','+noOfRow+'<span class="close" onclick="removeSeatType(\''+seatType+'\');">&times;</span></li></div>');
    					$( "#txtSeatType" ).val("");
    					$( "#txtNoOfRow" ).val("");
    					closePopupAddSeat();
    				}else{
    					$("#errorMsgRow").show();
    					window.setTimeout(function(){
    			    		$(".errormsg").fadeOut();
    			    	}, 3000);
            			$("#errorMsgRow").text("Please enter number only!");
    				}
    			}else{
    				$("#errorMsgRow").show();
    				window.setTimeout(function(){
    		    		$(".errormsg").fadeOut();
    		    	}, 3000);
        			$("#errorMsgRow").text("Please enter no of row!");
    			}
    		}else
    		{
    			$("#errorMsgSeatType").show();
    			window.setTimeout(function(){
    	    		$(".errormsg").fadeOut();
    	    	}, 3000);
    			$("#errorMsgSeatType").text("Please enter seat type!");
    		}	  
	    });
		
		var msg = '${errortext}';
		<c:if test="${Constant.ERROR_OCCURS eq Constant.ERROR_OCCURS_YES}">
			modal.style.display = "block";
			$(".wrapper").addClass("blur");
			p.innerHTML = msg;
		</c:if>
		<c:if test="${Constant.ERROR_OCCURS eq Constant.ERROR_OCCURS_NO}">
			modal.style.display = "block";
			$(".wrapper").addClass("blur");
			p.innerHTML = msg;
		</c:if>

    });
   
  	//Display Error msg 
		<c:if test="${validErrorScreenInsert eq Constant.ERROR_OCCURS_YES}">
			window.history.replaceState({}, '', 'ManageScreen');
			addNewCC();
		</c:if>
    </script>
</body>
</html>