<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="comp9321.*"%>
<jsp:useBean id="user" class="comp9321.User"
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
        <h1>Coming Soon</h1>
        <p>The next best thing to hit the screen</p>
        <br>
        <!-- The next ten movies to be released-->
        <ul class="thumbnails">
        <li class="span10">
    		<div class="thumbnail">
    			<div class="row">
    				<div class="span6">
        				<h2>Transformers <small>2011</small></h2>
        				
        				<p>An ancient struggle reerupts on Earth between two extraterrestrial clans, the heroic Autobots and the evil Decepticons, with a clue to the ultimate power held by a young teenager. </p>
        				<p><b>Release Date:</b>21/12/2011</p>
        				<p><b>Stars:</b>Shia LaBeouf, Megan Fox and Josh Duhamel</p>
        				<div class="row">
	        				<div class="span2">
	        					<form action='ControlServlet' method='POST'>
	        					<input type="hidden" name="viewDetailsOnMovie" value="DYNAMIC_INSERT">
        						<input type='submit' class="btn btn" value='View Details'>
        						</form>
        					</div>
	        				<div class="span3">
	        					<form action='ControlServlet' method='POST'>
	        					<input type="hidden" name="buyTicketsMovieName" value="DYNAMIC_INSERT">
	        					<input type='submit' class="btn btn-primary" value='Buy Tickets'>
	        					</form>
        					</div>
        				</div>
        			</div>
        			<div class="span3">
        			<img src="pics/transformers.jpg" alt="property_image"/>
        			</div>
        		</div>
        	</div>
        	<br>
        	<div class="thumbnail">
    			<div class="row">
    				<div class="span6">
        				<h2>Transformers <small>2011</small></h2>
        				
        				<p>An ancient struggle reerupts on Earth between two extraterrestrial clans, the heroic Autobots and the evil Decepticons, with a clue to the ultimate power held by a young teenager. </p>
        				<p><b>Release Date:</b>21/12/2011</p>
        				<p><b>Stars:</b>Shia LaBeouf, Megan Fox and Josh Duhamel</p>
        				<div class="row">
	        				<div class="span2">
	        					<form action='ControlServlet' method='POST'>
	        					<input type="hidden" name="viewDetailsOnMovie" value="DYNAMIC_INSERT">
        						<input type='submit' class="btn btn" value='View Details'>
        						</form>
        					</div>
	        				<div class="span3">
	        					<form action='ControlServlet' method='POST'>
	        					<input type="hidden" name="buyTicketsMovieName" value="DYNAMIC_INSERT">
	        					<input type='submit' class="btn btn-primary" value='Buy Tickets'>
	        					</form>
        					</div>
        				</div>
        			</div>
        			<div class="span3">
        			<img src="pics/transformers.jpg" alt="property_image"/>
        			</div>
        		</div>
        	</div>
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