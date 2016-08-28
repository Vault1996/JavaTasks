<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.08.2016
  Time: 11:05
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
<c:set var="page" value="/jsp/allMovies.jsp" scope="session"/>
<%@include file="jspf/navigationBar.jspf"%>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <!-- EMPTY -->
            <p><a href="#movies">Welcome</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=redirect&next=path.page.main">Home</a></li>
                <li><a href="#">All Movies</a></li>
            </ul>

            <c:if test="${sortBy eq 'name'}">
                <h1><a name="movies"></a>All Movies</h1>
            </c:if>
            <c:if test="${sortBy eq 'rating'}">
                <h1><a name="movies"></a>Top Movies</h1>
            </c:if>

            <c:forEach var="movie" items="${movies}" begin="${pageNumber * 3}" end="${pageNumber * 3 + 2}">
                <div class="container content-container">
                    <div class="picture col-sm-2 col-sm-offset-2">
                        <img src="${pageContext.request.contextPath}/${movie.poster}" class="img-rounded mini" alt="movie">
                    </div>
                    <div class="col-sm-6 text-container">
                        <a href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${movie.movieId}">
                            <h2><c:out value="${movie.name}"/></h2>
                        </a>
                        <h4 class="sub-text">
                            <fmt:message key="label.rating" bundle="${rb}"/>: ${movie.rating}
                        </h4>
                        <div class="justify"><c:out value="${movie.description}"/></div>
                    </div>
                </div>
            </c:forEach>
            <ctg:pagination numberOfMovies="${movies.size()}"/>
        </div>
        <div class="col-sm-2 sidenav">
            <div class="well">
                <a href="http://www.w3schools.com/bootstrap/default.asp"><img src = "${pageContext.request.contextPath}/images/ads/bootstrap.jpg" width="100%" alt="Adv"/></a>
            </div>
        </div>
    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
