<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>WELCOME TO SECURE AREA</title>
    </head>
    <body>
        <h1>Message : ${message}</h1>	
        <h1>Author : ${author}</h1>	
        <a href='<c:url value="/sendRequest" />' > Send Friend Request</a> <br/>
        <sec:authorize access="hasRole('ROLE_ADMIN')" >
            <a href='<c:url value="/adminController" />'> Manage Users</a>
        </sec:authorize>
        <br/>
        <a href='<c:url value="/chat" />' > Chat</a><br/>
        <a href='<c:url value="/edit" />' > Edit Profile</a><br/>

        <a href='<c:url value="/j_spring_security_logout" />' var="logoutUrl"> Logout</a>
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
    </body>
</html>