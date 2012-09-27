<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.logic.*, java.util.*"%>
<jsp:useBean id="sessionBean" class="edu.unsw.comp9321.beans.SessionBean"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<title>Coming Soon</title>
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
	            		<input type="hidden" name="source" value="comingSoon.jsp" />
	            		<button type="submit" class="btn">Logout</button>
            		</form>
	    	  </c:if>
    		</ul>
    		<c:if test="${sessionBean.userType==0}">
	            <form class="navbar-form pull-right" action='controller' method='POST'>
	            	<input type="hidden" name="action" value="login" />
	            	<input type="hidden" name="source" value="comingSoon.jsp" />
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
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Coming Soon</h1>
        <p>The next best thing to hit the screen</p>
        <br>
        <!-- The next ten movies to be released-->
        <ul class="thumbnails">
        <li class="span10">
    		<c:forEach items="${movieDeets}" var="aMovie">
				<div class="thumbnail">
					<div class="row">
						<div class="span6">
							<h2><c:out value="${aMovie.title}" /></h2>
							<p><c:out value="${aMovie.movieSynopsis}" /> <br /></p>
							<p><b>Release Date: </b> <c:out value="${aMovie.releaseDate}" /> <br /></p>
							<p><b>Genres: </b>
							<c:forEach items="${aMovie.genres}" var="genre"> 
								<c:out value="${genre}" /> 
							</c:forEach> <br></p>
							<div class="row">
		        				<div class="span2">
		        					<form action='controller' method='POST'>
		        					<input type="hidden" name="action" value="<c:out value="viewFullDetails" />">
		        					<input type="hidden" name="viewDetailsOnMovie" value="<c:out value="${aMovie.title}" />">
	        						<input type='submit' class="btn btn" value='View Details'>
	        						</form>
	        					</div>
		        				<div class="span3">
		        					<form action='controller' method='POST'>
		        					<input type="hidden" name="action" value="<c:out value="buyTickets" />">
		        					<input type="hidden" name="buyTicketsMovieName" value="<c:out value="${aMovie.title}" />">
		        					<input type='submit' class="btn btn-primary" value='Buy Tickets'>
		        					</form>
	        					</div>
        					</div>
        				</div>
        				<div class="span3">
        					<img src="/BlackAndGarcias/<c:out value="${aMovie.poster}" />" alt="property_image"/>
        				</div>
        			</div>
				</div>			
				<br>			
			</c:forEach>
  		</li>
  		</ul>
  		
      </div>
      


<br>
<br>


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