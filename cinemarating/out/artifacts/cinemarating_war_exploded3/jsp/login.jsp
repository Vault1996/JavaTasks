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

<fmt:setLocale value="ru_ru"/>
<fmt:setBundle basename="lang" var="rb"/>

<html>
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>

        <title> <fmt:message key="title.login" bundle="${ rb }" /> </title>
    </head>
    <body>
        <div class="container">
            <form method="POST" class="form-horizontal login-form" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login">
                <div class="form-group">
                    <label for="login" class="control-label compulsory">
                        <fmt:message key="label.login" bundle="${ rb }" />
                    </label>
                    <input id="login" type="text" name="login" class="form-control" required
                           minLength="1" maxLength="30" placeholder=<fmt:message key="label.login" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="password" class="control-label compulsory">
                        <fmt:message key="label.password" bundle="${ rb }" />
                    </label>
                    <input id="password" type="password" name="password" class="form-control" required
                           minLength="4" maxLength="20" placeholder=<fmt:message key="label.password" bundle="${ rb }" /> />
                </div>
                <input class="btn btn-primary btn-block" type="submit" value=<fmt:message key="button.login" bundle="${ rb }" /> />
                <br/>
                <span class="error">
                    <c:out value="${errorLoginPassMessage}"/>
                </span>
                <hr>
                <ul class="pagination">
                    <li class = "active"><a href="#"><fmt:message key="button.login" bundle="${ rb }" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/registration.jsp"><fmt:message key="button.registration" bundle="${ rb }" /></a></li>
                </ul>
            </form>
        </div>
    </body>
</html>
