<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Do you want info on ?</h1>
<form action="controller">
<input TYPE="radio" name="character" value="zombies" /> Zombies <br />
<input type="radio" name="character" value="pirates" /> Pirates <br />
<input type="radio" name="character" value="ninjas" /> Ninjas <br />
<input type="radio" name="character" value="vampires" /> vampires <br />
Also
<input type="radio" name="action" value="character" /> Character Info <br /> or 
<input type="radio" name="action" value="comments" /> Comments <br />
<input TYPE="submit" VALUE="View" />
</form>
</body>
</html>