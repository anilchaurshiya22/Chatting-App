<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <style>
            .error{
                color : #ff0000;
            }
        </style>
    </head>
    <body>
        <div align="center">
            <form:form action="register" method="POST" commandName="user">
                <table border="0">
                    <tr>
                        <td colspan="3" align="center"><h2>User - Registration</h2></td>
                    </tr>
                    <tr>
                        <td>First Name :</td>
                        <td><form:input path="firstName" value="bikram" /></td>
                        <td><form:errors path="firstName" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Last Name :</td>
                        <td><form:input path="lastName" value="pradhan" /></td>
                        <td><form:errors path="lastName" cssClass="error"/></td>
                    </tr>
                    
                    <tr>
                        <td>DOB :</td>
                        <td><form:input path="dob" value="19/19/1989" /></td>
                        <td><form:errors path="dob" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Gender : </td>
                        <td>
                            <form:select path = "gender">
                                <form:option value="M">Male</form:option>
                                <form:option value="F">Female</form:option>
                            </form:select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>User Name:</td>
                        <td><form:input path="username" value="bikram" /></td>
                        <td><form:errors path="username" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><form:password path="password" value="bikram"  /></td>
                        <td><form:errors path="password" cssClass="error"/></td>
                    </tr>
                    
                    <tr>
                        <td>Confirm Password:</td>
                        <td><form:password path="confirmPassword" value="bikram" /></td>
                        <td><form:errors path="confirmPassword" cssClass="error"/></td>
                    </tr>
                    
                    <tr>
                        <td>E-mail:</td>
                        <td><form:input path="email" value="bekrampradhan@gmail.com" /></td>
                        <td><form:errors path="email" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="submit" value="Register" /></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
