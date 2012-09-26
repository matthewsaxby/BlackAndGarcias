<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<jsp:useBean id="sessionBean" class="edu.unsw.comp9321.beans.SessionBean"
	scope="session" />
<html>
<head>
<title>Admin Options</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">


</head>
<body>

<c:if test="${sessionBean.userType!=2}">
	<div class="container">
		<div class="hero-unit">
			<h1> Sorry! You aren't allowed to view this page :( </h1>
		</div>
	</div>
</c:if>

<c:if test="${sessionBean.userType==2}">
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">SMDB</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="index.jsp">Home</a></li>
              <li><a href="myProfile.jsp">My Profile</a></li>			<%-- only show this if someone is logged in--%>
              <li><a href="movieSearch.jsp">Search</a></li>
              <li><a href="nowShowing.jsp">Now Showing</a></li>
              <li><a href="comingSoon.jsp">Coming Soon</a></li>
              
              
              
              
            </ul>
            <form class="navbar-form pull-right" action='myProfile.jsp' method='POST'>
              <input class="span2" type="text" placeholder="Username">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn">Sign in</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    
    
    
    <div class="container">
    
	   	<c:if test="${adminResponse!=null}">
	  		 <div class="hero-unit">
	  		 	<h1> <c:out value="${adminResponse}" /> </h1>
	  		 </div>
	   	</c:if>
	   	
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
      	<h2>Add Cinema:</h2>
		<form action='controller' method='POST'>
			<input type="hidden" name="action" value="addCinema">
          	<label> Location: </label>
            <input type="text" name="location" >
            <label> Seating Capacity: </label>
            <input type="text" name="capacity">
            <label>Amenities: </label>
            <select name="amenities" multiple="multiple">
          		<option value="popcorn">Popcorn</option>
          		<option value="atms">ATMs</option>
          		<option value="snackbar">Snack Bar</option>
				<option value="restaurant">Restaurant</option>
				<option value="3d">3D</option>
				<option value="disabled">Disabled Access</option>
				<option value="beanbags">Bean Bags</option>
				<option value="silent">Silent Cinema</option>
          	</select><br>
          	<input type='submit' class="btn" value='Add Cinema'>
        </form>
      </div>
       <div class="hero-unit">
      	<h2>Add Movie:</h2>      	
		<form action='controller' method='POST' enctype="application/x-www-form-urlencoded">
			<input type="hidden" name="action" value="addMovie">
          	<label> Title: </label>
            <input type="text" name="title" >
            <p><label> Poster: </label>
            <input type="file" name="poster"></p>
            <label> Actors and Actresses (comma separated): </label>
            <input type="text" name="actors" >
            <label> Genre(s) (comma separated): </label>
            <input type="text" name="genres" >
            <label> Director: </label>
            <input type="text" name="director" >
            <label> Synopsis: </label>
            <textarea name="synopsis" ></textarea>
            <label> Age Rating: </label>
            <input type="text" name="agerating" >
            <label> Release Date: (YYYY-MM-DD)</label>
            <input type="text" name="releasedate" >
          	<input type='submit' class="btn" value='Add movie'>
        </form>
      </div>
       <div class="hero-unit">
      	<h2><a href="mapMtoC.jsp" >Associate a movie with a cinema</a></h2>      	

      </div>






      <footer>
        <p>&copy; Sydney Movie Database 2012</p>
      </footer>
</div> <!-- /container -->
</c:if>
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap-transition.js"></script>
    <script src="../assets/js/bootstrap-alert.js"></script>
    <script src="../assets/js/bootstrap-modal.js"></script>
    <script src="../assets/js/bootstrap-dropdown.js"></script>
    <script src="../assets/js/bootstrap-scrollspy.js"></script>
    <script src="../assets/js/bootstrap-tab.js"></script>
    <script src="../assets/js/bootstrap-tooltip.js"></script>
    <script src="../assets/js/bootstrap-popover.js"></script>
    <script src="../assets/js/bootstrap-button.js"></script>
    <script src="../assets/js/bootstrap-collapse.js"></script>
    <script src="../assets/js/bootstrap-carousel.js"></script>
    <script src="../assets/js/bootstrap-typeahead.js"></script>
</body>
</html>