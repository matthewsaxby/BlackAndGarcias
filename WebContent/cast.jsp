<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Character Information</h1>

<b>Character: </b> <c:out value="${mcharacter.name}" /> <br />
<b>Diet: </b> <c:out value="${mcharacter.diet}" /> <br />
<b>Sounds: </b> <c:out value="${mcharacter.sounds[0]}" /> <br />
<b><a href="controller?action=comments&character=${mcharacter.name}">Comments</a></b> <br />

</body>
</html>