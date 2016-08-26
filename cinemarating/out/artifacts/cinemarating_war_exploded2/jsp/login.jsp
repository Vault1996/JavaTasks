<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.07.2016
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="ru_ru" />
<fmt:setBundle basename="lang" var="rb"/>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <%-- скачать BOOTSTRAP на комп --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <title> <fmt:message key="title.login" bundle="${ rb }" /> </title>
    </head>
    <body>
        <div class="container">
            <form method="POST" class="form-horizontal login-form" action="controller">
                <input type="hidden" name="command" value="loginn">
                <div class="form-group">
                    <label for="login" class="control-label compulsory">
                        <fmt:message key="loginForm.login" bundle="${ rb }" />
                    </label>
                    <input id="login" type="text" name="login" class="form-control" required minLength="1" maxLength="30" placeholder="Login" />
                </div>
                <div class="form-group">
                    <label for="password" class="control-label compulsory">
                        Password
                    </label>
                    <input id="password" type="password" name="password" class="form-control" required minLength="4" maxLength="20" placeholder="Password" />
                </div>
                <input class="btn btn-primary btn-block" type="submit" value="Log In" />
                <br/>
                <span class="error">
                    <c:out value="${errorLoginPassMessage}"/>
                    <br/>
                    ${errorLoginPassMessage}
                </span>
                <hr>
                <ul class="pagination">
                    <li class = "active"><a href="#">LogIn</a></li>
                    <!-- EMPTY REFERENCE -->
                    <li><a href="">Registration</a></li>
                </ul>
            </form>
        </div>
    </body>
</html>
