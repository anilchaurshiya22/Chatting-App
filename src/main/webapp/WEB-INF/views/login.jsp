<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <head>
        <title>Login Page </title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload='document.f.j_username.focus();'>
        <div id="login-box">
            <h3>Login Here</h3>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>



            <form name='f' action="<c:url value='j_spring_security_check' />"
                  method='POST'>

                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='j_username' value=''>
                        </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='j_password' />
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit"
                                               value="Login" />
                        </td>
                    </tr>                    
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
            
            <a href="register">Register </a> | <a href="forgetPassword">Forget Password </a>
            
        </div>
    </body>
</html>