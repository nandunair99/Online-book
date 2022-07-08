<%@page import="com.narola.bookmyseat.utility.validationFunction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.bookmyseat.utility.Constant"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="resources/img/icons/icon-48x48.png" />
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />

	<title>Show</title>
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
    		<span class="close" onclick="closePopup('ManageShow');">&times;</span>
    		<p class="msg">Some text...</p>
    		<div id="modal2" class="modal-content2 ">
    			<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button><button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('ManageShow');">Cancel</button>
    		</div>  
  		</div>
	</div>
	<!-- Insert-Update-popup -->
	<div id="InUpmyModal" class="InUpmodal">
		<!-- Modal content -->
  		<div class="InUpmodal-content">
    		<span class="InUpclose" onclick="InUpclosePopup('ManageShow');">&times;</span>
    		<p class="InUpmsg">Insert Show</p>
    		<hr>
    		<div style="width: 100%;text-align: center;"><span class="errormsg"><c:if test="${not empty validErrorSameTime}"><c:out value ="${validErrorSameTime}"/></c:if> </span></div>
    		<div id="InUpmodal2" class="InUpmodal-content2">
    			<form name="ShowForm" method="post">
					<div class="card-body row">
						<div class="col-6 col-lg-6 col-xxl-6">
							<div class="mb-3">
								<input type="hidden" value='<c:choose><c:when test = '${not empty afterShowData.getShowID()}'><c:out value ='${afterShowData.getShowID()}'/></c:when><c:otherwise>${UpdateShowData.getShowID()}</c:otherwise></c:choose>' name = "txtShowId" class="txtShowId"> 
								<label class="form-label">Movie</label> <span class="errormsg"><c:if test="${not empty validErrorMovieID}"><c:out value ="${validErrorMovieID}"/></c:if></span>
									<select class="form-select mb-3" id="txtMovieID" name="txtMovieID">
			 							<option value="-1" disabled="disabled" selected="selected">Select movie</option>
			 							<c:forEach var="movie" items="${MovieData}">
			 								<option value="${movie.getMovieID()}" <c:if test="${movie.getMovieID() eq afterShowData.getMovieID()}">selected</c:if><c:if test="${movie.getMovieID() eq UpdateShowData.getMovieID()}">selected</c:if>>${movie.getMovieTitle()}</option>
			 							</c:forEach>
			 						</select>
							</div>
							<div class="mb-3">
								<label class="form-label">Show date</label> <span class="errormsg"><c:if test="${not empty validErrorShowDate}"><c:out value ="${validErrorShowDate}"/></c:if></span>
								<input type="date" id="txtShowDate" name="txtShowDate" class="form-control" value="<c:choose><c:when test = '${not empty afterShowData.getShowDate()}'><c:out value ='${afterShowData.getShowDate()}'/></c:when><c:when test = '${not empty UpdateShowData.getShowDate()}'><c:out value ='${UpdateShowData.getShowDate()}'/></c:when></c:choose>">
							</div>
							<div class="mb-3">
								<label class="form-label">Show time</label> <span class="errormsg"><c:if test="${not empty validErrorShowTime}"><c:out value ="${validErrorShowTime}"/></c:if></span>
								<input type="time"  id="txtShowTime" name="txtShowTime" class="form-control" value="<c:choose><c:when test = '${not empty afterShowData.getShowTime()}'><c:out value ='${afterShowData.getShowTime()}'/></c:when><c:when test = '${not empty UpdateShowData.getShowTime()}'><c:out value ='${UpdateShowData.getShowTime()}'/></c:when></c:choose>">
							</div>
							<div class="md-3">
								<input type="button" value="ADD" class="btn btn-success btnAdd">
								<input type="button" name="updateCC" value="Update Show" class="btn btn-primary btnUpdate">
							</div>
						</div>
						<div class="col-6 col-lg-6 col-xxl-6">
							<div class="mb-3">
								<label class="form-label">Screen</label> <span class="errormsg"><c:if test="${not empty validErrorScreenID}"><c:out value ="${validErrorScreenID}"/></c:if></span> 
									<select class="form-select mb-3" id="txtScreenId" name="txtScreenId" onchange="displayPrice(this);">
			 							<option value="-1" disabled="disabled" selected="selected">Select seat type</option>
			 							<c:forEach var="screen" items="${ScreenData}">
			 								<option value="${screen.getScreenID()}" <c:if test="${screen.getScreenID() eq afterShowData.getScreenID()}">selected</c:if> <c:if test="${screen.getScreenID() eq UpdateShowData.getScreenID()}">selected</c:if>>${screen.getScreenName()}</option>
			 							</c:forEach>
			 						</select>
							</div>
							<div class="mb-3">
								<label class="form-label">Price</label> <span id="errorMsgMovieName" class="errormsg"><c:if test="${not empty validErrorShowPrice}"><c:out value ="${validErrorShowPrice}"/></c:if></span>
								<ul class="addPrice" id="addPrice">
									<c:forEach var="price" items="${UpdateShowData.getShowPrice()}">
										<input type="hidden" name="txtShowPriceID" value="${price.getShowPriceID()}">
										<input type="hidden" id="txtSeatTypeID" name="txtSeatTypeID" value="${price.getSeatTypeID()}">
										<li>
										<c:forEach var="seattype" items="${SeatTypeData}">
											<c:if test="${seattype.getScreenSeatTypeID() eq price.getSeatTypeID()}">
												<span style="pedding:10px">${seattype.getSeatType()}</span>
											</c:if>
										</c:forEach>
											<input type="text" id="txtSeatTypePrice" name="txtSeatTypePrice" placeholder="Enter Rs." class="typePriceInput" value="${price.getPrice()}">
										</li>
									</c:forEach>
								</ul>
						  	</div>
						</div>
					</div>
				</form>
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
					<li class="sidebar-item">
						<a class="sidebar-link" href="CinemaAdminDashboard">
              				<i class="align-middle " data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
            			</a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="ManageScreen">
              				<i class="align-middle" data-feather="airplay"></i> <span class="align-middle">Screen</span>
            			</a>
					</li>
					<li class="sidebar-item active">
						<a class="sidebar-link" href="ManageShow">
              				<i class="align-middle elementToFadeInAndOut" data-feather="video"></i> <span class="align-middle">Show</span>
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
                			<i class="align-middle settings" data-feather="settings"></i>
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
					<h1 class="h3 mb-3"><strong>Manage</strong> Show</h1>
					<div>
						<ul class="dataviewtop">
							<li>
								<button class="btn btn-secondary d-inline" onclick="addNewShow();">Add <i class="align-middle" data-feather="plus"></i></button>
							</li>
						</ul>
					</div>
					<table class="table table-hover my-0">
						<thead>
							<tr>
								<th>SN</th>
								<th>Movie</th>
								<th style="text-align: center;">Date</th>
								<th style="text-align: center;">Show Time</th>
								<th style="text-align: center;">ShowEnd Time</th>
								<th style="text-align: center;">Screen</th>
								<th style="text-align: center;">Price</th>
								<th colspan="1" style="text-align: center;">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="count" value="1" scope="page" />
							<c:set var="i" value="0" scope="page" />
							<c:forEach var="show" items="${ShowData}">
								<tr>
									<td>${count}</td>
									<td>
										<c:forEach var="movie" items="${MovieData}">
											<c:if test="${movie.getMovieID() eq show.getMovieID()}">
												${movie.getMovieTitle()}
											</c:if>
										</c:forEach>									
									</td>
									<td style="text-align: center;"><fmt:formatDate pattern = "dd-MM-yyyy" value = "${show.getShowDate()}" /></td>
									<td style="text-align: center;">${show.getShowTime()}</td>
									<td style="text-align: center;">${show.getEndTime()}</td>
									<td style="text-align: center;">
										<c:forEach var="screen" items="${ScreenData}">
											<c:if test="${screen.getScreenID() eq show.getScreenID()}">
												${screen.getScreenName()}
											</c:if>
										</c:forEach>
									</td>
									<td style="text-align: center;"><span class="btn-sm btn-success viewPrice" id="viewPrice">View price</span>
										<div class="viewPriceBox">
											<ul style="list-style-type: none;">
												<c:forEach var="price" items="${show.getShowPrice()}">
													<li style="margin-left: -32px">
													<c:forEach var="seattype" items="${SeatTypeData}">
														<c:if test="${price.getSeatTypeID() eq seattype.getScreenSeatTypeID()}">
														 ${seattype.getSeatType()}
														</c:if>
													</c:forEach>
													: ${price.getPrice()} Rs.</li>
												</c:forEach>
											</ul>
										</div>
									</td>
