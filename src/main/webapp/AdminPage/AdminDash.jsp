<%@page import="com.narola.bookmyseat.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.narola.bookmyseat.utility.Constant"%>

<%@ page import="java.util.Base64" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<!DOCTYPE html>
<%
	User userdata = (User) session.getAttribute("LoginUser");
	String profileImg = (String) session.getAttribute("profile");
	pageContext.setAttribute("user", userdata);
%>	

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
					<li class="sidebar-item active">
						<a class="sidebar-link" href="AdminDash">
              <i class="align-middle elementToFadeInAndOut" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
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

					<h1 class="h3 mb-3"><strong>Analytics</strong> Dashboard</h1>
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
		    	window.history.replaceState({}, '', 'AdminDash');
		    </c:if>
	    });
    </script>
</body>
</html>