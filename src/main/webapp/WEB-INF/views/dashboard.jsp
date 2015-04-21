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
        <h1>Books currently in the shop</h1>
        <table>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.ISBN}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <td><a href="books/${book.id}">edit</a></td>
                    </sec:authorize>

                </tr>
            </c:forEach>
        </table>
        <sec:authorize url="/addBook" >
            <a href="addBook"> Add a Book</a>
        </sec:authorize>
        <a href="j_spring_security_logout">logout</a>
    </body>
</html>