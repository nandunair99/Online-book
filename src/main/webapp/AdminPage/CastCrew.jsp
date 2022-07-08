<%@page import="com.narola.bookmyseat.utility.Constant"%>
<%@page import="com.narola.bookmyseat.entity.CastCrew"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.narola.bookmyseat.entity.User"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
	User user = (User) session.getAttribute("LoginUser");
	pageContext.setAttribute("user", user);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="resources/img/icons/icon-48x48.png" />
<link rel="canonical" href="https://demo-basic.adminkit.io/" />

<title>Cast Crew</title>
<link href="resources/css/app.css" rel="stylesheet">
<link href="resources/css/custom.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap"
	rel="stylesheet">
</head>

<body>
	<!-- 	PopUpBox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<span class="close" onclick="closePopup('CastCrew');">&times;</span>
			<p class="msg">Some text in the Modal..</p>
			<div id="modal2" class="modal-content2 ">
				<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button>
				<button type="button" class="noBtn btn btn-secondary" id="noBtn"
					onclick="closePopup('CastCrew');">Cancel</button>
			</div>
		</div>
	</div>
	<!-- Insert-Update-PopUp -->
	<div id="InUpmyModal" class="InUpmodal">
		<!-- Modal content -->
		<div class="InUpmodal-content">
			<span class="InUpclose" onclick="InUpclosePopup('CastCrew');">&times;</span>
			<p class="InUpmsg">Insert Cast Crew...</p>
			<hr>
			<div id="InUpmodal2" class="InUpmodal-content2 ">
				<form action="" name="CastCrewForm" method="post" enctype="multipart/form-data">
					<div class="card-body">
						<div class="mb-3">
							<label class="form-label">Name</label> <span id="errormsgccname" class="errormsg"> <% if (request.getAttribute("validErrorCCName") != null) { %><%=request.getAttribute("validErrorCCName")%> <%}%></span>
							<input type="hidden" value="<%if (request.getAttribute("txtCastCrewId") != null) {%><%=request.getAttribute("txtCastCrewId")%><%} else {%>-1<%}%>" name="txtCastCrewId" class="ccid">
							<!-- class(ccid) for value from table -->
							<input type="text" id="txtname" name="txtName"
								class="form-control ccname"
								value="<%if (request.getAttribute("txtName") != null) {%><%=request.getAttribute("txtName")%><%}%>"
								placeholder="Enter name...">
						</div>
						<div class="mb-3">
							<label class="form-label">Profile Image</label> <span
								id="errormsgstatename" class="errormsg"> <%
 if (request.getAttribute("validErrorCCProfilePic") != null) {
 %><%=request.getAttribute("validErrorCCProfilePic")%> <%
 }
 %>
							</span> <input type="file" id="txtpimg" name="txtPImg"
								class="form-control spimgname"> <img
								class="card-img-top upimgView"
								src="<%if (request.getAttribute("txtDisImg") != null) {%>data:image/png;base64,<%=request.getAttribute("txtDisImg")%><%}%>"
								alt="Unsplash"> <input type="hidden"
								value="<%if (request.getAttribute("txtDisImg") != null) {%><%=request.getAttribute("txtDisImg")%><%}%>"
								name="txtDisImg" class="txtDisImg">

						</div>
						<div class="md-3">
							<input type="button" value="ADD" class="btn btn-success btnCC btnAddCC">
							<input type="button" name="updateCC" value="Update Cast Crew" class="btn btn-primary btnUpdateCC">
							<input type="reset" value="Cancel" class="btn btn-secondary btnSCancel" onClick="window.location.reload()">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="wrapper">
		<nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="AdminDash.jsp"> <span
					class="align-middle">Admin Panel</span>
				</a>
				<ul class="sidebar-nav">
					<li class="sidebar-item"><a class="sidebar-link"
						href="AdminDash"> <i class="align-middle"
							data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
					</a></li>
					<li class="sidebar-item"><a class="sidebar-link" href="Place">
							<i class="align-middle" data-feather="map-pin"></i> <span
							class="align-middle">Place</span>
					</a></li>
					<li class="sidebar-item active"><a class="sidebar-link"
						href="CastCrew"> <i class="align-middle elementToFadeInAndOut"
							data-feather="star"></i> <span class="align-middle">Cast-Crew</span>
					</a></li>
					<li class="sidebar-item"><a class="sidebar-link" href="Movies">
							<i class="align-middle" data-feather="film"></i> <span
							class="align-middle">Movies</span>
					</a></li>
					<li class="sidebar-item"><a class="sidebar-link"
						href="Cinemas"> <i class="align-middle" data-feather="home"></i>
							<span class="align-middle">Cinemas</span>
					</a></li>
					<c:if test="${user.getUserTyp() eq Constant.USER_TYPE_SUPERADMIN}">
						<li class="sidebar-item">
							<a class="sidebar-link" href="Users">
	              				<i class="align-middle" data-feather="users"></i> <span class="align-middle">Users</span>
	           				 </a>
						</li>
					</c:if>
					<li class="sidebar-item"><a class="sidebar-link"
						href="Booking"> <i class="align-middle"
							data-feather="clipboard"></i> <span class="align-middle">Booking</span>
					</a></li>
				</ul>
			</div>
		</nav>

		<div class="main">
			<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle js-sidebar-toggle"> <i
					class="hamburger align-self-center"></i>
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
					<h1 class="h3 mb-3">
						<strong>Cast And Crew</strong> Data()
					</h1>
					<%
					List<CastCrew> al = new ArrayList<CastCrew>();
					al = (ArrayList<CastCrew>) request.getAttribute("castCrewdata");
					%>
					<div class="row">
						<div class="col-12 col-lg-12 col-xxl-12 d-flex">
							<div class="card flex-fill">
								<div class="card-header">
									<h5 class="card-title mb-0">
										Cast Crew Data (<%=al.size()%>)
									</h5>
									<h5 class="viewalltext">View all</h5>
								</div>
								<div class="d-flex" style="display: inline-block;">
									<div class="card NewCastViewcard" onclick="addNewCC();">
										<img class="card-img-top addnewimg"
											src="resources/img/photos/addNew.jpg" alt="Unsplash"> <span
											class="centerimg"><i class="align-middle"
											data-feather="user-plus"></i></span>
										<div class="card-header">
											<h5 class="card-title mb-0 addnewtext">Add New</h5>
										</div>
									</div>
									<div class="changeAll cardRow" id="cardRow">
										<%
										for (CastCrew castcrew : al) {
											String imgConvert = Base64.getEncoder().encodeToString(castcrew.getProfileImg());
										%>
										<div class="card CastViewcard">
											<img class="card-img-top"
												src="data:image/png;base64,<%=imgConvert%>" alt="Unsplash">
											<div class="card-header">
												<h5 class="card-title mb-0"><%=castcrew.getCastCrewName()%></h5>
												<div class="actionDiv">
													<i class="align-middle delbtn" data-feather="trash-2"
													onclick="confirmGo('sure to delete this record?','RemoveCCAction?casrCrewId=<%=castcrew.getCastCrewId()%>');"></i>&nbsp;&nbsp;&nbsp;
													<i class="align-middle upbtn" data-feather="edit"
													onclick="upCC('<%=castcrew.getCastCrewId()%>','<%=castcrew.getCastCrewName()%>','<%=imgConvert%>');"></i>
												</div>
											</div>
										</div>
										<%
										}
										%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="resources/js/custom.js"></script>
	<script src="resources/js/action.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
     	feather.replace()
    </script>
	<script>
    	$(document).ready(function(){
    		 $(".dropdown-toggle").on('click',function(){
 		    	$(".dropdown-menu").toggle(100);		
 		    });
 		    
    		//hide error msg and remove session after 3sec page load
        	window.setTimeout(function(){
        		$(".errormsg").fadeOut();
        	}, 3000);
        	//view ALl CC
    		$(".viewalltext").on('click',function(){
    			$('.changeAll').toggleClass('cardRow cardRowViewAll');
    			if ($('.changeAll').hasClass("cardRow")) {
    			      	$(".viewalltext").html("View all");
    			    } else {
    			     	$(".viewalltext").html("View less");
    			    }
    		});
    		//redirect to InsertCCServlet for insert
        	$(".btnAddCC").on('click',function(){
        		document.CastCrewForm.action = "<%=request.getContextPath() + Constant.URL_CASTCREW_INSERT%>";
       	    	document.CastCrewForm.submit();
        	});
        	//redirect to UpdateCCServlet for update
        	$(".btnUpdateCC").on('click',function(){
        		document.CastCrewForm.action = "<%=request.getContextPath() + Constant.URL_CASTCREW_UPDATE%>";
				document.CastCrewForm.submit();
			});
		});
		<%
		if (request.getAttribute("validErrorCCInsert") == Constant.ERROR_OCCURS_YES)
		{
		%>
		window.history.replaceState({}, '', 'CastCrew');
		addNewCC();
		<%
		} 
		else if (request.getAttribute("validErrorCCUpdate") == Constant.ERROR_OCCURS_YES)
		{
		%>
		window.history.replaceState({}, '', 'CastCrew');
		upCCAfterError();
		<%
		}
		%>
	</script>
	<script>
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
			<%}
		%>
	</script>
</body>
</html>