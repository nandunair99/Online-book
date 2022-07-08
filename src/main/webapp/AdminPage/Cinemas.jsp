<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.bookmyseat.utility.Constant"%>
<%@page import="com.narola.bookmyseat.entity.State"%>
<%@page import="java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.bookmyseat.entity.User"%>
<%
	User user = (User) session.getAttribute("LoginUser");
	pageContext.setAttribute("user", user);
%>	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="resources/img/icons/icon-48x48.png" />
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />

	<title>Cinemas</title>
	<link href="resources/css/app.css" rel="stylesheet">
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

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
    		<span class="InUpclose" onclick="InUpclosePopup('Cinemas');">&times;</span>
    		<p class="InUpmsg">Insert Movie</p>
    		<hr>
    		<div id="InUpmodal2" class="InUpmodal-content2">
    			<form action="" name="CinemaForm" method="post">
					<div class="card-body row">
						<div class="col-12">
							<div class="mb-3">
								<label class="form-label">Name</label> <span class="errormsg"><c:if test="${not empty validErrorCinemaName}"><c:out value ="${validErrorCinemaName}"/></c:if></span>
 								<input type="hidden" value='<c:choose><c:when test = '${not empty afterCinemaData.getCinemaId()}'><c:out value ='${afterCinemaData.getCinemaId()}'/></c:when><c:otherwise>${cinemaDataById.getCinemasID()}</c:otherwise></c:choose>' name = "txtCinemaId" class="txtCinemaId"> 
								<input type="text" id="txtCinemaName" name="txtCinemaName" class="form-control" placeholder="Enter cinema name..." value="<c:choose><c:when test = '${not empty afterCinemaData.getCinemaName()}'><c:out value ='${afterCinemaData.getCinemaName()}'/></c:when><c:when test = '${not empty cinemaDataById.getName()}'><c:out value ='${cinemaDataById.getName()}'/></c:when></c:choose>">
							</div>
						</div>
						
						<div class="col-6 col-lg-6 col-xxl-6">
							<div class="mb-3">
								<label class="form-label">Status</label>
									 <select id="txtStatus" name="txtStatus" class="form-select mb-3">
									 	<option value="${Constant.STATUS_MOVIE_INACTIVE}" <c:choose> <c:when test="${cinemaDataById.getStatus() eq Constant.STATUS_MOVIE_INACTIVE}">selected</c:when> <c:when test="${afterCinemaData.getCinemaStatus() eq Constant.STATUS_MOVIE_INACTIVE}">selected</c:when></c:choose> >Inactive</option>
									 	<option value="${Constant.STATUS_MOVIE_ACTIVE}" <c:choose> <c:when test="${cinemaDataById.getStatus() eq Constant.STATUS_MOVIE_ACTIVE}">selected</c:when> <c:when test="${afterCinemaData.getCinemaStatus() eq Constant.STATUS_MOVIE_ACTIVE}">selected</c:when></c:choose> >Active</option>
									</select>
							</div>
							<div class="mb-3">
								<input type="hidden" value='<c:choose><c:when test = '${not empty afterAddressData.getAddressId()}'><c:out value ='${afterAddressData.getAddressId()}'/></c:when><c:otherwise>${cinemaDataById.getAddressData().getAddressID()}</c:otherwise></c:choose>' name = "txtAddressId" class="txtAddressId"> 
								<label class="form-label">Address Line1</label> <span class="errormsg"><c:if test="${not empty validErrorAddressLine1}"><c:out value ="${validErrorAddressLine1}"/></c:if></span>
								<input type="text" id="txtCinemaAddLine1" name="txtCinemaAddLine1" class="form-control" placeholder="Enter movie title..." value="<c:choose><c:when test = '${not empty afterAddressData.getAddressLine1()}'><c:out value ='${afterAddressData.getAddressLine1()}'/></c:when><c:when test = '${not empty cinemaDataById.getAddressData().getAddressline1()}'><c:out value ='${cinemaDataById.getAddressData().getAddressline1()}'/></c:when></c:choose>">
							</div>
							<div class="mb-3">
								<label class="form-label">Address Line2</label> <span class="errormsg"><c:if test="${not empty validErrorAddressLine2}"><c:out value ="${validErrorAddressLine2}"/></c:if></span>
								<input type="text" id="txtCinemaAddLine2" name="txtCinemaAddLine2" class="form-control" placeholder="Enter movie title..." value="<c:choose><c:when test = '${not empty afterAddressData.getAddressLine2()}'><c:out value ='${afterAddressData.getAddressLine2()}'/></c:when><c:when test = '${not empty cinemaDataById.getAddressData().getAddressline2()}'><c:out value ='${cinemaDataById.getAddressData().getAddressline2()}'/></c:when></c:choose>">
							</div>
							<div class="mb-3">
								<label class="form-label">Pincode</label> <span class="errormsg"><c:if test="${not empty validErrorPincode}"><c:out value ="${validErrorPincode}"/></c:if></span>
								<input type="text" id="txtPincode" maxlength="6" min="6"  max="6" name="txtPincode" class="form-control" placeholder="Enter movie title..." value="<c:choose><c:when test = '${not empty afterAddressData.getPincode()}'><c:out value ='${afterAddressData.getPincode()}'/></c:when><c:when test = '${not empty cinemaDataById.getAddressData().getPincode()}'><c:out value ='${cinemaDataById.getAddressData().getPincode()}'/></c:when></c:choose>">
							</div>
						</div>
						<div class="col-6 col-lg-6 col-xxl-6">
							<div class="mb-3">
								<label class="form-label">Available facilities</label> 
									<textarea class="form-control" rows="5" style="height: 110px;" id="txtAvailableFacilities" name="txtAvailableFacilities" placeholder="Enter Available Facilities...&#10;(separate with comma)"><c:choose><c:when test = '${not empty afterCinemaData.getCinemaFacilities()}'><c:out value ='${afterCinemaData.getCinemaFacilities()}'/></c:when><c:when test = '${not empty cinemaDataById.getFacilities()}'><c:out value ='${cinemaDataById.getFacilities()}'/></c:when></c:choose></textarea>
							</div>
							<div class="mb-3">
								<label class="form-label">Landmark</label> <span class="errormsg"><c:if test="${not empty validErrorLandmark}"><c:out value ="${validErrorLandmark}"/></c:if></span>
								<input type="text" id="txtLandmark" name="txtLandmark" class="form-control" placeholder="Enter landmark..." value="<c:choose><c:when test = '${not empty afterAddressData.getLandmark()}'><c:out value ='${afterAddressData.getLandmark()}'/></c:when><c:when test = '${not empty cinemaDataById.getAddressData().getLandmark()}'><c:out value ='${cinemaDataById.getAddressData().getLandmark()}'/></c:when></c:choose>">
							</div>
							
							<div class="mb-3">
								<label class="form-label">City</label> <span class="errormsg"><c:if test="${not empty validErrorCity}"><c:out value ="${validErrorCity}"/></c:if></span>
									<select id="txtCityId" name="txtCityId" class="form-select mb-3 stateid">
          								<option selected disabled="disabled" value="-1">Select City</option>
          								<c:forEach var="city" items="${Citydata}">
          									 <option value="${city.getCityId()}" <c:if test="${city.getCityId() eq afterAddressData.getCity()}">selected</c:if><c:if test="${city.getCityId() eq cinemaDataById.getAddressData().getCity_ID()}">selected</c:if>>
          									 <c:forEach var = "i" begin = "0" end = "${Statedata.size()-1}">
          									 	<c:if test="${Statedata.get(i).getStateId() eq city.getStateId()}">
          									 		${city.getCityName()}, ${Statedata.get(i).getStateName()}
          									 	</c:if>
          									 </c:forEach>
          									 </option>
          								</c:forEach>
        							</select>
							</div>
						</div>
						<div class="col-12" style="margin-top: 15px;">
							<div class="md-3">
								<input type="button" value="ADD" class="btn btn-success btnAdd">
								<input type="button" name="updateCC" value="Update Cinema" class="btn btn-primary btnUpdate">
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
						<a class="sidebar-link" href="AdminDash">
              <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
            </a>
					</li>

					<li class="sidebar-item">
						<a class="sidebar-link" href="Place">
              <i class="align-middle" data-feather="map-pin"></i> <span class="align-middle">Place</span>
            </a>
					</li>

					<li class="sidebar-item">
						<a class="sidebar-link" href="CastCrew">
              <i class="align-middle" data-feather="star"></i> <span class="align-middle">Cast-Crew</span>
            </a>
					</li>

					<li class="sidebar-item">
						<a class="sidebar-link" href="Movies">
              <i class="align-middle" data-feather="film"></i> <span class="align-middle">Movies</span>
            </a>
					</li>

					<li class="sidebar-item active">
						<a class="sidebar-link" href="Cinemas">
              <i class="align-middle elementToFadeInAndOut" data-feather="home"></i> <span class="align-middle">Cinemas</span>
            </a>
					</li>


					<c:if test="${user.getUserTyp() eq Constant.USER_TYPE_SUPERADMIN}">
					<li class="sidebar-item">
						<a class="sidebar-link" href="Users">
              				<i class="align-middle" data-feather="users"></i> <span class="align-middle">Users</span>
           				 </a>
					</li>
					</c:if>

					<li class="sidebar-item">
						<a class="sidebar-link" href="Booking">
              <i class="align-middle" data-feather="clipboard"></i> <span class="align-middle">Booking</span>
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
	                			<img src="data:image/png;base64,${user.getProfileImgAsBase64()}" class="avatar img-fluid rounded me-1" alt="Charles Hall" /> <span class="text-dark">${user.getFirstName()}</span>
	              			</a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="pages-profile.html"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
								<a class="dropdown-item" href="LogoutServlet">Log out</a>
							</div>
						</li>
					</ul>
				</div>				
			</nav>

			<main class="content">
				<div class="container-fluid p-0">

					<h1 class="h3 mb-3"><strong>Cinemas</strong> Data</h1>
					<div>
						<ul class="dataviewtop">
							<li>
								<button class="btn btn-secondary d-inline" onclick="addNewCC();">Add <i class="align-middle" data-feather="plus"></i></button>
							</li>
							<li>
								<input type="search" id="searchMovie" class="form-control inputsearch" placeholder="Search cinema..." onkeyup="searchMovie();">
							</li>
							<li style="float: right;">
								<span class="badge filterBtnInactive">Inactive</span>
							</li>
							<li style="float: right;">
								<span class="badge filterBtnActive">Active</span>
							</li>
						</ul>	
					</div>
					<form action="" method="post">
						<table class="table table-hover my-0" id="movieTable">
							<thead>
								<tr>
									<th>SN</th>
									<th>Name</th>
									<th>Available Facilities</th>
									<th>Status</th>
									<th colspan="3" style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="1" scope="page" />
								<c:set var="i" value="0" scope="page" />
								<c:forEach var="cinema" items="${CinemaData}">
									<tr>
										<td>${count}</td>
										<td>${cinema.getName()}</td>
										<c:set var="cinemaAF" value="${cinema.getFacilities()}"></c:set>
										<%
											String str = (String) pageContext.getAttribute("cinemaAF");
										 	String[] af = str.split("[,]", 0);
										 	pageContext.setAttribute("af",af);
										%>
										<td><c:forEach var="afdata" items="${af}"><span style="padding:2px 5px 2px 5px;background-color:#4e4eb2;color: white;margin-left: 5px;border-radius: 0.2rem;">${afdata}</span></c:forEach></td>
										<td><c:choose>
											  <c:when test="${cinema.getStatus() == 0}">
											  	<span style="cursor: pointer;" class="badge bg-success">Active</span>
											  </c:when>
											  <c:when test="${cinema.getStatus() == 1}">
											    <span style="cursor: pointer;" class="badge bg-warning">Inactive</span>
											  </c:when>
											  </c:choose>									
										</td>
										<td><i class="align-middle upbtn" data-feather="edit" onclick=updateCinema(${cinema.getCinemasID()});></i></td>
										<td><a style="color:#495057" href="ViewCinema?cinemaId=${cinema.getCinemasID()}"><i class="align-middle viewbtn" data-feather="eye"></i></a></td>
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"/>
									<c:set var="i" value="${i + 1}" scope="page" />
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="resources/js/custom.js"></script>
	<script src="resources/js/app.js"></script>
	<script src="resources/js/action.js"></script>
	<script src="resources/js/cinemas.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script>
      feather.replace()
    </script>
    <script type="text/javascript">
    $(document).ready(function(){
    	//profile dropdown
    	$(".dropdown-toggle").on('click',function(){
		    	$(".dropdown-menu").toggle(100);		
		});
    	//hide error msg and remove session after 3sec page load
    	window.setTimeout(function(){
    		$(".errormsg").fadeOut();
    	}, 3000);
	  	//redirect to InsertMovieServlet for insert
		$(".btnAdd").on('click',function(){
			document.CinemaForm.action = "<%=request.getContextPath() + Constant.URL_CINEMA_INSERT%>";
		    	document.CinemaForm.submit();
		});
		//redirect to UpdateMovieServlet for update
		$(".btnUpdate").on('click',function(){
			document.CinemaForm.action = "<%=request.getContextPath() + Constant.URL_CINEMA_UPDATE%>";
			document.CinemaForm.submit();
		});
		
		//Display Error msg 
    	<%
		if (request.getAttribute("validErrorCinemaInsert") == Constant.ERROR_OCCURS_YES)
		{
		%>
			window.history.replaceState({}, '', 'Cinemas');
			addNewCC();
		<%
		} 
		else if (request.getAttribute("validErrorCinemaUpdate") == Constant.ERROR_OCCURS_YES)
		{
		%>
			window.history.replaceState({}, '', 'Cinemas');
			upCCAfterErrorCinema();
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
		
		<c:if test="${not empty cinemaDataById.getCinemasID()}">
			window.history.replaceState({}, '', 'Cinemas');
		
			updateCinemaForm();
		</c:if>
    });
    </script>
</body>
</html>