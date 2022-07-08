
<%@page import="com.narola.bookmyseat.utility.Constant"%>
<%@page import="com.narola.bookmyseat.entity.City"%>
<%@page import="com.narola.bookmyseat.entity.State"%>
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
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="preconnect" href="https://fonts.gstatic.com">

	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/img/icons/icon-48x48.png" />
	<link rel="canonical" href="https://demo-basic.adminkit.io/" />

	<title>Place</title>
	<link href="${pageContext.request.contextPath}/resource/css/app.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resource/css/custom.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
	<!-- 	popupbox -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
  		<div class="modal-content">
    		<span class="close" onclick="closePopup('place');">&times;</span>
    		<p class="msg">Some text in the Modal..</p>
    		<div id="modal2" class="modal-content2 ">
    			<button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button><button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('place');">Cancel</button>
    		</div>  
  		</div>
	</div>
	
	<div class="wrapper ">
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

					<li class="sidebar-item active">
						<a class="sidebar-link" href="${pageContext.request.contextPath}/location/place">
              <i class="align-middle elementToFadeInAndOut" data-feather="map-pin"></i> <span class="align-middle">Place</span>
            </a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="CastCrew">
              <i class="align-middle" data-feather="star"></i> <span class="align-middle">Cast-Crew</span>
            </a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="${pageContext.request.contextPath}/movies/getmovies">
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
					<h1 class="h3 mb-3"><strong> State-City </strong> Data</h1>
					<!--for state-->
					<%
					List<State> al = new ArrayList<State>();		
					al =  (ArrayList<State>) request.getAttribute("Statedata");
					int c=0;
					%>
					<div class="row">
						<div class="col-12 col-lg-6 col-xxl-6 d-flex">
							<div class="card flex-fill">
								<div class="card-header ">
									<ul class="dataviewtop">
										<li><h5 class="card-title mb-0 dataTitle">State Data (<%= al.size() %>)</h5></li>
										<li><input type="search" id="searchState" class="form-control inputsearch" onkeyup="searchState();" placeholder="Search state..."></li>
									</ul>
								</div>
								<div class="tableBox">
								<table class="table table-hover my-0" id="tableState">
									<thead>
										<tr>
											<th>SN</th>
											<th>Name</th>
											<th>Country</th> 
											<th colspan="2">Action</th>
										</tr>
									</thead>
									<tbody>
									<%
										for (State state: al)
										{
											c++;
									%>
										<tr>
											<td><%= c %></td>
											<td hidden="true"><%=state.getStateId()%></td>
											<td class="sname"><%=state.getStateName()%></td>
											<td><%
											if(state.getCountryId()==1){
											%>India<%
											}
											%></td>
											<td><i class="align-middle delbtn delState" data-feather="trash-2" onclick="confirmGo('sure to delete this record?','deletestate?stateId=<%=state.getStateId()%>');"></i></td>
											<td><i class="align-middle upbtn updateState" data-feather="edit"></i></td>
										</tr>
									<%
									}
									%>
									</tbody>
								</table>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-6 col-xxl-6 d-flex">
							<div class="card flex-fill">
								<div class="card-header">
									<h5 class="card-title statetitle mb-0">Add State</h5>
								</div>
								<form name="StateForm" method="post">
								<div class="card-body">
									<div class="mb-3">
										<label class="form-label">State</label> <span id="errormsgstatename" class="errormsg"><%
 if(request.getAttribute("validErrorStateName")!= null){
 %><script>window.history.replaceState({}, '','place');</script><%=request.getAttribute("validErrorStateName")%><%
 }
 %></span>
 										<input type="hidden" value="-1" name = "stateId" class="stateid"> <!-- class(stateid) for value from table -->
										<input type="text" id="txtstate" name="stateName" class="form-control statename" placeholder="Enter state..." value="<%if(request.getAttribute("txtState")!= null){%><%=request.getAttribute("txtState")%><%}%>">
									</div>
									<div class="md-3">
										<input type="button" value="ADD State" class="btn btn-success btnState btnAddState">
										<input type="button" name="updateCity" value="Update State" class="btn btn-primary btnUpdateState">
										<input type="reset" value="Cancel" class="btn btn-secondary btnSCancel" onClick="window.location.reload()">
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
					<!--for city-->
					<%
					List<City> alCity = new ArrayList<City>();
											alCity = (ArrayList<City>) request.getAttribute("Citydata");
											int c1=0;
											String state = new String();
					%>
					<div class="row">
						<div class="col-12 col-lg-6 col-xxl-6 d-flex">
							<div class="card flex-fill">
								<div class="card-header">
									<ul class="dataviewtop">
										<li><h5 class="card-title mb-0 dataTitle">City Data (<%=alCity.size()%>)</h5></li>
										<li><input type="search" id="searchCity" class="form-control inputsearch" onkeyup="searchCity();" placeholder="Search city..."></li>
									</ul>
								</div>
								<div class="tableBox">
								<table class="table table-hover my-0" id="tableCity">
									<thead>
										<tr>
											<th>SN</th>
											<th>Name</th>
											<th>State</th> 
											<th colspan="2" >Action</th>
										</tr>
									</thead>
									<tbody>
									<%
									for (City ct: alCity) {
										for (State s : al) {
											if (s.getStateId() == ct.getStateId()) {
												state = s.getStateName();
											}
										}
										c1++;
									%>
										<tr>
											<td><%=c1%></td>
											<td hidden="true"><%=ct.getCityId()%></td>
											<td class="sname"><%=ct.getCityName()%></td>
											<td hidden="true"><%=ct.getStateId()%></td>
											<td><%=state%></td>
											<td><i class="align-middle delbtn delCity" data-feather="trash-2" onclick="confirmGo('sure to delete this record?','deletecity?cityID=<%=ct.getCityId()%>');"></i></td>
											<td><i class="align-middle upbtn updateCity" data-feather="edit"></i></td>
										</tr>
									<%
									}
									%>
									</tbody>
								</table>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-6 col-xxl-6 d-flex">
							<div class="card flex-fill">
								<div class="card-header">
									<h5 class="card-title mb-0 citytitle">Add City</h5>
								</div>
								<form name="CityForm" method="post">
								<div class="card-body">
									<div class="mb-3">
										<label class="form-label">City</label> <span id="errormsgcityname" class="errormsg"><%
 if(request.getAttribute("validErrorCityName")!= null){
 %><script>window.history.replaceState({}, '','Place');</script><%=request.getAttribute("validErrorCityName")%><%
 }
 %></span>
										<input type="hidden" value="-1" name = "cityId" class="cityid" >
										<input type="text" id="txtcity" name="cityName" class="form-control cityname" placeholder="Enter city..." value="<%if(request.getAttribute("txtCity")!= null){%><%=request.getAttribute("txtCity")%><%}%>">
									</div>
									<div class="mb-3">
										<label class="form-label">State</label> <span id="errormsgselstate" class="errormsg"><%
 if(request.getAttribute("validErrorSelectState")!= null){
 %><script>window.history.replaceState({}, '','Place');</script><%=request.getAttribute("validErrorSelectState")%><%
 }
 %></span>
										<select id="txtselstate" name="stateId" class="form-select mb-3 stateid">
          									<option selected disabled="disabled" value="-1">Select state</option>
          								<%
          								for (State s: al)
          								          																{
          								%>
          									<option value="<%=s.getStateId()%>" <%if(request.getAttribute("txtStateId")!=null){ String sid = request.getAttribute("txtStateId").toString(); if(s.getStateId() == Integer.parseInt(sid) ){%> selected="selected" <%}}%> ><%=s.getStateName()%></option>
          								<% 
										}
          								%>
        								</select>
									</div>
									<div class="md-3">
										<input type="button" name="addCity" value="ADD City" class="btn btn-success btnAddCity">
										<input type="button" name="updateCity" value="Update City" class="btn btn-primary btnUpdateCity">
										<input type="reset" value="Cancel" class="btn btn-secondary btnCCancel" onClick="window.location.reload()">
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script src="https://unpkg.com/feather-icons"></script>
	<script src="${pageContext.request.contextPath}/resource/js/custom.js"></script>
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
    	//hide error msg and remove session after 3sec page load
    	window.setTimeout(function(){
    		$(".errormsg").fadeOut();
    	}, 3000);
    	//update state
    	$(".updateState").on('click',function(){
    		 var currentRow=$(this).closest("tr");
    		 var id=currentRow.find("td:eq(1)").html();
    		 var name=currentRow.find("td:eq(2)").html();
    		 //$('tr.uprow-border').removeClass("uprow-border");
    		 $(this).closest("tr").addClass("uprow-border");
    		 $(this).closest("tr").siblings().removeClass( "uprow-border" );
    		 $(".stateid").prop('value', id);
    		 $(".statename").prop('value', name);
    		 $(".statetitle").html('Update State');
    		 $(".btnUpdateState").show(); 
    		 $(".btnAddState").hide();
    		 $(".btnSCancel").show();  
    	});
    	//redirect to InsertSatetServlet for insert
    	$(".btnAddState").on('click',function(){
    		document.StateForm.action = "<%=request.getContextPath()%>/location/insertstate";
    		document.StateForm.method = "post";
   	    	document.StateForm.submit();
    	});
    	//redirect to UpdateStateServlet for update
    	$(".btnUpdateState").on('click',function(){
    		document.StateForm.action = "<%=request.getContextPath()%>/location/updatestate";
			document.StateForm.method = "post";
   	    	document.StateForm.submit();
    	});
    	//update cuty
    	$(".updateCity").on('click',function(){
   		 	var currentRow=$(this).closest("tr");
   		 	var id=currentRow.find("td:eq(1)").html();
   		 	var name=currentRow.find("td:eq(2)").html();
   		 	var sid=currentRow.find("td:eq(3)").html();
   		 	//$('tr.uprow-border').removeClass("uprow-border");
   		 	$(this).closest("tr").addClass("uprow-border");
   		 	$(this).closest("tr").siblings().removeClass( "uprow-border" );
   		 	$(".cityid").prop('value', id);
		 	$(".cityname").prop('value', name);
		 	$(".stateid").val(sid);
   		 	$(".citytitle").html('Update City');
   		 	$(".btnUpdateCity").show();
   		 	$(".btnAddCity").hide();
   		 	$(".btnCCancel").show();
   		});
    	//redirect to InsertCityServlet for insert
    	$(".btnAddCity").on('click',function(){
    		document.CityForm.action = "<%=request.getContextPath()%>/location/insertcity";
			document.StateForm.method = "post";
   	    	document.CityForm.submit();
    	});
    	//redirect to UpdateCityServlet for update
    	$(".btnUpdateCity").on('click',function(){
    		document.CityForm.action = "<%=request.getContextPath()%>/location/updatecity";
			document.StateForm.method = "post";
   	    	document.CityForm.submit();
    	});
    	$(".sidebar-toggle").on('click',function(){
    		$(".sidebar").toggle();
    	});
    	
    	$(".delState").on('click',function(){
    		$(this).closest("tr").addClass("delrow-border");
    	});
    	
    	$(".delCity").on('click',function(){
    		$(this).closest("tr").addClass("delrow-border");
    	});   
    });
    </script>
    <script>
    	var msg = '${errortext}';
    	<c:if test="${error eq Constant.ERROR_OCCURS_YES}">
			modal.style.display = "block";
			$(".wrapper").addClass("blur");
			p.innerHTML = msg;
		</c:if>
		<c:if test="${error eq Constant.ERROR_OCCURS_NO}">
			modal.style.display = "block";
			$(".wrapper").addClass("blur");
			p.innerHTML = msg;
		</c:if>
	</script>
</body>
</html>