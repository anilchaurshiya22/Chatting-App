<%-- 
    Document   : friendRequest
    Created on : Apr 20, 2015, 4:32:12 PM
    Author     : 984351
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friend Request </title>
        <link href="resources/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>${message}</h1>	
        <div id="friendRequestDiv">
            <form:form commandName="friendRequest" action="sendRequest"  method='POST'>

                <table>

                    <tr>
                        <td><form:label path="inviteCode" >Username or Email:</form:label></td>
                        <td><form:input path="inviteCode" /> </td>
                    </tr>
                    <tr>
                        <td><form:label path="message" >Message:</form:label></td>
                        <td><form:input path="message" /> </td>
                    </tr>
                </table>
                <input type="submit" value="Send"/>
            </form:form>
        </div>
    </body>
</html>
