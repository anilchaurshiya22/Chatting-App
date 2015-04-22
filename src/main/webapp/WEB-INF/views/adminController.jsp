<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Controller/title>
            <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="nav.jsp" />
        <div style="margin-top: 90px; margin-left : 20px;">
            <table border ="0" cellspacing = "15px" >
                <tr>
                    <td>Full Name</td>
                    <td>UserName</td>
                    <td>Email</td>
                    <td>Gender</td>
                    <td>DOB</td>
                    <td>enable/disable</td>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName} ${user.lastName}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.gender}</td>
                        <td>${user.dob}</td>

                        <td>  
                            <form action = "adminController?id=${user.id}" method ="POST">
                                <c:if test = "${user.active == 'true'}">
                                    <input type ="Submit" value ="Disable" />
                                </c:if>

                                <c:if test = "${user.active != 'true'}">
                                    <input type ="Submit" value ="Enable" />
                                </c:if>
                            </form>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
