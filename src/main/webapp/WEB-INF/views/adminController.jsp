<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Controller</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="nav.jsp" />

        <div style="margin-top: 90px; margin-left : 20px;">
            <table border = "0" style=" font-size : 20px ; border-spacing: 20px;border-collapse: separate;" >
                <tr>
                    <th>Full Name</th>
                    <th>UserName</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>enable/disable</th>
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
