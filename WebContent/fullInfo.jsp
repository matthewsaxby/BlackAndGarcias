<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="comp9321.UserBean"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Property Information</title>



</head>
 <body onload="initialize()" onunload="GUnload()" >
<%@ include file="Header.html" %>
<center>

<br>
<h1>Property Details:</h1> 
<br> 

<script>onload=findLocation('356 Anzac Prd');</script>
<h2><%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getAddress() %> , 
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getSuburbPrint() %>
</h2>
<h3><%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getNumBedrooms() %> Bedroom 
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getNumBathrooms() %> Bathroom
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getTypePrint() %>
</h3>

<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getDescription() %><br>

Open for Inspection:
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getInspectionHour() %>:<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getInspectionMinute() %> , 
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getInspectionDay() %>/ 
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getInspectionMonth() %>/ 
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getInspectionYear() %><br>

Rental Price: $
<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getRent() %> a week<br>

Other Amenities:
<% for(int i = 0; i < user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getOtherAmenitiesList().size(); i++){%>
	<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getOtherAmenitiesList().elementAt(i)%>, 
<%} %>


<br><br>
<b>Pictures:</b>
<br>
<% for(int i = 0; i < user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getPicAddress().size(); i++){%>
	<img src="<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getPicAddress().elementAt(i) %>" alt="property_image" width="149" height="112"/>
<%} %>


<br>
<br>
<% if(user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getMap() != null){%>
			<b>Map:</b>
			<br>
			<iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="<%= user.getSearchResults().elementAt(Integer.parseInt(request.getParameter("propSearchID"))).getMap() %>">
			</iframe>
			<br>
			<br>

<%} %>







<form action='searchResults.jsp' method='POST'>
<input type='submit' value='Back to Search Results'>
</form>


</p>

</center>
</body>
</html>