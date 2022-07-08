<%@page import="com.narola.bookmyseat.utility.Constant" %>
<%@page import="com.narola.bookmyseat.utility.Constant" %>
<%@page import="javax.management.modelmbean.ModelMBeanOperationInfo" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="com.narola.bookmyseat.entity.*" %>

<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    User user = (User) session.getAttribute("LoginUser");
    pageContext.setAttribute("user", user);
%>

<%
    if (request.getAttribute("xyz") != null) {
        System.out.println("yes");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/img/icons/icon-48x48.png"/>
    <link rel="canonical" href="https://demo-basic.adminkit.io/"/>
    <title>Movies</title>
    <link href="${pageContext.request.contextPath}/resource/css/app.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resource/css/custom.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
<!-- Add cast crew popupbox -->
<div id="myModalCastCrew" class="myModalCastCrew">
    <!-- Modal content -->
    <div class="modal-contentCastCrew">
        <span class="closeCastCrew" onclick="closePopupAddCastCrew();">&times;</span>
        <p class="msgCastCrew">Add Cast Crew</p>
        <div class="mb-3">
            <label class="form-label">Select Type</label>
            <div>
                <label class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="txtcastCrewType" id="txtcastCrewType" value="0"
                           checked="checked">
                    <span class="form-check-label"></span>Cast</label>
                <label class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="txtcastCrewType" id="txtcastCrewType" value="1">
                    <span class="form-check-label"></span>Crew</label>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Cast Crew</label><span id="errorMsgCastCrew" class="errormsg"></span>
            <select id="txtCastCrew" name="txtCastCrew" class="form-select mb-3">
                <option selected disabled="disabled" value="-1">Select CastCrew</option>
                <c:forEach var="castcrew" items="${CastCrewData}">
                    <option value="${castcrew.getCastCrewId()}">${castcrew.getCastCrewName()}</option>
                </c:forEach>
            </select>
        </div>
        <div id="modal2CastCrew" class="modal-content2CastCrew">
            <button type="button" class="yesBtnCC btn btn-success" id="yesBtnCC">Add</button>
            <button type="button" class="noBtnCC btn btn-secondary" onclick="closePopupAddCastCrew();" id="noBtn">
                Cancel
            </button>
        </div>
    </div>
</div>
<!-- popupbox -->
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close" onclick="closePopup('getmovies');">&times;</span>
        <p class="msg">Some text in the Modal..</p>
        <div id="modal2" class="modal-content2 ">
            <button type="button" class="yesBtn btn btn-danger" id="yesBtn">Delete</button>
            <button type="button" class="noBtn btn btn-secondary" id="noBtn" onclick="closePopup('getmovies');">Cancel
            </button>
        </div>
    </div>
</div>
<!-- Insert-Update-popup -->
<div id="InUpmyModal" class="InUpmodal">
    <!-- Modal content -->
    <div class="InUpmodal-contentMovie">
        <span class="InUpclose" onclick="InUpclosePopup('getmovies');">&times;</span>
        <p class="InUpmsg">Insert Movie</p>
        <hr>
        <div id="InUpmodal2" class="InUpmodal-content2Movie ">
            <form name="MovieForm" method="post" enctype="multipart/form-data">
                <div class="card-body row">
                    <div class="col-4 col-lg-4 col-xxl-4">
                        <div class="mb-3">
                            <label class="form-label">Title</label> <span id="errorMsgMovieName" class="errormsg"><c:if
                                test="${not empty validErrorMovieName}"><c:out
                                value="${validErrorMovieName}"/></c:if></span>
                            <input type="hidden"
                                   value='<c:choose><c:when test = '${not empty afterMovieData.getMovieId()}'><c:out value ='${afterMovieData.getMovieId()}'/></c:when><c:otherwise>${movieDataById.getMovieID()}</c:otherwise></c:choose>'
                                   name="txtMovieId" class="txtMovieId">
                            <input type="text" id="txtMovieName" name="txtMovieName" class="form-control"
                                   placeholder="Enter movie title..."
                                   value="<c:choose><c:when test = '${not empty afterMovieData.getMovieName()}'><c:out value ='${afterMovieData.getMovieName()}'/></c:when><c:when test = '${not empty movieDataById.getMovieTitle()}'><c:out value ='${movieDataById.getMovieTitle()}'/></c:when></c:choose> ">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Release Date</label> <span id="errorMsgReleaseDate"
                                                                                 class="errormsg"><c:if
                                test="${not empty validErrorReleaseDate}"><c:out
                                value="${validErrorReleaseDate}"/></c:if></span>
                            <input type="date" id="txtReleaseDate" name="txtReleaseDate" class="form-control"
                                   value="<c:choose><c:when test = '${not empty afterMovieData.getReleaseDate()}'><c:out value ='${afterMovieData.getReleaseDate()}'/></c:when><c:when test = '${not empty movieDataById.getReleaseDate()}'><c:out value ='${movieDataById.getReleaseDate()}'/></c:when></c:choose>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Duration</label> <span id="errorMsgDuration"
                                                                             class="errormsg"><c:if
                                test="${not empty validErrorDuration}"><c:out
                                value="${validErrorDuration}"/></c:if></span>
                            <input type="time" id="txtDuration" name="txtDuration" class="form-control"
                                   value="<c:choose><c:when test = '${not empty afterMovieData.getDuration()}'><c:out value ='${afterMovieData.getDuration()}'/></c:when><c:when test = '${not empty movieDataById.getDuration()}'><c:out value ='${movieDataById.getDuration()}'/></c:when></c:choose>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Censor Rating</label> <span id="errorMsgCensorRating"
                                                                                  class="errormsg"><c:if
                                test="${not empty validErrorCensorRating}"><c:out
                                value="${validErrorCensorRating}"/></c:if></span>
                            <input type="text" id="txtCensorRating" name="txtCensorRating" class="form-control"
                                   placeholder="Enter censor ratings..."
                                   value="<c:choose><c:when test = '${not empty afterMovieData.getCensorRating()}'><c:out value ='${afterMovieData.getCensorRating()}'/></c:when><c:when test = '${not empty movieDataById.getCensorRating()}'><c:out value ='${movieDataById.getCensorRating()}'/></c:when></c:choose> ">
                        </div>
                    </div>
                    <div class="col-8 col-lg-8 col-xxl-8">
                        <div class="row">
                            <div class="col-6 col-lg-6 col-xxl-6">
                                <div class="mb-3">
                                    <input type="hidden" name="genreCreatedTime"
                                           value="<c:choose><c:when test="${not empty genreCreatedTime }">${genreCreatedTime}</c:when><c:otherwise>${genreDataById.get(0).getCreatedTime()}</c:otherwise></c:choose>">
                                    <label class="form-label">Genre: </label> <span id="selGenre"><c:forEach var="genre"
                                                                                                             items="${GenreData}"><c:forEach
                                        var='item' items='${afterMovieData.getGenre()}'><c:if
                                        test='${item eq genre.getGenreID()}'>${genre.getGenre()},</c:if></c:forEach></c:forEach><c:forEach
                                        var='itemUpdate'
                                        items='${genreDataById}'>${itemUpdate.getGenre()},</c:forEach></span> <span
                                        id="errorMsgGenre" class="errormsg"><c:if
                                        test="${not empty validErrorGenre}"><c:out
                                        value="${validErrorGenre}"/></c:if></span>
                                    <select id="txtGenre" name="txtGenre" size="4" multiple="multiple"
                                            class="form-select mb-3">
                                        <c:forEach var="genre" items="${GenreData}">
                                            <option value="${genre.getGenreID()}"
                                                    <c:forEach var='item' items='${afterMovieData.getGenre()}'>
                                                    <c:if test='${item eq genre.getGenreID()}'>selected</c:if>
                                                    </c:forEach>
                                                        <c:forEach var='itemUpdate' items='${genreDataById}'>
                                                        <c:if test='${itemUpdate.getGenre() eq genre.getGenre()}'>selected</c:if>
                                            </c:forEach>>${genre.getGenre()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Movie Banner</label> &nbsp;<span class="UpdateviewImgB"
                                                                                               onclick="viewImg('updateImgB');">Click to view!</span><img
                                        class="updateImgB"
                                        src="data:image/png;base64,<c:choose><c:when test = '${not empty txtMovieBannerImg}'><c:out value ='${txtMovieBannerImg}'/></c:when><c:otherwise>${imgBannerById}</c:otherwise></c:choose>">
                                    <input type="hidden"
                                           value='<c:choose><c:when test = '${not empty txtMovieBannerImg}'><c:out value ='${txtMovieBannerImg}'/></c:when><c:otherwise>${imgBannerById}</c:otherwise></c:choose>'
                                           name="txtMovieBannerImg" class="txtMovieBannerImg">
                                    <input type="file" id="txtMovieBanner" name="txtMovieBanner"
                                           class="form-control spimgname">
                                </div>
                            </div>
                            <div class="col-6 col-lg-6 col-xxl-6">
                                <div class="mb-3">
                                    <input type="hidden" name="languageCreatedTime"
                                           value="<c:choose><c:when test="${not empty languageCreatedTime }">${languageCreatedTime}</c:when><c:otherwise>${languageDataById.get(0).getCreatedTime()}</c:otherwise></c:choose>">
                                    <label class="form-label">Language: </label> <span id="selLanguage"><c:forEach
                                        var="language" items="${LanguageData}"><c:forEach var='item'
                                                                                          items='${afterMovieData.getLanguage()}'><c:if
                                        test='${item eq language.getLanguageID()}'>${language.getLanguage()},</c:if></c:forEach></c:forEach><c:forEach
                                        var='itemUpdate'
                                        items='${languageDataById}'>${itemUpdate.getLanguage()},</c:forEach></span><span
                                        id="errorMsgLanguage" class="errormsg"><c:if
                                        test="${not empty validErrorLanguage}"><c:out
                                        value="${validErrorLanguage}"/></c:if></span>
                                    <select id="txtLanguage" name="txtLanguage" size="4" multiple="multiple"
                                            class="form-select mb-3">
                                        <c:forEach var="language" items="${LanguageData}">
                                            <option value="${language.getLanguageID()}"
                                                    <c:forEach var='item' items='${afterMovieData.getLanguage()}'>
                                                    <c:if test='${item eq language.getLanguageID()}'>selected</c:if>
                                                    </c:forEach>
                                                        <c:forEach var='item' items='${languageDataById}'>
                                                        <c:if test='${item.getLanguage() eq language.getLanguage()}'>selected</c:if>
                                            </c:forEach> >${language.getLanguage()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Movie Poster</label> &nbsp;<span class="UpdateviewImgP"
                                                                                               onclick="viewImg('updateImgP');">Click to view!</span><img
                                        class="updateImgP"
                                        src="data:image/png;base64,<c:choose><c:when test = '${not empty txtMoviePosterImg}'>${txtMoviePosterImg}</c:when><c:otherwise>${imgPosterById}</c:otherwise></c:choose>">
                                    <input type="hidden"
                                           value='<c:choose><c:when test = '${not empty txtMoviePosterImg}'><c:out value ='${txtMoviePosterImg}'/></c:when><c:otherwise>${imgPosterById}</c:otherwise></c:choose>'
                                           name="txtMoviePosterImg" class="txtMoviePosterImg">
                                    <input type="file" id="txtMoviePoster" name="txtMoviePoster" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 col-lg-12 col-xxl-12">
                                <div class="mb-3">
                                    <input type="hidden" name="castCrewCreatedTime"
                                           value="<c:choose><c:when test="${not empty castCrewCreatedTime }">${castCrewCreatedTime}</c:when><c:otherwise>${castCrewDataById.get(0).getCreatedTime()}</c:otherwise></c:choose>">
                                    <label class="form-label">CastCrew</label>&nbsp;<span class="addCastCrewBtn"
                                                                                          onclick="addCastCrew();">Add</span>
                                    <span id="errorMsgCast" class="errormsg"><c:if
                                            test="${not empty validErrorMovieCastCrew}"><c:out
                                            value="${validErrorMovieCastCrew}"/></c:if></span>
                                    <ul class="addCastCrew" id="addCastCrew">
                                        <c:choose>
                                            <c:when test="${not empty afterMovieData.getCastCrewId()}">
                                                <c:forEach var="i" begin="0"
                                                           end="${fn:length(afterMovieData.getCastCrewId())-1}">
                                                    <div id="${afterMovieData.getCastCrewId()[i]}">
                                                        <input type="hidden" name="txtType"
                                                               value="${afterMovieData.getType()[i]}">
                                                        <input type="hidden" name="txtCastCrewId"
                                                               value="${afterMovieData.getCastCrewId()[i]}">
                                                        <input type="hidden" name="txtcastCrewName"
                                                               value="${txtCastCrewName[i]}">
                                                        <li>${txtCastCrewName[i]}<span class="close"
                                                                                       onclick="removeCastCrew(${afterMovieData.getCastCrewId()[i]});">&times;</span>
                                                        </li>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${not empty castCrewDataById}">
                                                <c:forEach var="castCrew" items="${castCrewDataById}">
                                                    <div id="${castCrew.getCastCrewId()}">
                                                        <input type="hidden" name="txtType"
                                                               value="${castCrew.getType()}">
                                                        <input type="hidden" name="txtCastCrewId"
                                                               value="${castCrew.getCastCrewId()}">
                                                        <input type="hidden" name="txtcastCrewName"
                                                               value="${castCrew.getCastCrewName()}">
                                                        <li>${castCrew.getCastCrewName()}<span class="close"
                                                                                               onclick="removeCastCrew(${castCrew.getCastCrewId()});">&times;</span>
                                                        </li>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="row">
                            <div class="col-9 col-lg-9 col-xxl-9">
                                <label class="form-label">Movie Description</label>
                                <textarea class="form-control" rows="2" id="txtMovieDescription"
                                          name="txtMovieDescription"
                                          placeholder="Enter description..."><c:choose><c:when
                                        test='${not empty txtMovieDescription}'><c:out value='${txtMovieDescription}'/></c:when><c:when
                                        test='${not empty movieDataById.getDescription()}'><c:out
                                        value='${movieDataById.getDescription()}'/></c:when></c:choose></textarea>
                            </div>
                            <div class="col-3 col-lg-3 col-xxl-3 divStatus">
                                <label class="form-label">Status</label>
                                <select id="txtStatus" name="txtStatus" class="form-select mb-3">
                                    <option value="${Constant.STATUS_MOVIE_ACTIVE}" <c:choose> <c:when
                                            test="${movieDataById.getStatus() eq Constant.STATUS_MOVIE_ACTIVE}">selected</c:when>
                                        <c:when test="${txtStatus eq Constant.STATUS_MOVIE_ACTIVE}">selected</c:when></c:choose> >
                                        Active
                                    </option>
                                    <option value="${Constant.STATUS_MOVIE_INACTIVE}" <c:choose> <c:when
                                            test="${movieDataById.getStatus() eq Constant.STATUS_MOVIE_INACTIVE}">selected</c:when>
                                        <c:when test="${txtStatus eq Constant.STATUS_MOVIE_INACTIVE}">selected</c:when></c:choose> >
                                        Inactive
                                    </option>
                                    <option value="${Constant.STATUS_MOVIE_UPCOMING}" <c:choose> <c:when
                                            test="${movieDataById.getStatus() eq Constant.STATUS_MOVIE_UPCOMING}">selected</c:when>
                                        <c:when test="${txtStatus eq Constant.STATUS_MOVIE_UPCOMING}">selected</c:when></c:choose> >
                                        Upcoming
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-12" style="margin-top: 15px;">
                        <div class="md-3">
                            <input type="button" value="ADD" class="btn btn-success btnAddMovie">
                            <input type="button" name="updateCC" value="Update Movie"
                                   class="btn btn-primary btnUpdateMovie">
                            <input type="reset" value="Cancel" class="btn btn-secondary btnSCancel"
                                   onClick="window.location.reload()">
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
                    <a class="sidebar-link" href="${pageContext.request.contextPath}/location/place">
                        <i class="align-middle" data-feather="map-pin"></i> <span class="align-middle">Place</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="CastCrew">
                        <i class="align-middle" data-feather="star"></i> <span class="align-middle">Cast-Crew</span>
                    </a>
                </li>
                <li class="sidebar-item active">
                    <a class="sidebar-link" href="${pageContext.request.contextPath}/movies/getmovies">
                        <i class="align-middle elementToFadeInAndOut" data-feather="film"></i> <span
                            class="align-middle">Movies</span>
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
                            <img src="data:image/png;base64,${user.getProfileImgAsBase64()}"
                                 class="avatar img-fluid rounded me-1" alt="Charles Hall"/> <span
                                class="text-dark">${user.getFirstName()}</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end">
                            <a class="dropdown-item" href="pages-profile.html"><i class="align-middle me-1"
                                                                                  data-feather="user"></i> Profile</a>
                            <a class="dropdown-item" href="LogoutServlet">Log out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <main class="content">
            <div class="container-fluid p-0">
                <h1 class="h3 mb-3"><strong>Movies</strong> Data</h1>
                <div>
                    <ul class="dataviewtop">
                        <li>
                            <button class="btn btn-secondary d-inline" onclick="addNewCC();">Add <i class="align-middle"
                                                                                                    data-feather="plus"></i>
                            </button>
                        </li>
                        <li>
                            <input type="search" id="searchMovie" class="form-control inputsearch"
                                   placeholder="Search movie..." onkeyup="searchMovie();">
                        </li>
                        <li style="float: right;">
                            <span class="badge filterBtnUpcoming">Upcoming</span>
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
                            <th>Poster</th>
                            <th>Title</th>
                            <th>Release Date</th>
                            <th>Duration</th>
                            <th>Censor Rating</th>
                            <th>Status</th>
                            <th colspan="3" style="text-align: center;">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="1" scope="page"/>
                        <c:set var="i" value="0" scope="page"/>
                        <c:forEach var="movie" items="${MovieData}">
                            <tr>
                                <td>${count}</td>
                                <td><a style="color:#495057" href="getmovie?movieId=${movie.getMovieID()}"><img
                                        class="card-img-top dataviewposter" id="dataviewposter"
                                        src="data:image/png;base64,${imgPoster.get(i)}" alt="Unsplash"></a></td>
                                <td>${movie.getMovieTitle()}</td>
                                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${movie.getReleaseDate()}"/></td>
                                <td>${movie.getDuration()}</td>
                                <td>${movie.getCensorRating()}</td>
                                <td><c:choose>
                                    <c:when test="${movie.getStatus() == 0}">
                                        <span style="cursor: pointer;"
                                              onclick="changeStatus(${movie.getMovieID()},${movie.getStatus()});"
                                              class="badge bg-success">Active</span>
                                    </c:when>
                                    <c:when test="${movie.getStatus() == 1}">
                                        <span style="cursor: pointer;"
                                              onclick="changeStatus(${movie.getMovieID()},${movie.getStatus()});"
                                              class="badge bg-warning">Inactive</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="cursor: pointer;"
                                              onclick="changeStatus(${movie.getMovieID()},${movie.getStatus()});"
                                              class="badge bg-primary">Upcoming</span>
                                    </c:otherwise>
                                </c:choose>
                                </td>
                                <td><i class="align-middle delbtn" data-feather="trash-2"
                                       onclick="confirmGo('sure to delete this record?','deletemovie?movieId=${movie.getMovieID()}');"></i>
                                </td>
                                <td><i class="align-middle upbtn" data-feather="edit"
                                       onclick=updateMovie(${movie.getMovieID()});></i></td>
                                <td><a style="color:#495057" href="getmovie?movieId=${movie.getMovieID()}"><i
                                        class="align-middle viewbtn" data-feather="eye"></i></a></td>
                            </tr>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <c:set var="i" value="${i + 1}" scope="page"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </main>
    </div>
</div>
<script src="https://unpkg.com/feather-icons"></script>
<script src="${pageContext.request.contextPath}/source/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/source/js/action.js"></script>
<script src="${pageContext.request.contextPath}/source/js/movies.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    feather.replace()
</script>
<script type="text/javascript">

    $(document).ready(function () {
        //profile dropdown
        $(".dropdown-toggle").on('click', function () {
            $(".dropdown-menu").toggle(100);
        });
        //hide error msg and remove session after 3sec page load
        window.setTimeout(function () {
            $(".errormsg").fadeOut();
        }, 3000);
        //add CastCrew in form
        $("#yesBtnCC").on('click', function () {
            var typeCastCrew = $("input[type=radio][name=txtcastCrewType]:checked").val();
            var castCrew = $("select#txtCastCrew").val();
            var castCrewName = $("#txtCastCrew option:selected").text();
            if (castCrew != null) {
                $("#addCastCrew").append('<div id="' + castCrew + '"><input type="hidden" name="txtType" value="' + typeCastCrew + '"><input type="hidden" name="txtCastCrewId" value="' + castCrew + '"><input type="hidden" name="txtcastCrewName" value="' + castCrewName + '"><li>' + castCrewName + '<span class="close" onclick="removeCastCrew(' + castCrew + ');">&times;</span></li></div>');
                closePopupAddCastCrew();
            } else {
                $("#errorMsgCastCrew").show();
                $("#errorMsgCastCrew span").text("Please select cast-crew!");
            }
        });

        //redirect to InsertMovieServlet for insert
        $(".btnAddMovie").on('click', function () {
            document.MovieForm.action = "<%=request.getContextPath() + Constant.URL_MOVIE_INSERT%>";
            document.MovieForm.submit();
        });
        //redirect to UpdateMovieServlet for update
        $(".btnUpdateMovie").on('click', function () {
            document.MovieForm.action = "<%=request.getContextPath() + Constant.URL_MOVIE_UPDATE%>";
            document.MovieForm.submit();
        });

        //Display Error msg
        <%
        if (request.getAttribute("validErrorMovieInsert") == Constant.ERROR_OCCURS_YES)
        {
        %>
        window.history.replaceState({}, '', 'Movies');
        addNewCC();
        <%
        }
        else if (request.getAttribute("validErrorMovieUpdate") == Constant.ERROR_OCCURS_YES)
        {
        %>
        window.history.replaceState({}, '', 'Movies');
        upCCAfterErrorMovie();
        <%
        }
        %>

        $('#txtGenre').on('change', function () {
            var text = "";
            $("#txtGenre option:selected").each(function () {
                var $this = $(this);
                if ($this.length) {
                    var selText = $this.text();
                    text = text + selText + ",";
                    $('#selGenre').text(text);
                }
            });
        });

        $('#txtLanguage').on('change', function () {
            var text = "";
            $("#txtLanguage option:selected").each(function () {
                var $this = $(this);
                if ($this.length) {
                    var selText = $this.text();
                    text = text + selText + ",";
                    $('#selLanguage').text(text);
                }
            });
        });
        <c:if test="${not empty movieDataById.getMovieID()}">
        window.history.replaceState({}, '', 'Movies');

        updateMovieForm();
        </c:if>

    });

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

    function changeStatus(movieID, Status) {
        if (Status == 1) {
            Status = 2
        } else if (Status == 2) {
            Status = 0
        } else {
            Status = 1
        }
        load_ajax(movieID, Status);
    }

    function load_ajax(movieID, Status) {
        var xhttp;
        xhttp = new XMLHttpRequest();

        xhttp.open("POST", "updatemoviestatus?movieId=" + movieID + "&status=" + Status, true);
        xhttp.send();
        location.reload();
    }

    //searching Movie
    function searchMovie() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("searchMovie");
        filter = input.value.toUpperCase();
        table = document.getElementById("movieTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>
</html>