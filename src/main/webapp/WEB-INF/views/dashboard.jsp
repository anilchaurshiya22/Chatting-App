<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
        <title>Dashboard</title>
    </head>
    <body>
        <jsp:include page="nav.jsp" />
        <div style="margin-top: 90px; margin-left : 20px;">

            <div style="width:50%; float:left; border : 0px solid">
                <h1>${message}</h1>	
                
            </div>
            <div style="width:50% ; float:right; border-left : 1px solid;padding : 10px; ">
                <u><h1>Friend Request List</h1></u>
                <c:if test="${!empty friendRequests }">
                    <table border = "0" style=" font-size : 15px ; border-spacing: 10px;border-collapse: separate;" >
                        <tr>
                            <th>Username</th>
                            <th>Send Date</th>
                            <th>Message</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var="friendRequest" items="${friendRequests}">
                            <tr>
                                <td>${friendRequest.sender.username}</td>
                                <fmt:formatDate var = "formatDate" value="${friendRequest.sendDate}" pattern="MM/dd/yyyy" />
                                <td>${formatDate}</td>
                                <td>${friendRequest.message}</td>

                                <td>
                                    <form action="friendRequests/accept?id=${friendRequest.id}" method="post">
                                        <button type="submit">Accept</button>
                                    </form>
                                </td> <td>
                                    <form action="friendRequests/decline?id=${friendRequest.id}" method="post">
                                        <button type="submit">Decline</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty friendRequests }">
                        <h3>No Request Available</h3>
                    </c:if>
                </table>
                </div>
            </div>
    </body>
</html>