<%-- 									<td><i class="align-middle delbtn" data-feather="trash-2" onclick="confirmGo('sure to delete this record?','RemoveMovieAction?movieId=${movie.getMovieID()}');"></i></td> --%>
									<td style="text-align: center;"><i class="align-middle upbtn" data-feather="edit" onclick=updateShow(${show.getShowID()});></i></td>
								</tr>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<c:set var="i" value="${i + 1}" scope="page" />
							</c:forEach>
						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="resources/js/app.js"></script>
	<script src="resources/js/show.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script>
      feather.replace()
    </script>
    <script type="text/javascript">
    	$(document).ready(function(){
        	//hide error msg and remove session after 3sec page load
        	window.setTimeout(function(){
        		$(".errormsg").fadeOut();
        	}, 3000);
	    	$(".dropdown-toggle").on('click',function(){
	    		$(".dropdown-menu").toggle(100);
	    	});
	    	//redirect to InsertShow for insert
			$(".btnAdd").on('click',function(){
				document.ShowForm.action = "<%=request.getContextPath() + Constant.URL_SHOW_INSERT%>";
			   	document.ShowForm.submit();
			});
			//redirect to UpdateShow for update
			$(".btnUpdate").on('click',function(){
				document.ShowForm.action = "<%=request.getContextPath() + Constant.URL_SHOW_UPDATE%>";
				document.ShowForm.submit();
			});
			
			<c:if test="${not empty UpdateShowData.getShowID()}">
				window.history.replaceState({}, '', 'ManageShow');
				updateShowForm();
			</c:if>
    	});
    	$(function(){
    	    var dtToday = new Date();
    	    var month = dtToday.getMonth() + 1;
    	    var day = dtToday.getDate();
    	    var year = dtToday.getFullYear();
    	    if(month < 10)
    	        month = '0' + month.toString();
    	    if(day < 10)
    	        day = '0' + day.toString();
    	    var minDate = year + '-' + month + '-' + day;
    	    $('#txtShowDate').attr('min', minDate);
    	});
    	//Display Error msg 
    	<%
		if (request.getAttribute("validErrorShowInsert") == Constant.ERROR_OCCURS_YES)
		{
		%>
			window.history.replaceState({}, '', 'ManageShow');
			addNewShow();
		<%
		} 
		else if (request.getAttribute("validErrorShowUpdate") == Constant.ERROR_OCCURS_YES)
		{
		%>
			window.history.replaceState({}, '', 'ManageShow');
			//upCCAfterErrorCinema();
		<%
		}
		%>
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
    	function displayPrice(el){
    		var screenID = el.value;
    		$('#addPrice').empty();
    		<c:forEach var="seattype" items="${SeatTypeData}">
    			var dataScreenID = "<c:out value="${seattype.getScreenID()}"/>";
				if(screenID == dataScreenID){
					var seatType = "<c:out value="${seattype.getSeatType()}"/>";
					var seatTypeID = "<c:out value="${seattype.getScreenSeatTypeID()}"/>";
					$("#addPrice").append('<input type="hidden" id="txtSeatTypeID" name="txtSeatTypeID" value="'+seatTypeID+'"><li><span style="pedding:10px">'+seatType+'</span><input type="text" id="txtSeatTypePrice" name="txtSeatTypePrice" placeholder="Enter Rs." class="typePriceInput"></li>');
				}
			</c:forEach>
    	}
    	<c:if test="${not empty afterShowData.getScreenID()}">
			var screenID = ${afterShowData.getScreenID()};
				const e = new Event("change");
				const element = document.querySelector('#txtScreenId');
				element.dispatchEvent(e);
		</c:if>
		
    </script>
</body>
</html>