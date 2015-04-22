<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
        
    </head>
    <body>
        <div align="left">
            <form:form action="register" method="POST" commandName="user">
                <table border="0"  >
                    <tr>
                        <td colspan="3" align="center"><h2>User - Registration</h2></td>
                    </tr>
                    <tr>
                        <td><form:label path = "firstName" >First Name :</form:label></td>
                        <td><form:input path="firstName" value="bikram" /></td>
                        <td><form:errors path="firstName" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td><form:label path = "lastName" >Last Name :</form:label></td>
                        <td><form:input path="lastName" value="pradhan" /></td>
                        <td><form:errors path="lastName" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td><form:label path = "dob" >DOB : </form:label></td>
                        <td><form:input path="dob" value="11/19/1989" /></td>
                        <td><form:errors path="dob" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td><form:label path = "gender" >Gender : </form:label></td>
                            <td>
                            <form:select path = "gender">
                                <form:option value="M">Male</form:option>
                                <form:option value="F">Female</form:option>
                            </form:select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><form:label path = "username" >User Name:</form:label></td>
                        <td><form:input path="username" value="bikram" /></td>
                        <td><form:errors path="username" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td><form:label path = "password" >Password:</form:label></td>
                        <td><form:password path="password" value="bikram"  /></td>
                        <td><form:errors path="password" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td><form:label path = "confirmPassword" >Confirm Password:</form:label></td>
                        <td><form:password path="confirmPassword" value="bikram" /></td>
                        <td><form:errors path="confirmPassword" cssClass="error"/></td>
                    </tr>

                    <tr>
                        <td><form:label path = "email" >E-mail:</form:label></td>
                        <td><form:input path="email" value="bekrampradhan@gmail.com" /></td>
                        <td><form:errors path="email" cssClass="error"/></td>
                    </tr>

                </table>
                <input type="submit" value="Register" />
            </form:form> 
        </div>
    </body>
</html>
