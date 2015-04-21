<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center">
            <form:form action="edit" method="POST" commandName="user">
                <form:hidden path = "id" value = "${userVal.id}" />
                <table border="0">
                    <tr>
                        <td colspan="3" align="center"><h2>User - Registration</h2></td>
                    </tr>
                    <tr>
                        <td>First Name :</td>
                        <td><form:input path="firstName" value="${userVal.firstName}"/></td>
                        <td><form:errors path="firstName" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td>Last Name :</td>
                        <td><form:input path="lastName" value="${userVal.lastName}" /></td>
                        <td><form:errors path="lastName" cssClass="error"/></td>
                    </tr>
                    
                    <tr>
                        <td>DOB :</td>
                        <td><form:input path="dob" value="${userVal.dob}"/></td>
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
                    
                    <form:hidden path="username" value="${userVal.username}"/>
                   <form:hidden path="password" value="${userVal.password}"/>
             
                    <tr>
                        <td>E-mail:</td>
                        <td><form:input path="email" value="${userVal.email}"/></td>
                        <td><form:errors path="email" cssClass="error"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="submit" value="Update" /></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
