<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<jsp:useBean id="sessionBean"
	class="edu.unsw.comp9321.beans.SessionBean" scope="session" />
<html>
<head>
<title>Checkout</title>
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
    <c:if test="${sessionBean.userType==0}">
		<div class="container">
			<div class="hero-unit">
				<h1> Sorry! You aren't allowed to view this page :( </h1>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionBean.userType>0}">
    
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Booking Details</h1>
        <p>Fill in your booking details below</p>
        
        <c:if test="${isError==1}">
        <div class="alert alert-error">
    			<h4>Error!</h4>
    			${error}
   		</div>
        </c:if>
        
        
        <p><b>Movie:</b> ${movie.title}</p>
        <p><b>Cinema:</b> ${cinema.name}</p>
        
        <form action='controller' method='POST'>
        <input type="hidden" name="action" value="checkout">
        <input type="hidden" name="movieID" value="${movie.id}">
        <input type="hidden" name="cinemaID" value="${cinema.id}">
        
        <label>Select show time:</label>
        <select name='showTime' size="1">
			<c:forEach items="${showTimes}" var="aSession">
					<option><c:out value="${aSession.showingDate}" /> <c:out value="${aSession.showingTime}" /></option>
				</c:forEach>
		</select>
        
        <label>Number of adults:</label>
        <select name='numAdults' size="1">
			<option>0</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
		</select>
		<label>Number of concessions:</label>
        <select name='numConcessions' size="1">
			<option>0</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
		</select>
		<label>Number of children:</label>
        <select name='numChildren' size="1">
			<option>0</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
		</select>
        
        <label>Name on crdit card:</label>
		<input type="text" name="creditcardName">
		<label>Credit card number:</label>
		<input type="text" name="creditcardNumber">
		<label>CSV:</label>
		<input type="text" name="creditcardCSV">
		<br>
        
        <input type='submit' class="btn btn-primary" value='Book &raquo'>
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