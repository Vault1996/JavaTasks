<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.08.2016
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang" var="rb"/>

<html>
<head>
    <title>Movie</title>

    <%@include file="jspf/bootstrap.jspf"%>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<c:set var="page" value="/jsp/editProfile.jsp" scope="session"/>
<%@include file="jspf/navigationBar.jspf"%>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-8 col-sm-offset-2 text-left">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/jsp/main.jsp">Home</a></li>
                <li><a href="#">Edit Profile</a></li>
            </ul>
            <h1>${activeUser.login}</h1>
            <hr>
            <form method="POST" class="form-horizontal login-form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="edit_profile">
                <div class="form-group">
                    <label for="photo" class="control-label compulsory">
                        <fmt:message key="label.photo" bundle="${ rb }" />
                    </label>
                    <input type="file" name="photo" class="form-control" id="photo" accept="image/png,image/jpeg"
                           placeholder=<fmt:message key="label.photo" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="name" class="control-label compulsory">
                        <fmt:message key="label.name" bundle="${ rb }" />
                    </label>
                    <input id="name" type="text" name="name" value="${activeUser.name}" class="form-control"
                            maxlength="30" placeholder=<fmt:message key="label.name" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="surname" class="control-label compulsory">
                        <fmt:message key="label.surname" bundle="${ rb }" />
                    </label>
                    <input id="surname" type="text" name="surname" value="${activeUser.surname}" class="form-control"
                            maxlength="30" placeholder=<fmt:message key="label.surname" bundle="${ rb }" /> />
                </div>
                <div class="form-group">
                    <label for="password" class="control-label compulsory">
                        <fmt:message key="label.password" bundle="${ rb }" />
                    </label>
                    <input id="password" type="password" name="password" class="form-control"
                           minlength="4" maxlength="20" placeholder=<fmt:message key="label.password" bundle="${ rb }" /> />
                </div>
                <input class="btn btn-primary btn-block" type="submit" value=<fmt:message key="button.submit" bundle="${ rb }" /> />
                <br/>
                <span class="error">
                    <c:out value="${invalidDataMessage}"/>
                </span>
            </form>
        </div>
    </div>
</div>

<%@include file="jspf/footer.jspf"%>

</body>
</html>

