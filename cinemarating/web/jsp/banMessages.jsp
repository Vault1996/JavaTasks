<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.09.2016
  Time: 23:18
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
<c:set var="page" value="/jsp/banMessages.jsp" scope="session"/>
<%@include file="jspf/navigationBar.jspf"%>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-8 col-sm-offset-2 text-left">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=show_main_page">Home</a></li>
                <li><a href="#">Messages</a></li>
            </ul>

            <h1>Ban Messages</h1>

            <c:set var="numberOfElementsOnPage" value="2" scope="page"/>

            <c:forEach var="message" items="${banMessages}" begin="${pageNumber * numberOfElementsOnPage}" end="${pageNumber * numberOfElementsOnPage + numberOfElementsOnPage - 1}">
                <div class="container-fluid">
                    <div class="picture col-sm-2 col-sm-offset-2">
                        <img src="${pageContext.request.contextPath}/${message.user.photo}" class="img-rounded img-thumbnail mini" alt="user"/>
                    </div>
                    <div class="col-sm-6 text-container">
                        <a href="${pageContext.request.contextPath}/controller?command=show_user&user_id=${message.user.userId}">
                            <h2><c:out value="${message.user.login}"/></h2>
                        </a>
                        <p>${message.banMessage.message}</p>
                    </div>
                </div>
                <hr>
            </c:forEach>
            <ctg:pagination numberOfElements="${banMessages.size()}" numberOfElementsOnPage="${numberOfElementsOnPage}"/>
        </div>
    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>

