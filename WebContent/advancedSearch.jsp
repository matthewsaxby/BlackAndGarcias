<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<jsp:useBean id="sessionBean"
	class="edu.unsw.comp9321.beans.SessionBean" scope="session" />
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
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">SMDB</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="controller?action=home">Home</a></li>
						<c:if test="${sessionBean.userType>0}">
							<li><a href="controller?action=viewProfile">My Profile</a></li>
						</c:if>
						<%-- only show this is someone is logged in--%>
						<li><a href="controller?action=search">Search</a></li>
						<li><a href="controller?action=nowShowing">Now Showing</a></li>
						<li><a href="controller?action=comingSoon">Coming Soon</a></li>
						<c:if test="${sessionBean.userType==2}">
			              	 <li><a href="admin.jsp">Admin</a></li>
			              </c:if>
						<c:if test="${sessionBean.userType>0}">
							<li><a>Welcome, <c:out
										value="${sessionBean.user.username}" />
							</a></li>
							<li><form class="navbar-form pull-right" action='controller'
									method='POST'>
									<input type="hidden" name="action" value="logout" /> <input
										type="hidden" name="source" value="advancedSearch.jsp" />
									<button type="submit" class="btn">Logout</button>
								</form>
						</c:if>
					</ul>
					<c:if test="${sessionBean.userType==0}">
						<form class="navbar-form pull-right" action='controller'
							method='POST'>
							<input type="hidden" name="action" value="login" /> <input
								type="hidden" name="source" value="advancedSearch.jsp" /> <input
								class="span2" type="text" name="username" placeholder="Username">
							<input class="span2" type="password" name="password"
								placeholder="Password">
							<button type="submit" class="btn">Sign in</button>
						</form>
					</c:if>
				</div>
				<!--/.nav-collapse -->
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
		<c:if test="${resultData==null}">
			<!-- if there are no results to serve, then it's searchin' time -->
			<div class="hero-unit">
				<h2>Search</h2>
				<p>Need more options?</p>
				<form action='controller' method='POST'>
					<input type="hidden" name="action" value="search"> 
					<input type="hidden" name="state" value="results"> 
					<input type="text" name="yearrange" placeholder="Year Range: 1900-2100"><br>
					<input type="text" name="title" placeholder="Title"> <label>Actor:</label>
					<select name="actor">
						<option></option>
						<c:forEach items="${actorList}" var="anActor">
							<option>
								<c:out value="${anActor.firstName}" />
								<c:out value="${anActor.lastName}" />
							</option>
						</c:forEach>
					</select> <label>Genre:</label> <select name="genre">
					<option></option>
						<c:forEach items="${genreList}" var="genre">
							<option>
								<c:out value="${genre}" />
							</option>
						</c:forEach>
					</select> <br> <input type='submit' class="btn" value='Search &raquo'>
				</form>
			</div>
		</c:if>

		<c:if test="${resultData!=null}">
		<div class="hero-unit">
			<h1>Results:</h1>
			<c:forEach items="${resultData}" var="movie">
				<div class="thumbnail">
					<div class="row">
						<div class="span6">
							<h2>
								<c:out value="${movie.title}" />
							</h2>
							<c:if test="${movie.currentUserRating>0}">
							<p>
								<b>Current User Rating: </b>
								<c:out value="${movie.currentUserRating}" />
								<br />
							</p>
							</c:if>
							<p>
								<b>Number of Reviews: </b>
								<c:out value="${movie.ratingCount}" />
								<br />
							</p>
							<p>
								<b>Genres: </b>
								<c:forEach items="${movie.genres}" var="genre">
									<c:out value="${genre}" />
								</c:forEach>
								<br>
							</p>
							<div class="row">
								<div class="span2">
									<form action='controller' method='POST'>
		        					<input type="hidden" name="action" value="details" >
		        					<input type="hidden" name="viewDetailsOnMovie" value="<c:out value="${movie.id}" />">
	        						<input type='submit' class="btn btn" value='View Details'>
	        						</form>
								</div>
							</div>
						</div>
						<div class="span3">
							<img src="/BlackAndGarcias/<c:out value="${movie.poster}" />"
								alt="property_image" />
						</div>
					</div>
				</div>
				<br>
			</c:forEach>
		</div>
		</c:if>


		<footer>
		<p>&copy; Sydney Movie Database 2012</p>
		</footer>
	</div>
	<!-- /container -->

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