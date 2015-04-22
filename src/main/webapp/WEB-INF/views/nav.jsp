<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" conwtent="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="resources/css/bootstrap.css"/>
    </head>
    <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href='<c:url value="/index" />' > Dash Board </a></li>
                    <li><a href='<c:url value="/sendRequest" />' > Send Friend Request</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <li><a href='<c:url value="/adminController" />'>Manage Users</a></li>
                    </sec:authorize>
                    <li><a href='<c:url value="/chat" />' > Chat</a></li>
                    <li><a href='<c:url value="/edit" />' > Edit Profile </a></li>
                    <li><a href='<c:url value="/friendList" />' > Friends </a></li>
                </ul>
                
                <ul class="nav navbar-nav navbar-right">
                     <li><a href='<c:url value="/j_spring_security_logout" />' var="logoutUrl"> Logout</a></li>
                </ul>
            </div>
                
                
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </div>
    </nav>
</body>

</html>