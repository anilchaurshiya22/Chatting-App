<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget Password</title>
        <style>
            .error{
                color : #ff0000;
            }
        </style>
    </head>
    <body>
        <form:form action="forgetPassword" method="POST" commandName="user">
            <table>
                <tr> 
                    <td>E-mail:</td>
                    <td><form:input path="email" value="bekrampradhan@gmail.com" /></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="3" align="center"><input type="submit" value="Reset Password" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
