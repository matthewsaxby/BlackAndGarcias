<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<jsp:useBean id="sessionBean" class="edu.unsw.comp9321.beans.SessionBean"
	scope="session" />
<html>
<head>
<title>Advanced Search</title>
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
	            		<input type="hidden" name="source" value="details.jsp" />
	            		<button type="submit" class="btn">Logout</button>
            		</form>
	    	  </c:if>
    		</ul>
    		<c:if test="${sessionBean.userType==0}">
	            <form class="navbar-form pull-right" action='controller' method='POST'>
	            	<input type="hidden" name="action" value="login" />
	            	<input type="hidden" name="source" value="details.jsp" />
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
    
    <c:if test="${!released}">
	    <div class="container">
			<!-- if there are no results to serve, then it's searchin' time -->
			<div class="hero-unit">
				<div class="row">
					<div class="span6">
						<h2><c:out value="${targetMovie.title}" /></h2>
						<p><c:out value="${targetMovie.movieSynopsis}" /> <br /></p>
						<p><b>Current User Rating: </b> <c:out value="${targetMovie.currentUserRating}" /> <br /></p>
						<p><b>Number of Reviews: </b> <c:out value="${targetMovie.ratingCount}" /> <br /></p>
						<p><b>Release Date: </b> <c:out value="${targetMovie.releaseDate}" /> <br /></p>
						<p><b>Genres: </b><br>
						<c:forEach items="${targetMovie.genres}" var="genre"> 
							<c:out value="${genre}" /> 
						</c:forEach> <br></p>
						<p><b>Director: </b> <c:out value="${targetMovie.director}" /> <br /></p>
						<p><b>Age Rating: </b> <c:out value="${targetMovie.ageRating}" /> <br /></p>
						<p><b>Actors: </b> <br>
						<c:forEach items="${targetMovie.actors}" var="actor"> 
							<c:out value="${actor.firstName}" /><c:out value=" ${actor.lastName}" />
						</c:forEach> <br></p>
						</div>
				<div class="span3">
					<img src="/BlackAndGarcias/<c:out value="${targetMovie.poster}" />" alt="property_image"/>
				</div>
			</div>
		</div>
    </c:if>
     <c:if test="${released}">
	    <div class="container">
			<!-- if there are no results to serve, then it's searchin' time -->
			<div class="hero-unit">
				<div class="row">
					<div class="span6">
						<h2><c:out value="${targetMovie.title}" /></h2>
						<p><c:out value="${targetMovie.movieSynopsis}" /> <br /></p>
						<p><b>Current User Rating: </b> <c:out value="${targetMovie.currentUserRating}" /> <br /></p>
						<p><b>Number of Reviews: </b> <c:out value="${targetMovie.ratingCount}" /> <br /></p>
						<p><b>Release Date: </b> <c:out value="${targetMovie.releaseDate}" /> <br /></p>
						<p><b>Genres: </b><br>
						<c:forEach items="${targetMovie.genres}" var="genre"> 
							<c:out value="${genre}" /> 
						</c:forEach> <br></p>
						<p><b>Director: </b> <c:out value="${targetMovie.director}" /> <br /></p>
						<p><b>Age Rating: </b> <c:out value="${targetMovie.ageRating}" /> <br /></p>
						<p><b>Actors: </b> <br>
						<c:forEach items="${targetMovie.actors}" var="actor"> 
							<c:out value="${actor.firstName}" /><c:out value=" ${actor.lastName}" />
						</c:forEach> <br></p>
						
						<h2>Cinemas:</h2>
						<c:forEach items="${relatedCinemas}" var="cinema">
							<p>
								<c:out value="${cinema.name}"></c:out> 
								<form action='controller' method='POST'>
		        					<input type="hidden" name="action" value="makeBooking">
		        					<input type="hidden" name="buyTicketsMovieId" value="<c:out value="${targetMovie.id}" />">
		        					<input type="hidden" name="buyTicketsCinemaId" value="<c:out value="${cinema.id}" />">
		        					<input type='submit' class="btn btn-primary" value='Buy Tickets'>
	        					</form>
							</p>
						</c:forEach>
					</div>
					<div class="span3">
						<img src="/BlackAndGarcias/<c:out value="${targetMovie.poster}" />" alt="property_image"/>
					</div>
					
				</div>
			</div>
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