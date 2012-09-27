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
              <c:if test="${sessionBean.userType>0}"><li><a href="controller?action=viewProfile">My Profile</a></li></c:if>			<%-- only show this is someone is logged in--%>
              <li><a href="controller?action=search">Search</a></li>
              <li><a href="controller?action=nowShowing">Now Showing</a></li>
              <li><a href="controller?action=comingSoon">Coming Soon</a></li>
            <c:if test="${sessionBean.userType>0}">
			  	<li><a>Welcome, <c:out value="${sessionBean.user.username}" /> </a></li>
			  	<li><form class="navbar-form pull-right" action='controller' method='POST'>
			  			<input type="hidden" name="action" value="logout" />
	            		<input type="hidden" name="source" value="admin.jsp" />
	            		<button type="submit" class="btn">Logout</button>
            		</form>
	    	  </c:if>
    		</ul>
    		<c:if test="${sessionBean.userType==0}">
	            <form class="navbar-form pull-right" action='controller' method='POST'>
	            	<input type="hidden" name="action" value="login" />
	            	<input type="hidden" name="source" value="admin.jsp" />
	              <input class="span2" type="text" name="username" placeholder="Username">
	              <input class="span2" type="password" name="password" placeholder="Password">
	              <button type="submit" class="btn">Sign in</button>
	            </form>
            </c:if>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    
    
    
    <div class="container">
    
    <c:if test="${sessionBean.userType!=2}">
		<div class="container">
			<div class="hero-unit">
				<h1> Sorry! You aren't allowed to view this page :( </h1>
			</div>
		</div>
	</c:if>
	
	<c:if test="${sessionBean.userType==2}">
    
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
          	<label> Name: </label>
            <input type="text" name="name" >
            <label> Location: </label>
            <input type="text" name="location" >
            <label> Seating Capacity: </label>
            <input type="text" name="capacity">
            <label>Amenities: </label>
       		<input type="checkbox" name="amenities" value="popcorn">Popcorn<br>
       		<input type="checkbox" name="amenities" value="atms">ATMs<br>
       		<input type="checkbox" name="amenities" value="snackbar">Snack Bar<br>
			<input type="checkbox" name="amenities" value="restaurant">Restaurant<br>
			<input type="checkbox" name="amenities" value="3d">3D<br>
			<input type="checkbox" name="amenities" value="disabled">Disabled Access<br>
			<input type="checkbox" name="amenities" value="beanbags">Bean Bags<br>
			<input type="checkbox" name="amenities" value="silent">Silent Cinema<br>
			<br>
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
            <input type="text" name="releasedate" ><br>
          	<input type='submit' class="btn" value='Add movie'>
        </form>
      </div>
       <div class="hero-unit">
      	<form action="controller" method="POST" >
			<input type="hidden" name="action" value="mapMtoC">    
			<input type='submit' class="btn btn" value='Map Movie to Cinemas'>  	
      	</form>  
      </div>



		</c:if>


      <footer>
        <p>&copy; Sydney Movie Database 2012</p>
      </footer>
</div> <!-- /container -->

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