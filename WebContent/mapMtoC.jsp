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
	            		<input type="hidden" name="source" value="index.jsp" />
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
		<div class="hero-unit">
			<h1> Sorry! You aren't allowed to view this page :( </h1>
		</div>
	</c:if>
	
	<c:if test="${sessionBean.userType==2}">
    
	   <c:if test="${state==0}">
	      <!-- Main hero unit for a primary marketing message or call to action -->
	      <div class="hero-unit">
			<form action="controller" method="POST" >
				<input type="hidden" name="state" value="1">
				<input type="hidden" name="action" value="mapMtoC">
				
		      	<select name="movie">
		   			<c:forEach items="${movies}" var="movie">
						<option value=<c:out value="${movie.id}"/>><c:out value="${movie.title}" /></option>
					</c:forEach>
		      	</select>
		      	
		      	<%int i = 0; %>
		      	<c:forEach items="${cinemas}" var="cinema">
			      	<div class="row">
			      		
						<div class="span3">
							<p> Cinema: </p>
							<select name="cinemaSelect">
							<option></option>
							<c:forEach items="${cinemas}" var="cinemaName">
								<option value=<c:out value="${cinemaName.id}"/>><c:out value="${cinemaName.name}" /></option>
							</c:forEach>
							</select><br>
						</div>
						<div class="span3">
							<p> Showtimes:  </p>
							<input type="text" name="showtime<%=i %>" >
							<input type="text" name="showtime<%=i %>" >
							<input type="text" name="showtime<%=i %>" >
						</div>
						<div class="span3">
							<br><br>
							<input type="text" name="showtime<%=i %>" >
							<input type="text" name="showtime<%=i %>" >
							<input type="text" name="showtime<%=i %>" >
							
						</div>
						
					</div>
					<% i++; %>
				</c:forEach>
			<div><button  type="submit" class="btn btn-primary btn-large">Add Showtimes</button></div>
		</form>
		<br>
	    <h6> Note: showtime format = YYYY-MM-DD HH:MM:SS </h6>
	     	
	    </div>
	    	
    	</c:if>
       <c:if test="${state>0}">
			<!--  display confirmation page -->
			<div class="hero-unit">
	  		 	<h1> Showtimes Added! </h1><br>
	  		 	<form action="controller" method="POST" >
					<input type="hidden" name="state" value="0">
					<input type="hidden" name="action" value="mapMtoC">
		  		 	<button  type="submit" class="btn btn-primary btn-large">Add More Showtimes</button>
	  		 	</form>
	  		 </div>
		</c:if>


	</c:if>


      <footer>
        <p>&copy; Sydney Movie Database 2012</p>
      </footer>
</div> <!-- /container -->

<script language="javascript">
		fields = 0;
		function addShowtime() {
		if (fields != 10) {
		document.getElementById('text').innerHTML += "<input type='text' name='showtime"+fields+"' ><br />";
		fields += 1;
		} else {
		document.getElementById('text').innerHTML += "<br />Only 10 upload fields allowed.";
		document.form.add.disabled=true;
		}
		}
</script>
<!--  javascript courtesy of : http://forum.codecall.net/topic/40444-javascript-to-add-a-text-input-field/#axzz27g8nTfw2 -->
		<script language="javascript">
		fields = 0;
		function addInput() {
		if (fields != 10) {
		document.getElementById('text').innerHTML += "<select><c:forEach items='${cinemas}' var='cinema'><option value=<c:out value='${cinema.id}"+fields+"'/>><c:out value='${cinema.name}' /></option></c:forEach></select><br>"; //"<input type='file' value='' /><br />";
		fields += 1;
		} else {
		document.getElementById('text').innerHTML += "<br />Only 10 upload fields allowed.";
		document.form.add.disabled=true;
		}
		}
		</script>
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