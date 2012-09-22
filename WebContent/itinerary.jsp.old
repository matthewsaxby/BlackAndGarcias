<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.beans.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="comp9321.UserBean"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Itinerary</title>
</head>
<body>
<%@ include file="Header.html" %>
<center>

<h1>Itinerary</h1>

<p> Your itinerary is:
<br><br><br> 

<table border="1">
<tr>
<th>Clash</th>
<th>Picture</th>
<th>Inspection Date</th>
<th>Details</th>
<th>More Info</th>
<th>Remove</th>
</tr>

<c:forEach var="result" items="${user.itinerary}">
<tr>
<td>
<c:choose>
	<c:when test="${result.clash eq 'true'}">
		<h1 style="font-family: calibri; color: red;"> ! </h1>
	</c:when>
</c:choose>



</td>
<td><img src="${result.picAddressMain}" alt="property_image" width="149" height="112"/></td>
<td>${result.inspectionHour}:${result.inspectionMinute}, ${result.inspectionDay}/${result.inspectionMonth}/${result.inspectionYear}</td>
<td>
<p align="left">
<b><c:out value="${result.address},  ${result.suburbPrint}" /><br></b>
<c:out value="${result.numBedrooms} Bedroom and ${result.numBathrooms} Bathroom ${result.typePrint}" /><br>
<c:out value=" ${result.description}" /><br>
</p>
</td>
<td>

<form action='fullInfo.jsp' method='POST'>
<input type="hidden" name="propSearchID" value="${result.propSearchID}" />
<input type='submit' value='Full Info'>
</form>
</td>


<td>
<form action='ControlServlet' method='POST'>
<input type="hidden" name="propSearchIDRemove" value="${result.propSearchID}" />
<input type='submit' value='Remove'>
</form>
</td>
</tr>

</c:forEach>
</table>  

<br>

<form action='searchResults.jsp' method='POST'>
<input type='submit' value='Back to Search Results'>
</form>
<form action='enough.jsp' method='POST'><input type='submit'
	value='End Search'></form>
</center>
</body>
</html>