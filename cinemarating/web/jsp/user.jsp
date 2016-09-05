<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.08.2016
  Time: 19:19
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
<c:set var="page" value="/jsp/user.jsp" scope="session"/>
<%@include file="jspf/navigationBar.jspf"%>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-8 col-sm-offset-2 text-left">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=show_main_page">Home</a></li>
                <li><a href="#">${user.login}</a></li>
            </ul>
            <c:if test="${deleteUserStatus == true}">
                <div class="alert alert-info">
                    <span class="close" data-dismiss="alert">&times;</span>
                    <fmt:message key="message.userDeleted" bundle="${ rb }" />
                </div>
            </c:if>
            <h1>${user.login}</h1>
            <hr>
            <div class="clearfix">
                <img class = "main img-rounded img-thumbnail" src = "${pageContext.request.contextPath}/${user.photo}" alt="${user.login}"/>
                <h3>Registration Date: <small><fmt:formatDate value="${user.createDate}" type="date"/> </small></h3>
                <h3>Status: <small>${user.status}</small></h3>
                <h3>Name: <small>${user.name}</small></h3>
                <h3>Surname: <small>${user.surname}</small></h3>
            </div>
            <ctg:admin>
                <form method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="delete_user"/>
                    <input type="hidden" name="user_id" value="${user.userId}"/>
                    <input class="btn btn-primary" type="submit" value="<fmt:message key="button.deleteUser" bundle="${ rb }" />" />
                </form>
            </ctg:admin>
            <hr>
        </div>
    </div>
</div>

<%@include file="jspf/footer.jspf"%>

</body>
</html>