<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${movieDeets}" var="aMovie">
	<h3>Movie Information</h3>
	<b>Title: </b> <c:out value="${aMovie.title}" /> <br />
	<b>poster: </b> <c:out value="${aMovie.poster}" /> <br />
	<b>movieSynopsis: </b> <c:out value="${aMovie.movieSynopsis}" /> <br />
	<b>currentUserRating: </b> <c:out value="${aMovie.currentUserRating}" /> <br />
	<b>ratingCount: </b> <c:out value="${aMovie.ratingCount}" /> <br />
	<b>releaseDate: </b> <c:out value="${aMovie.releaseDate}" /> <br />
	<b>genres: </b>
		<c:forEach items="${aMovie.genres}" var="genre"> 
			<c:out value="${genre}" /> 
		</c:forEach> <br>
	<b>director: </b> <c:out value="${aMovie.director}" /> <br />
	<b>ageRating: </b> <c:out value="${aMovie.ageRating}" /> <br />
	
	<br><br>
</c:forEach>	
</body>
</html>