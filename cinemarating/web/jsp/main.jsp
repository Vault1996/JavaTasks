<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.07.2016
  Time: 17:24
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
        <c:set var="page" value="/jsp/main.jsp" scope="session"/>
        <%@include file="jspf/navigationBar.jspf"%>

        <div class="container-fluid text-center">
            <div class="row content">
                <%--
                <div class="col-sm-2 sidenav">
                    <!-- EMPTY -->
                    <p><a href="#welcome">Welcome</a></p>
                    <p><a href="#top3">Top 3 Movies</a></p>
                </div>
                --%>
                <div class="col-sm-8 col-sm-offset-2 text-left">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_main_page">Home</a></li>
                    </ul>
                    <h1><a name="welcome"></a>Welcome</h1>
                    <p>Welcome to our website. Here you can see new movies, rate them and get status to be the best and cleverest reviewer ever.</p>
                    <hr>
                    <h3> <a name="top3"></a> Top 3 Movies</h3>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <c:forEach items="${topMovies}" varStatus="status">
                                <c:if test="${status.index == 0}">
                                    <li data-target="#myCarousel" data-slide-to="${status.index}" class="active"></li>
                                </c:if>
                                <c:if test="${status.index != 0}">
                                    <li data-target="#myCarousel" data-slide-to="${status.index}"></li>
                                </c:if>
                            </c:forEach>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <c:forEach var="movie" items="${topMovies}" varStatus="status">
                                <c:if test="${status.index == 0}" >
                                    <div class="item active">
                                        <a href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${movie.movieId}">
                                            <img src="${pageContext.request.contextPath}/${movie.poster}" alt="${movie.name}">
                                        </a>
                                    </div>
                                </c:if>
                                <c:if test="${status.index != 0}" >
                                    <div class="item">
                                        <a href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${movie.movieId}">
                                            <img src="${pageContext.request.contextPath}/${movie.poster}" alt="${movie.name}">
                                        </a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>

                        <!-- Left and right controls -->
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                    <%--
                <div class="col-sm-2 sidenav">
                    <div class="well">
                        <a href="http://www.w3schools.com/bootstrap/default.asp"><img src = "${pageContext.request.contextPath}/images/ads/bootstrap.jpg" width="100%" alt="Adv"/></a>
                    </div>
                </div>
                --%>
            </div>
        </div>
        <%@include file="jspf/footer.jspf"%>
    </body>
</html>
