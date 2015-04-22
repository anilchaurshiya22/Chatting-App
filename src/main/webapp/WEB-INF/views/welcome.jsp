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
        <jsp:include page="nav.jsp" />
        <div style="margin-top: 90px; margin-left : 20px;">
            <h1>${message}</h1>	
            <h1>${author}</h1>	
        </div>
    </body>
</html>