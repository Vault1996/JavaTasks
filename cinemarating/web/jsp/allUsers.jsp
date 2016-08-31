<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.08.2016
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang" var="rb"/>

<html>
<head>
    <title>Home</title>

    <%@include file="jspf/bootstrap.jspf"%>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<c:set var="page" value="/jsp/allUsers.jsp" scope="session"/>
<%@include file="jspf/navigationBar.jspf"%>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-8 col-sm-offset-2 text-left">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=redirect&next=path.page.main">Home</a></li>
                <li><a href="#">Users</a></li>
            </ul>

            <h1><a name="users"></a>Users</h1>

            <c:set var="numberOfElementsOnPage" value="3" scope="page"/>
            <c:forEach var="user" items="${users}" begin="${pageNumber * numberOfElementsOnPage}" end="${pageNumber * numberOfElementsOnPage + numberOfElementsOnPage - 1}">
                <div class="container-fluid">
                    <div class="picture col-sm-2 col-sm-offset-2">
                        <img src="${pageContext.request.contextPath}/${user.photo}" class="img-rounded img-thumbnail mini" alt="movie">
                    </div>
                    <div class="col-sm-6 text-container">
                        <a href="${pageContext.request.contextPath}/controller?command=show_user&user_id=${user.userId}">
                            <h2><c:out value="${user.login}"/></h2>
                        </a>
                        <div class="justify"><c:out value="${user.createDate}"/></div>
                        <h4 class="sub-text">
                            <fmt:message key="label.status" bundle="${rb}"/>: ${user.status}
                        </h4>
                    </div>
                </div>
                <hr>
            </c:forEach>
            <ctg:pagination numberOfElements="${users.size()}" numberOfElementsOnPage="${numberOfElementsOnPage}"/>
        </div>
    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>