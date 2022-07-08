<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.bookmyseat.utility.Constant"%>
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

	<title>Dashboard</title>
	<link href="resources/css/app.css" rel="stylesheet">
	<link href="resources/css/custom.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

<body>
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
					<li class="sidebar-item">
						<a class="sidebar-link" href="Cinemas">
              <i class="align-middle" data-feather="home"></i> <span class="align-middle">Cinemas</span>
            </a>
					</li>
					<c:if test="${user.getUserTyp() eq Constant.USER_TYPE_SUPERADMIN}">
					<li class="sidebar-item active">
						<a class="sidebar-link" href="Users">
              				<i class="align-middle elementToFadeInAndOut"  data-feather="users"></i> <span class="align-middle">Users</span>
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
					<h1 class="h3 mb-3"><strong>Users</strong> Data</h1>
				</div>
					<div class="row">
						<div class="col-12">
							<div class="card flex-fill">
								<div class="tableBoxUser" >
									<div class="card-header ">
										<ul class="dataviewtop">
											<li><h5 class="card-title mb-0 dataTitle">Admin </h5></li>
										</ul>
									</div>
									<c:set var="i" value="0" scope="page" />
									<c:forEach var="user" items="${UserData}">
									<c:if test="${user.getUserTyp() eq Constant.USER_TYPE_ADMIN}">
										<div class="row userdiv">
											<div class="col-1" ><img style="width: 50px;border-radius:50%" src="data:image/png;base64,${profileImg.get(i)}"></div>
											<div class="col-11" ><span class="span">${user.getFirstName()}</span></div>
										</div>
										<div class="row userinfo" id="userinfo">
											<form action="" method="post">
												<div class="row" style="padding: 15px;">
													<div class="row">
														<div class="col-6">
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<input type="hidden" name="txtUserId" value="${user.getUserID()}">
																		<label class="form-label">First Name</label>
																		<input type="text" id="txtFirstName" name="txtFirstName" value="${user.getFirstName()}" class="form-control">
																	</div>
																</div>
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Last Name</label>
																		<input type="text" id="txtLastName" name="txtLastName" value="${user.getLastName()}" class="form-control">
																	</div>
																</div>
															</div>
															<div class="mb-3">
																<label class="form-label">Email</label>
																<input type="text" id="txtEmail" name="txtEmail" value="${user.getEmailID()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Phone</label>
																<input type="text" id="txtPhone" name="txtPhone" value="${user.getPhoneno()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Profile Photo</label>
																<input type="file" id="txtProfile" name="txtProfile" value="" class="form-control">
															</div>
														</div>
														<c:set var="address" value="${user.getAddressData()}" ></c:set>
														<input type="hidden" name="txtAddressID" value="${address.getAddressID()}">
														<div class="col-6">
															<div class="mb-3">
																<label class="form-label">Address Line1</label>
																<input type="text" id="txtAddressLine1" name="txtAddressLine1" value="${address.getAddressline1()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Address Line2</label>
																<input type="text" id="txtAddressLine2" name="txtAddressLine2" value="${address.getAddressline2()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Landmark</label>
																<input type="text" id="txtLandmark" name="txtLandmark" value="${address.getLandmark()}" class="form-control">
															</div>
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">City</label>
																		<select id="txtCityId" name="txtCityId" class="form-select mb-3 stateid">
	          																<option selected disabled="disabled" value="-1">Select City</option>
	          																<c:forEach var="city" items="${Citydata}">
								          									 	<option value="${city.getCityId()}" <c:if test="${address.getCity_ID() eq city.getCityId()} ">Selected</c:if>>
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
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Pincode</label>
																		<input type="text" id="txtPincode" name="txtPincode" value="${address.getPincode()}" class="form-control">
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row" >
														<div class="md-3">
															<center><input type="submit" name="submit" value="Save" class="btn btn-success"></center>
														</div>
													</div>
												</div>
											</form>
										</div>
									</c:if>
									<c:set var="i" value="${i + 1}" scope="page" />
									</c:forEach>
									
									<div class="card-header" style="margin-top: 20px">
										<ul class="dataviewtop">
											<li><h5 class="card-title mb-0 dataTitle">Cinemas Super Admin </h5></li>
										</ul>
									</div>
									<c:set var="j" value="0" scope="page" />
									<c:forEach var="user" items="${UserData}">
									<c:if test="${user.getUserTyp() eq Constant.USER_TYPE_SUPERCINEMAADMIN}">
										<div class="row userdiv">
											<div class="col-1" ><img style="width: 50px;border-radius:50%" src="data:image/png;base64,${profileImg.get(j)}"></div>
											<div class="col-11" ><span class="span">${user.getFirstName()} - <c:forEach var="admin" items="${CinemaAdmin}"><c:forEach var="cinema" items="${CinemaData}"><c:if test="${admin.getCinemaID() eq cinema.getCinemasID() and admin.getUserID() eq user.getUserID()}"> ${cinema.getName()}</c:if></c:forEach></c:forEach></span></div>
										</div>
										<div class="row userinfo" id="userinfo">
											<form action="" method="post">
												<div class="row" style="padding: 15px;">
													<div class="row">
														<div class="col-6">
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<input type="hidden" name="txtUserId" value="${user.getUserID()}">
																		<label class="form-label">First Name</label>
																		<input type="text" id="txtFirstName" name="txtFirstName" value="${user.getFirstName()}" class="form-control">
																	</div>
																</div>
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Last Name</label>
																		<input type="text" id="txtLastName" name="txtLastName" value="${user.getLastName()}" class="form-control">
																	</div>
																</div>
															</div>
															<div class="mb-3">
																<label class="form-label">Email</label>
																<input type="text" id="txtEmail" name="txtEmail" value="${user.getEmailID()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Phone</label>
																<input type="text" id="txtPhone" name="txtPhone" value="${user.getPhoneno()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Profile Photo</label>
																<input type="file" id="txtProfile" name="txtProfile" value="" class="form-control">
															</div>
														</div>
														<c:set var="address" value="${user.getAddressData()}" ></c:set>
														<input type="hidden" name="txtAddressID" value="${address.getAddressID()}">
														<div class="col-6">
															<div class="mb-3">
																<label class="form-label">Address Line1</label>
																<input type="text" id="txtAddressLine1" name="txtAddressLine1" value="${address.getAddressline1()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Address Line2</label>
																<input type="text" id="txtAddressLine2" name="txtAddressLine2" value="${address.getAddressline2()}" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Landmark</label>
																<input type="text" id="txtLandmark" name="txtLandmark" value="${address.getLandmark()}" class="form-control">
															</div>
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">City</label>
																		<select id="txtCityId" name="txtCityId" class="form-select mb-3 stateid">
	          																<option selected disabled="disabled" value="-1">Select City</option>
	          																<c:forEach var="city" items="${Citydata}">
								          									 	<option value="${city.getCityId()}" <c:if test="${address.getCity_ID() eq city.getCityId()} ">Selected</c:if>>
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
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Pincode</label>
																		<input type="text" id="txtPincode" name="txtPincode" value="${address.getPincode()}" class="form-control">
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row" >
														<div class="md-3">
															<center><input type="submit" name="submit" value="Save" class="btn btn-success"></center>
														</div>
													</div>
												</div>
											</form>
										</div>
										
									</c:if>
									<c:set var="j" value="${j + 1}" scope="page" />
									</c:forEach>
									<!--end of repete user -->
									
									<div class="card-header" style="margin-top: 20px">
										<ul class="dataviewtop">
											<li><h5 class="card-title mb-0 dataTitle">Add new admin </h5></li>
										</ul>
									</div>
									<div class="row userdiv addnewadmin">
										<div class="col-1" ><img style="width: 50px" src="resources/img/avatars/Invite.png"></div>
										<div class="col-11" ><span class="span">Add </span></div>
									</div>
										<div class="row userinfo useradd" id="userinfo">
											<form action="InsertUser" method="post" enctype="multipart/form-data">
												<div class="row" style="padding: 15px;">
													<div class="row">
														<div class="col-6">
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">First Name</label> <span class="errormsg"><c:if test="${not empty validErrorFirstName}"><c:out value ="${validErrorFirstName}"/></c:if></span>
																		<input type="text" id="txtFirstName" name="txtFirstName" value='<c:if test="${not empty afterUserData.getFirstName()}">${afterUserData.getFirstName()}</c:if>' class="form-control">
																	</div>
																</div>
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Last Name</label> <span class="errormsg"><c:if test="${not empty validErrorLastName}"><c:out value ="${validErrorLastName}"/></c:if></span>
																		<input type="text" id="txtLastName" name="txtLastName" value='<c:if test="${not empty afterUserData.getLastName()}">${afterUserData.getLastName()}</c:if>' class="form-control">
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Email</label> <span class="errormsg"><c:if test="${not empty validErrorEmail}"><c:out value ="${validErrorEmail}"/></c:if></span>
																		<input type="text" id="txtEmail" name="txtEmail" value='<c:if test="${not empty afterUserData.getEmail()}">${afterUserData.getEmail()}</c:if>' class="form-control">
																	</div>
																</div>
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Phone</label> <span class="errormsg"><c:if test="${not empty validErrorPhoneNo}"><c:out value ="${validErrorPhoneNo}"/></c:if></span>
																		<input type="text" id="txtPhone" name="txtPhone" value='<c:if test="${not empty afterUserData.getPhone()}">${afterUserData.getPhone()}</c:if>' class="form-control">
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Type</label> <span class="errormsg"><c:if test="${not empty validErrorType}"><c:out value ="${validErrorType}"/></c:if></span>
																		<select name="txtType" class="form-control selectType">
																			<option selected="selected">Select Type</option>
																			<option value="${Constant.USER_TYPE_ADMIN}" <c:if test="${afterUserData.getType() eq Constant.USER_TYPE_ADMIN}">selected</c:if> >Admin</option>
																			<option value="${Constant.USER_TYPE_SUPERCINEMAADMIN}" <c:if test="${afterUserData.getType() eq Constant.USER_TYPE_SUPERCINEMAADMIN}">selected</c:if> >Cinema Admin</option>
																		</select>
																	</div>
																</div>
																<div class="col-6 cinemadiv">
																	<div class="mb-3">
																		<label class="form-label">Cinema</label> <span class="errormsg"><c:if test="${not empty validErrorCinema}"><c:out value ="${validErrorCinema}"/></c:if></span>
																		<select id="txtCinemaId" name="txtCinemaId" class="form-select mb-3 stateid">
	          																<option selected disabled="disabled" value="-1">Select Cinema</option>
	          																<c:forEach var="cinema" items="${CinemaData}">
								          									 	<option value="${cinema.getCinemasID()}" <c:if test="${cinemaID eq cinema.getCinemasID()} ">selected</c:if>>${cinema.getName()}</option>
								          									</c:forEach>
	        															</select>
																	</div>
																</div>
															</div>
															<div class="mb-3">
																<label class="form-label">Profile Photo</label>
																<input type="file" id="txtProfile" name="txtProfile" value="" class="form-control">
															</div>
														</div>
														<div class="col-6">
															<div class="mb-3">
																<label class="form-label">Address Line1</label> <span class="errormsg"><c:if test="${not empty validErrorAddressLine1}"><c:out value ="${validErrorAddressLine1}"/></c:if></span>
																<input type="text" id="txtAddressLine1" name="txtAddressLine1" value="" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Address Line2</label> <span class="errormsg"><c:if test="${not empty validErrorAddressLine2}"><c:out value ="${validErrorAddressLine2}"/></c:if></span>
																<input type="text" id="txtAddressLine2" name="txtAddressLine2" value="" class="form-control">
															</div>
															<div class="mb-3">
																<label class="form-label">Landmark</label> <span class="errormsg"><c:if test="${not empty validErrorLandmark}"><c:out value ="${validErrorLandmark}"/></c:if></span>
																<input type="text" id="txtLandmark" name="txtLandmark" value="" class="form-control">
															</div>
															<div class="row">
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">City</label> <span class="errormsg"><c:if test="${not empty validErrorCity}"><c:out value ="${validErrorCity}"/></c:if></span>
																		<select id="txtCityId" name="txtCityId" class="form-select mb-3 stateid">
	          																<option selected disabled="disabled" value="-1">Select City</option>
	          																<c:forEach var="city" items="${Citydata}">
								          									 	<option value="${city.getCityId()}" >
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
																<div class="col-6">
																	<div class="mb-3">
																		<label class="form-label">Pincode</label> <span class="errormsg"><c:if test="${not empty validErrorPincode}"><c:out value ="${validErrorPincode}"/></c:if></span>
																		<input type="text" id="txtPincode" name="txtPincode" value="" class="form-control">
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row" >
														<div class="md-3">
															<center><input type="submit" name="submit" value="Add" class="btn btn-success"></center>
														</div>
													</div>
												</div>
											</form>
										</div>
									<!-- end of new user -->
								</div>
									
							</div>
						</div>
					</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="resources/js/custom.js"></script>
	<script src="resources/js/app.js"></script>
	<script src="resources/js/action.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	 <script>
      feather.replace()
    </script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	//profile popup
		    $(".dropdown-toggle").on('click',function(){
		    	$(".dropdown-menu").toggle(100);		
		    });
		    //login
		    <c:if test='${LoginSuccess eq Constant.ERROR_OCCURS_YES}'>
		    	window.history.replaceState({}, '', 'Users');
		    </c:if>
		    
		    //userinfo
		    $( ".userdiv" ).click(function() {
		    	$(this).next(".userinfo").toggle(100);
		    });
		    $('.selectType').on('change', function() {
		    	if(this.value == ${Constant.USER_TYPE_SUPERCINEMAADMIN})
		    	{
		    		$(".cinemadiv").show();
		    	}else{
		    		$(".cinemadiv").hide();
		    	}
		   	});
		    <c:if test="${validErrorUser eq Constant.ERROR_OCCURS_YES}">
		    	window.history.replaceState({}, '', 'Users');
		    	$(".useradd").show();
		    	$("#txtFirstName").focus();
		    	$(document).scrollTop($(document).height());
		    	<c:if test="${not empty cinemaID}">
		    		$(".cinemadiv").show();
		    	</c:if>
		    </c:if>
		    <c:if test="${not empty ERROR_OCCURS}">
		   	 	modal.style.display = "block";
				$(".wrapper").addClass("blur");
				p.innerHTML = ${Constant.ERROR_TEXTMSG};
		    </c:if>
	    });
    </script>
</body>
</html>