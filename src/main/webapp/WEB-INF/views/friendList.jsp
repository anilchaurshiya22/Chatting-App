<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friend List</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="nav.jsp" />

        <div style="margin-top: 90px; margin-left : 20px;">
            <h1>Friend List</h1>
            <c:if test="${!empty friends }">
                <table>
                    <tr>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Date of Birth</td>
                        <td>Email</td>
                        <td>Username</td>
                    </tr>

                    <c:forEach var="friend" items="${friends}">

                        <tr>
                            <td>${friend.firstName}</td>
                            <td>${friend.lastName}</td>
                            <td>${friend.dob}</td>
                            <td>${friend.email}</td>
                            <td>${friend.username}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty friends }">
                    <h3>No Friends</h3>
                </c:if>

            </table>
        </div>
    </body>
</html>
