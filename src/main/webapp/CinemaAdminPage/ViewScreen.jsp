<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

	<title>View Screen</title>
	<link href="resources/css/app.css" rel="stylesheet">
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="resources/css/cinemaAdmin.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	<style>
		.alert {
		  padding: 10px;
		  background-color: #f44336;
		  color: white;
		  opacity: 1;
		  transition: opacity 0.6s;
		  margin-bottom: 15px;
		}

		.alert.info {
			background-color: #2196F3;
		}

		.closebtn {
		  margin-left: 15px;
		  color: white;
		  font-weight: bold;
		  float: right;
		  font-size: 22px;
		  line-height: 20px;
		  cursor: pointer;
		  transition: 0.3s;
		}
		
		.closebtn:hover {
		  color: black;
		}
	</style>
</head>
<body>
<!-- 	popupbox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
  		<div class="modal-content">
    		<span class="close" onclick="closePopupScreen('ViewScreen',${ScreenData.getScreenID()});">&times;</span>
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
			    			<input type="hidden" id="uptxtSeatTypeId" name="txtSeatTypeId">
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
	<div class="wrapper">
		<nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="AdminDash.jsp">
          			<span class="align-middle">Admin Panel</span>
        		</a>
				<ul class="sidebar-nav">
					<li class="sidebar-item ">
						<a class="sidebar-link" href="CinemaAdminDashboard">
              				<i class="align-middle " data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
            			</a>
					</li>
					<li class="sidebar-item active">
						<a class="sidebar-link" href="ManageScreen">
              				<i class="align-middle elementToFadeInAndOut" data-feather="airplay"></i> <span class="align-middle">Screen -> View Screen</span>
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
				<div class="container-fluid p-0" >
					<h1 class="h3 mb-3"><strong>${ScreenData.getScreenName()}</strong> Data</h1>
						<div class="col-6">
						<form action="UpdateScreen" method="post">
							<div class="mb-3">
								<input type="hidden" id="txtScreenId" name="txtScreenId" class="form-control" value="${ScreenData.getScreenID()}">
								<label class="form-label">Screen name</label> <span id="errorMsgScreenName" class="errormsg"><c:if test="${not empty validErrorScreenName}"><c:out value ="${validErrorScreenName}"/></c:if></span>
								<input type="text" id="txtScreenName" name="txtScreenName" class="form-control" placeholder="Enter screen name..." value="${ScreenData.getScreenName()}">
							</div>
							<div class="mb-3">
				  				<input type="submit" id="nextBtn" value="Update" class="btn btn-success">
				  			</div>
						</form>
						<%-- <div class="alert info">
 							 <span class="closebtn" >&times;</span>  
  							 <strong>Info!</strong> Click on the name of the seat type to update it.
						</div>
						<div class="mb-3 SeatType">
							<label class="form-label">Screen Seat Type</label>&nbsp;&nbsp;<span style="float: right;cursor: pointer;" class="" onclick="addSeatType('0',this);">Add new&nbsp;<i class="align-middle" data-feather="plus-square" onclick=""></i></span><span id="errorMsgMovieName" class="errormsg"><c:if test="${not empty validErrorScreenType}"><c:out value ="${validErrorScreenType}"/></c:if></span>
					  		<ul class="addSeatType" id="addSeatType">
								<c:forEach var = "i" begin = "0" end = "${ScreenSeatTypeData.size()-1}">
									<div class="seatTypeDiv" onclick="addSeatType('1',this);" id="${ScreenSeatTypeData[i].getSeatType()}">
										<input type="hidden" id="datatxtSeatTypeId" name="txtSeatTypeId" value="${ScreenSeatTypeData[i].getScreenSeatTypeID()}">
										<input type="hidden" id="datatxtSeatType" name="txtSeatType" value="${ScreenSeatTypeData[i].getSeatType()}">
										<input type="hidden" id="datatxtnoOfRow" name="txtnoOfRow" value="${ScreenSeatTypeData[i].getNoOfRow()}">
										<li>${ScreenSeatTypeData[i].getSeatType()},${ScreenSeatTypeData[i].getNoOfRow()}<span class="close" onclick="removeSeatType('${ScreenSeatTypeData[i].getSeatType()}');">&times;</span></li>
									</div>
								</c:forEach>
					  		</ul>
					  	</div> --%>
						  	
					  	</div>
					  	<div class="mb-3" style="float: none;display: inline-block;">
				  		</div>
					
					<h1 class="h3 mb-3">Seats<strong> Layout</strong></h1>
					<form action="InsertSeatLayout?screenId=${ ScreenData.getScreenID()}" method="Post">
						<div style="text-align: center;">
							<c:set var="rowid" value=""></c:set>
							<c:set var="chnum" value="65"></c:set>
							<c:set var="k" value="0"></c:set>
							<c:forEach var="s" items="${ScreenSeatTypeData}">
								<br>
								<input type="hidden" name="txtseatTypeId" value="${s.getScreenSeatTypeID()}">
								<h3 class="seatTypeName" id="seatTypeName" >${s.getSeatType()}</h3>
							 	<c:forEach var = "i" begin = "1" end = "${s.getNoOfRow()}">
									<c:set var="rowid" value="${chnum}"></c:set>
									<label style="height: 20px;width: 20px;">&#${rowid};</label>
									<c:set var="chnum" value="${chnum + 1 }"></c:set>
									<c:forEach var = "j" begin = "1" end = "30">
										<input id="seat" type="hidden" class="&#${rowid};" name="hiddenseat" value="&#${rowid};${j},${s.getScreenSeatTypeID()}">
											<c:choose>
												<c:when test="${SeatLayoutData.size() gt 0 && SeatLayoutData.get(k).getStatus() eq '1'}">
													<input id="seat" type="checkbox" class="&#${rowid};" name="seat" value="&#${rowid};${j},${s.getScreenSeatTypeID()}" checked="checked"><span class="label"><span style="color:white" class="seatNumber">${j}</span></span>
												</c:when>
												<c:otherwise>
													<input id="seat" type="checkbox" class="&#${rowid};" name="seat" value="&#${rowid};${j},${s.getScreenSeatTypeID()}"><span class="label"><span class="seatNumber">${j}</span></span>
												</c:otherwise>
											</c:choose>
											<c:set var="k" value="${k+1}"></c:set>
									</c:forEach>
									<input id="selectAll" type="checkbox" value="&#${rowid};" ><span onclick="checkAllSeat(this.previousSibling);" class="selectAll"></span>
									<br>
								</c:forEach>
							</c:forEach>
							<br>
							<input type="submit" id="nextBtn" value="Save" class="btn btn-success">
						</div>
					</form>
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
      feather.replace()
    </script>
    <script type="text/javascript">
    $(document).ready(function(){
    	$(".seatTypeName").on('click',function(){
    		var value = $(this).html();
    		var id = $(this).prev('input[name=txtseatTypeId]').val();
    		var editType = "<h3><input style='txt-align: center' class='txtseatTypeName' type='text' size='10' name='utype' value='"+ value +"'><span onClick='updateSeatType'><img style='width:20px;margin-left:10px;cursor:pointer' src='resources/img/defaultImg/check.png'></span><img onClick='window.location.href=window.location.href' style='width:17px;margin-left:10px;cursor:pointer' src='resources/img/defaultImg/close.png'></h3>";
    		$(this).replaceWith(editType);
    		$('.txtseatTypeName').focus();
    	});
    	function updateSeatType(){
    		alert();
    	}
    	
    	 $(".dropdown-toggle").on('click',function(){
 	    	$(".dropdown-menu").toggle(100);		
 	    });
    	var msg = '${errortext}';
		<%
			if(request.getAttribute(Constant.ERROR_OCCURS)==Constant.ERROR_OCCURS_YES)
			{%>
				modal.style.display = "block";
				$(".wrapper").addClass("blur");
				p.innerHTML = msg;
			<%}else if(request.getAttribute(Constant.ERROR_OCCURS)==Constant.ERROR_OCCURS_NO)
			{%>
				modal.style.display = "block";
				$(".wrapper").addClass("blur");
				p.innerHTML = msg;
			<%
			}
		%>
    	
    	
    	//hide error msg and remove error after 3sec page load
    	window.setTimeout(function(){
    		$(".errormsg").fadeOut();
    	}, 3000);
    	
    	function isNumber(n) {
    		return !isNaN(parseFloat(n)) && !isNaN(n - 0);
    	}
		$("#addType").on('click',function(event){
			if($(event.target).text()=="Add")
			{
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
			}else{
				
			}
	    });
		
		$('.label').click(function(){
			if(this.previousSibling.checked == true){
				this.previousSibling.checked = false;
			}else{
				this.previousSibling.checked = true;
			}
		});
		
		$('.selectAll').click(function(){
			if(this.previousSibling.checked != true){
				this.previousSibling.checked = true;
			}else{
				this.previousSibling.checked = false;
			}
		});
		$(".closebtn").click(function(){
			$(".alert").fadeOut();
		});
		
    });
    </script>
</body>
</html>