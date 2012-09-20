<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table> 
<tr>
<th>Character</th><th>Comment</th><th> On </th><th> By </th></tr>
<c:forEach items="${comments}" var="mcomment"> 
<tr>
<td>${mcomment.characterName}</td><td>${mcomment.comment}</td><td>${mcomment.commentDate}</td>
<td>${mcomment.user}</td>
</tr>
</c:forEach> </table>
 
<form action="controller" method="post">
<textarea name="comments" cols="40" rows="5">
Add a comment...
</textarea><br>
<input type="hidden" value="postcomment" name="action" />
<input type="hidden" value="${character}" name="character" />
<input type="submit" value="Post" />
Else return to <a href="welcome.jsp">start</a>
</form>
</body>
</html>