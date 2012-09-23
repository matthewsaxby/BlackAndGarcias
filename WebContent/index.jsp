<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<title>SMDB Homepage</title>
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
      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit">
        <h1>Welcome to the SMDB!</h1>
        <p>Becoming a member is currently free, click the join now button to become a member now.</p>
        <form action='signup.jsp' method='POST'>
        <input type='submit' class="btn btn-primary btn-large" value='Join Now &raquo'>
        </form>
      </div>

      

      
      <!-- Example row of columns -->
      <div class="row">

        <div class="span4">
          <h2>Now Showing</h2>

          
          <h4>x Men</h4>
          <div class="row">
          <div class="span2"><p>Blurb about the movie x men</p></div>
          <div class="span2"><img src="pics/transformers.jpg" alt="property_image" width="149" height="112"/></div>
          </div>
          <p>
          <form action='nowShowing.jsp' method='POST'>
          <input type="hidden" name="movie" value="1" />
          <input type='submit' class="btn" value='See more movies &raquo'>
          </form>
       </div>
        <div class="span4">
          <h2>Coming Soon</h2>
	      <h4>x Men</h4>
          <div class="row">
          <div class="span2"><p>Blurb about the movie x men</p></div>
          <div class="span2"><img src="pics/transformers.jpg" alt="property_image" width="149" height="112"/></div>
          </div>
          <p>
          <form action='comingSoon.jsp' method='POST'>
          <input type="hidden" name="movie" value="1" />
          <input type='submit' class="btn" value='See more movies &raquo'>
          </form>
       </div>
        <div class="span4">
          <h2>Search</h2>
		  <p>Can't find what your looking for? Why not search for a movie or a cinema here:</p>
          <form action='search.jsp' method='POST'>
          <select><option>Movie</option><option>Cinema</option></select>
          <input type="text" placeholder="Movie or Cinema">
          <input type='submit' class="btn" value='Search &raquo'>
          </form>
       </div>
      </div>

      <hr>






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