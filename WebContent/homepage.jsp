<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home | BlogApp</title>
</head>
<body>
<p> Jullay! </p>
<form method="post" id="loginForm" action="/BlogApp/Controller?action=dosignin">
	<input type = "hidden" name="validationMessageBox" id="validationMessageBox"/><br/><br/>
	Email: <input type="textbox" name="email"/></br>
	Password: <input type="textbox" name="password"/></br>
	<input type="submit" id="submitLogin" value="Sign In"/>
	<a href="/BlogApp/Controller?action=signup">Sign Up</a>
</form>
</body>
</html>