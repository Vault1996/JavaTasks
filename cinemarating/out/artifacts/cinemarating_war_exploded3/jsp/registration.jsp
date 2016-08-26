<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.08.2016
  Time: 12:30
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

        <title> <fmt:message key="title.registration" bundle="${ rb }" /> </title>
    </head>
    <body>
        <div class="container">
            <form method="POST" class="form-horizontal login-form" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="registration">
                <div class="form-group">
                    <label for="login" class="control-label compulsory">
                        <fmt:message key="label.login" bundle="${ rb }" />
                    </label>
                    <input id="login" type="text" name="login" class="form-control" required
                           minlength="1" maxlength="30" placeholder=<fmt:message key="label.login" bundle="${ rb }" /> />
                </div>
                <%-- TODO: NAME AND SURNAME NOT REQUIRED --%>
                <div class="form-group">
                    <label for="name" class="control-label compulsory">
                        <fmt:message key="label.name" bundle="${ rb }" />
                    </label>
                    <input id="name" type="text" name="name" class="form-control" required
                           minlength="1" maxlength="30" placeholder=<fmt:message key="label.name" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="surname" class="control-label compulsory">
                        <fmt:message key="label.surname" bundle="${ rb }" />
                    </label>
                    <input id="surname" type="text" name="surname" class="form-control" required
                           minlength="1" maxlength="30" placeholder=<fmt:message key="label.surname" bundle="${ rb }" /> />
                </div>
                <%-- ************************* --%>
                <div class="form-group">
                    <label for="email" class="control-label compulsory">
                        <fmt:message key="label.email" bundle="${ rb }" />
                    </label>
                    <input type="email" name="email" class="form-control" id="email" required
                           placeholder=<fmt:message key="label.email" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="password" class="control-label compulsory">
                        <fmt:message key="label.password" bundle="${ rb }" />
                    </label>
                    <input id="password" type="password" name="password" class="form-control" required
                           minlength="4" maxlength="20" placeholder=<fmt:message key="label.password" bundle="${ rb }" /> />
                </div>
                <input class="btn btn-primary btn-block" type="submit" value=<fmt:message key="button.register" bundle="${ rb }" /> />
                <br/>
                <span class="error">
                    <c:out value="${errorRegistrationMessage}"/>
                </span>
                <hr>
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="button.login" bundle="${ rb }" /></a></li>
                    <li class = "active"><a href="#"><fmt:message key="button.registration" bundle="${ rb }" /></a></li>
                </ul>
            </form>
        </div>
    </body>
</html>