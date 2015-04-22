<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring Chat</title>
        <c:set var="base" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/" />
        <link rel="stylesheet" href="${base}resources/css/bootstrap.css"/>
        <link rel="stylesheet" href="${base}resources/css/chat.css"/>
        <link rel="stylesheet" href="${base}resources/css/chosen.css"/>
        <!--        <link rel="stylesheet" href="resources/css/jquery-ui.css"/>-->
        <script src="${base}resources/js/jquery2.1.3.js"></script>
        <!--        <script src="resources/js/jquery-ui.js"></script>
                <script src="resources/js/tag-it.js"></script>-->
        <script src="${base}resources/js/chosen.jquery.js"></script>
        <script src="${base}resources/js/script.js"></script>
        <script type="text/javascript">
            var baseUrl = '${base}';
            var name = '${name}';
        </script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Spring Chat</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="javascript:;" id="new-message">New Message</a></li>
                        <li><a href="#">Settings</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="">
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <c:forEach var="chat" items="${chats}">
                            <li class="active">
                                <a href="javascript:;" class="chat" chat_id="${chat.id}">
                                    <span  style="font-weight: bold;">
                                        <c:if test='${fn:indexOf(chat.name, "/") > -1}'>
                                            <c:set var="test1" value="${name}/" />
                                            <c:set var="test2" value="/${name}" />
                                            <c:set var="chatName" value='${fn:replace(chat.name, test2, "")}' />
                                            ${fn:replace(chatName, test1, "")}
                                        </c:if>
                                        <c:if test='${fn:indexOf(chat.name, ",") > -1}'>
                                            <c:set var="test1" value="${name}, " />
                                            <c:set var="test2" value=", ${name}" />
                                            <c:set var="chatName" value='${fn:replace(chat.name, test2, "")}' />
                                            ${fn:replace(chatName, test1, "")}
                                        </c:if>
                                    </span>
                                    <br/>
                                    <fmt:formatDate dateStyle="medium" pattern="MMM d h:mm a" value="${chat.lastUpdated}" />
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="chat-area" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="overflow-y: scroll; height: 500px;">
                    <div id="new-chat" class="form-horizontal">
                        <div class="form-group">
                            <label for="to" class="col-sm-1 control-label">To</label>
                            <div class="col-sm-11">
                                <select id="to" class="form-control" multiple="multiple">
                                    <c:forEach var="friend" items="${friends}">
                                        <option value="${friend.id}">${friend.firstName}  ${friend.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="chat-messages" style="display: block;">
                        <h2 id="chat-name" class="sub-header" style="border-bottom-width: 0px;">Section title</h2>
                        <ul id="messages" class="list-group">
                        </ul>
                    </div>
                    <div id="message-box" style="position: fixed;bottom: 20px;" class="col-sm-9">
                        <textarea id="message" class="form-control" rows="3"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </body>
</body>
</html>
