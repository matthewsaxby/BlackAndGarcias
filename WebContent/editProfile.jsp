<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<title>Sign up</title>
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
              <li><a href="myProfile.jsp">My Profile</a></li>			<%-- only show this is someone is logged in--%>
              <li><a href="advancedSearch.jsp">Search</a></li>
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
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Edit Your Profile</h1>
        <p>Change your details below</p>
        <p>	</p>
        <br>
        <form action='controller' method='POST'>
    	  <input type="hidden" name="action" value="editProfile">
    	  
    	  <h4>Account Details:</h4>
          <label>Username:</label>
          <b>${user.username}</b>
          <label>Password:</label>
		  <input type="password" name="password">
          <label>Repeat Password:</label>
		  <input type="password" name="repeatPassowrd">
		  <h4>Personal Details:</h4>
		  <label>Name:</label>
		  <input type="text" name="firstName" placeholder="${user.firstName}"><input type="text" name="lastName" placeholder="${user.lastName}">
          <label>Nick Name:</label>
		  <input type="text" name="nickName" placeholder="${user.nickName}">
          <label>Year of Birth:</label>
		  <input type="text" name="yearOfBirth" placeholder="${user.yearOfBirth}">
		  <label>Email Address:</label>
		  <input type="text" name="emailAddress" placeholder="${user.emailAddress}">
          <label>Favourite Actors:</label>
		  <select multiple="multiple">
			<c:forEach items="${actorList}" var="anActor">
				<option><c:out value="${anActor.firstName}" /> <c:out value="${anActor.lastName}" /></option>
			</c:forEach>
		  </select>
		  <label>Favourite Genres:</label>
		  <select multiple="multiple">
			<option> to do</option>
		  </select>
          
          <p>
          <input type='submit' class="btn-primary btn-large" value='Update'>
        </form>
        
      </div>

      





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