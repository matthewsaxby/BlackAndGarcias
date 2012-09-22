<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.beans.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="edu.unsw.comp9321.beans.User"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<link href="bootstrap\css\bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="Header.html" %>
<center>

<h1>Search Results</h1>
<script src="js/bootstrap.min.js"></script>
<p> Your search results are:
<br><br><br> 

.thumbnail p {
height: 50px;
overflow: hidden;
}

<ul class="thumbnails">
  
  <c:forEach var="result" items="${user.searchResults}">
  <li class="span4">
    <div class="thumbnail">
      	<img src="${result.picAddressMain}" alt="property_image" width="149" height="112"/>
     	
      	<p><p align="left">
		<h3><c:out value="${result.address},  ${result.suburbPrint}" /></h3>
		<c:out value="${result.numBedrooms} Bedroom and ${result.numBathrooms} Bathroom ${result.typePrint}" />
		<c:out value=" ${result.description}" />
      	</p>
      	
      	<form action='fullInfo.jsp' method='POST'>
		<input type="hidden" name="propSearchID" value="${result.propSearchID}" />
		<input type='submit' class="btn btn-primary btn-medium" value='Full Info'>
		</form>
      	<form action='ControlServlet' method='POST'>
		<input type="hidden" name="propSearchID" value="${result.propSearchID}" />
		<input type='submit' class="btn btn-secondary btn-medium" value='Add'>
		</form>
      
    </div>
  </li>
  </c:forEach>

</ul>










<br>

<form action='welcome.jsp' method='POST'><input type='submit'
	value='Search Again'></form>
<form action='itinerary.jsp' method='POST'><input type='submit'
	value='Generate Itinerary'></form>
<form action='enough.jsp' method='POST'><input type='submit'
	value='End Search'></form>
</center>
</body>
</html>