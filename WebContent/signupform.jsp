<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up | BlogApp</title>
</head>
<body>
	<h2>Sign up </h2>
	<% 
			String message = "";
			if((String)request.getAttribute("message") != null) {
				message = (String)request.getAttribute("message");
			}
	%>
	<form method="post" id="signUpForm" action="/BlogApp/Controller?action=dosignup">
		<label name="validationMessageBox" id="validationMessageBox" value="<%= message%>" /> 
		<br/><br/>
		Email: <input type="textbox" name="email" id="email"/></br>
		Password: <input type="textbox" name="password" id="password"/></br>
		Re-enter Password: <input type="textbox" name="repassword" id="repassword"/></br>
		First Name: <input type="textbox" name="fname" id="fname"/><br/>
		Last Name: <input type="textbox" name="lname" id="lname"/><br/>
		<input type="submit" id="submitSignUp" value="Sign Up"/>
		<a href="/BlogApp/Controller?action=signup">Sign Up</a>
	</form>
</body>
</html>