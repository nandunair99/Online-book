<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.bookmyseat.utility.Constant"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="assets/css/style-starter.css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="resource/img/icons/icon-48x48.png" />
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />
	<title>Log In | BookMySeat</title>
	<link href="resource/css/app.css" rel="stylesheet">
	<link href="resource/css/custom.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
	<style type="text/css">
	
		body {
			background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
			background-size: 400% 400%;
			animation: gradient 15s ease infinite;
			height: 100vh;
			overflow-y: hidden; /* Hide vertical scrollbar */
  			overflow-x: hidden; /* Hide horizontal scrollbar */
		}

		@keyframes gradient {
			0% {
				background-position: 0% 50%;
			}
			50% {
				background-position: 100% 50%;
			}
			100% {
				background-position: 0% 50%;
			}
		}
		.showPwd{
			float: right;
			position: relative;
			z-index: 1;
			margin-top: -25px;
			margin-right: 10px;
			cursor: pointer;
			color: var(--bs-body-color);
		}
		.showPwdoff{
			float: right;
			position: relative;
			z-index: 1;
			margin-top: -25px;
			margin-right: 10px;
			cursor: pointer;
			color: var(--bs-body-color);
			display: none;
		}
		input[type=password]{
			position: relative;
		}
	</style>
</head>
<body>
<!-- 	popupbox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
  		<div class="modal-content">
    		<span class="close" onclick="closePopup('Movies');">&times;</span>
    		<p class="msg">Some text in the Modal..</p>
    		<div id="modal2" class="modal-content2 ">
    			<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button><button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('Movies');">Cancel</button>
    		</div>  
  		</div>
	</div>
	<main class="d-flex w-100">
		<div class="container d-flex flex-column">
			<div class="row vh-100">
				<div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
					<div class="d-table-cell align-middle">
						<div class="card">
							<div class="text-center mt-3" style="background-color: white;border-radius: .25rem .25rem 0px 0px;margin-bottom: -50px">
								<div class="row" id="logodiv" style="margin-bottom: 20px">
									<div class="col-5"><li style="float: right;list-style-type:none"><img style="width: 45px" src="resource/img/defaultImg/logo.png"></li></div>
									<div class="col-7"><h1><li style="float: left;list-style-type:none;margin-top: 10px;margin-left: -15px">BookMySeat</li></h1></div>
								</div>
								<h1 class="h2">Welcome back...</h1>
								<p class="lead">
									Log in to your account to continue<br>
								</p>
								
							</div>
							<div class="card-body">
								<div class="m-sm-4">
									<form method="Post" action="LoginAction">
										<div class="mb-3">
											<label class="form-label">Email</label> <span class="errormsg"><c:if test="${not empty validErrorEmail}"><c:out value ="${validErrorEmail}"/></c:if></span>
											<input class="form-control form-control-lg" type="text" name="txtEmail" placeholder="Enter your email" value="<c:if test="${not empty afterLoginData.getEmailId()}"><c:out value ="${afterLoginData.getEmailId()}"/></c:if>"/>
										</div>
										<div class="mb-3">
											<label class="form-label">Password</label> <span class="errormsg"><c:if test="${not empty validErrorPassword}"><c:out value ="${validErrorPassword}"/></c:if></span>
											<input class="form-control form-control-lg" type="password" id="inputPwd" name="txtPassword" placeholder="Enter your password" value="<c:if test="${not empty afterLoginData.getPassword()}"><c:out value ="${afterLoginData.getPassword()}"/></c:if>"/>
											<i class="align-middle showPwd" data-feather="eye" onclick="showPwd()" ></i>
											<i class="align-middle showPwdoff" data-feather="eye-off" onclick="showPwd()" ></i>
											<small>
            									<a href="index.html">Forgot password?</a>
          									</small>
										</div>
										<div class="mb-3">
											<label class="form-label" >Type</label> <span class="errormsg"><c:if test="${not empty validErrorType}"><c:out value ="${validErrorType}"/></c:if></span>
											<select class="form-control form-control-lg" name="txtType">
												<option value="-1" disabled="disabled" selected="selected">Select type</option>
												<option value="${Constant.USER_TYPE_SUPERADMIN}" <c:if test="${afterLoginData.getType() eq Constant.USER_TYPE_SUPERADMIN}">selected</c:if>>Super admin</option>
												<option value="${Constant.USER_TYPE_ADMIN}" <c:if test="${afterLoginData.getType() eq Constant.USER_TYPE_ADMIN}">selected</c:if>>Admin</option>
												<option value="${Constant.USER_TYPE_SUPERCINEMAADMIN}" <c:if test="${afterLoginData.getType() eq Constant.USER_TYPE_SUPERCINEMAADMIN}">selected</c:if>>Super cinema admin</option>
												<option value="${Constant.USER_TYPE_CINEMAADMIN}" <c:if test="${afterLoginData.getType() eq Constant.USER_TYPE_CINEMAADMIN}">selected</c:if>>Cinema admin</option>
												<option value="${Constant.USER_TYPE_ENDUSER}" <c:if test="${afterLoginData.getType() eq Constant.USER_TYPE_ENDUSER}">selected</c:if>>User</option>
											</select>
										</div>
										<div>
											<label class="form-check">
	            								<input class="form-check-input" type="checkbox" value="remember-me" name="remember-me" checked>
	            								<span class="form-check-label">
	              									Remember me next time <span class="errormsg"><c:if test="${not empty validErrorLogin}"><c:out value ="${validErrorLogin}"/></c:if></span>
	            								</span>
         									</label>
										</div>
										<div class="text-center mt-3">
											<input type="submit" class="btn btn-lg btn-primary" name="loign" value="Login"> 
										</div>
									</form>
									<span style="width: 85%;text-align: center;position: absolute;">Create new account <a href="SignUp">click here!</a></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
 	<script src="https://unpkg.com/feather-icons"></script>
 	<script src="resource/js/app.js"></script>
 	<script src="resource/js/custom.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
    	feather.replace();
    	$(document).ready(function(){
    		window.setTimeout(function(){
        		$(".errormsg").fadeOut();
        	}, 3000);	
    	});
    	function showPwd() {
    		var x = document.getElementById("inputPwd");
    		  if (x.type === "password") {
    			  $('.showPwdoff').show();
    			  $('.showPwd').hide();
    		    x.type = "text";
    		  } else {
    		    x.type = "password";
    		    $('.showPwdoff').hide();
  			  	$('.showPwd').show();
    		  }
		}
		<c:if test='${validError eq Constant.ERROR_OCCURS_YES }'>
			window.history.replaceState({}, '', 'LoginServlet');
		</c:if>

    </script>
</body>
</html>