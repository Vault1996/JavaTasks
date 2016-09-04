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

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang" var="rb"/>

<html>
    <head>
        <title> <fmt:message key="title.login" bundle="${ rb }" /> </title>

        <%@include file="jspf/bootstrap.jspf"%>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    </head>
    <body>
        <c:set var="page" value="/jsp/login.jsp" scope="session"/>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=show_main_page">CinemaRating</a>
                </div>

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="button.language" bundle="${ rb }" />
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/controller?command=change_language&language=en_en">English</a></li>
                            <li><a href="${pageContext.request.contextPath}/controller?command=change_language&language=ru_ru">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <form method="POST" class="form-horizontal login-form" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login">
            <div class="form-group">
                <label for="login" class="control-label compulsory">
                    <fmt:message key="label.login" bundle="${ rb }" />
                </label>
                <input id="login" type="text" name="login" value="${login}" class="form-control" autofocus required
                       minLength="1" maxLength="30" placeholder=<fmt:message key="label.login" bundle="${ rb }" /> />
            </div>
            <div class="form-group">
                <label for="password" class="control-label compulsory">
                    <fmt:message key="label.password" bundle="${ rb }" />
                </label>
                <input id="password" type="password" name="password" value="${password}" class="form-control" required
                       minLength="4" maxLength="20" placeholder=<fmt:message key="label.password" bundle="${ rb }" /> />
            </div>
            <input class="btn btn-primary btn-block" type="submit" value="<fmt:message key="button.login" bundle="${ rb }" />" />
            <br/>
            <c:if test="${registrationStatus == true}">
                <div class="alert alert-success">
                    <span class="close" data-dismiss="alert">&times;</span>
                    <fmt:message key="message.registrationSuccessful" bundle="${ rb }" />
                </div>
            </c:if>
            <c:if test="${errorLoginValidation == true}">
                <div class="alert alert-danger">
                    <span class="close" data-dismiss="alert">&times;</span>
                    <fmt:message key="message.errorPasswordOrLogin" bundle="${ rb }" />
                </div>
            </c:if>
            <c:if test="${errorLoginPassword == true}">
                <div class="alert alert-danger">
                    <span class="close" data-dismiss="alert">&times;</span>
                    <fmt:message key="message.errorPassword" bundle="${ rb }" />
                </div>
            </c:if>
            <hr>
            <div class="text-center">
                <ul class="pagination">
                    <li class = "active"><a href="#"><fmt:message key="button.login" bundle="${ rb }" /></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=redirect&next=path.page.registration"><fmt:message key="button.registration" bundle="${ rb }" /></a></li>
                </ul>
            </div>
        </form>
    </body>
</html>
