<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.logic.*, edu.unsw.comp9321.jdbc.*, java.util.*"%>
<jsp:useBean id="sessionBean" class="edu.unsw.comp9321.beans.SessionBean"
	scope="session" />
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
              <li class="active"><a href="controller?action=home">Home</a></li>
              <c:if test="${sessionBean.userType>0}"><li><a href="controller?action=viewProfile">My Profile</a></li></c:if>			<%-- only show this is someone is logged in--%>
              <li><a href="controller?action=search">Search</a></li>
              <li><a href="controller?action=nowShowing">Now Showing</a></li>
              <li><a href="controller?action=comingSoon">Coming Soon</a></li>
              <c:if test="${sessionBean.userType==2}">
              	 <li><a href="admin.jsp">Admin</a></li>
              </c:if>
             <c:if test="${sessionBean.userType>0}">
			  	<li><a>Welcome, <c:out value="${sessionBean.user.username}" /> </a></li>
			  	<li><form class="navbar-form pull-right" action='controller' method='POST'>
			  			<input type="hidden" name="action" value="logout" />
	            		
	            		<button type="submit" class="btn">Logout</button>
            		</form>
	    	  </c:if>
    		</ul>
    		<c:if test="${sessionBean.userType==0}">
	            <form class="navbar-form pull-right" action='controller' method='POST'>
	            	<input type="hidden" name="action" value="login" />
	            	
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
    <c:if test="${sessionBean.user!=null}">
			<c:if test="${sessionBean.user.userType==0}">
	     		 <div class="alert alert-error">
	   				<h4>Error!</h4>
	   				You're Account is unconfirmed. Please confirm via Email!
		 		 </div>
			</c:if>
		</c:if>
    
    <c:if test="${sessionBean.userType==0}">
		<div class="container">
			<div class="hero-unit">
				<h1> Sorry! You aren't allowed to view this page :( </h1>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionBean.userType>0}">
    
    	<c:if test="${state>0}">
	      <div class="hero-unit">
	        <h1>Your Details have been updated</h1>
	        <p>Thankyou for keeping your profile up to date</p>
	      </div>
		</c:if>

   		<c:if test="${state==0}">
   		
   			<c:if test="${errormsg!=null }">
   				 <div class="alert alert-error">
    				<h4>Error!</h4>
    				${errormsg}
   		 		 </div>
   			</c:if>
	      <!-- Main hero unit for a primary marketing message or call to action -->
	      <div class="hero-unit">
	        <h1>Edit Your Profile</h1>
	        <p>Change your details below</p>
	        <p>	</p>
	        <br>
	        <form action='controller' method='POST'>
	    	  <input type="hidden" name="action" value="editProfile">
	    	  <input type="hidden" name="state" value="1">
	    	  
	    	  <h4>Account Details:</h4>
	          <label>Username:</label>
	          <b>${sessionBean.user.username}</b>
	          <label>Password:</label>
			  <input type="password" name="password">
	          <label>Repeat Password:</label>
			  <input type="password" name="repeatPassowrd">
			  <h4>Personal Details:</h4>
			  <label>Name:</label>
			  <input type="text" name="firstName" placeholder="${sessionBean.user.firstName}"> <input type="text" name="lastName" placeholder="${sessionBean.user.lastName}">
	          <label>Nick Name:</label>
			  <input type="text" name="nickName" placeholder="${sessionBean.user.nickName}">
	          <label>Year of Birth:</label>
			  <input type="text" name="yearOfBirth" placeholder="${sessionBean.user.yearOfBirth}">
			  <label>Email Address:</label>
			  <input type="text" name="emailAddress" placeholder="${sessionBean.user.emailAddress}">
	          <label>Favourite Actors:</label>
			  <select multiple="multiple">
				<c:forEach items="${actorList}" var="anActor">
					<option><c:out value="${anActor.firstName}" /> <c:out value="${anActor.lastName}" /></option>
				</c:forEach>
			  </select>
			  <label>Favourite Genres:</label>
			  <select multiple="multiple">
				<c:forEach items="${genreList}" var="genre">
					<option><c:out value="${genre}" /></option>
				</c:forEach>
			  </select>
	          
	          <p>
	          <input type='submit' class="btn-primary btn-large" value='Update'>
	        </form>
	        
	      </div>
      </c:if>

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