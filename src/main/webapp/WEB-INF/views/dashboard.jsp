<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <h1>${message}</h1>	
        <h1>Friend Request List</h1>
        <table>
            <tr>
                <td>Username</td>
                <td>Send Date</td>
                <td>Message</td>
                <td>Action</td>
            </tr>
           
            <c:forEach var="friendRequest" items="${friendRequests}">
                <tr>
                    <td>${friendRequest.sender.username}</td>
                    <td>${friendRequest.sendDate}</td>
                    <td>${friendRequest.message}</td>


<!--                    <td><a href="friendRequests/accept/${friendRequest.id}">Accept</a></td>
                    <td><a href="friendRequests/decline/${friendRequest.id}">Decline</a></td>-->
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
        </table>

    </body>
</html>