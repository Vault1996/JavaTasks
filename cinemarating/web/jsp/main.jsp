<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.07.2016
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
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
                <div class="col-sm-2 sidenav">
                    <!-- EMPTY -->
                    <p><a href="#welcome">Welcome</a></p>
                    <p><a href="#top3">Top 3 Movies</a></p>
                </div>
                <div class="col-sm-8 text-left">
                    <ul class="breadcrumb">
                        <li><a href="#">Home</a></li>
                    </ul>
                    <h1><a name="welcome"></a>Welcome</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    <hr>
                    <h3> <a name="top3"></a> Top 3 Movies</h3>
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <a href="${pageContext.request.contextPath}/jsp/movie.jsp">
                                    <img src="${pageContext.request.contextPath}/images/movies/film1.jpg" alt="Film1">
                                </a>
                            </div>

                            <div class="item">
                                <img src="${pageContext.request.contextPath}/images/movies/film2.jpg" alt="Film2">
                            </div>

                            <div class="item">
                                <img src="${pageContext.request.contextPath}/images/movies/film3.jpg" alt="Film3">
                                <div class="carousel-caption">
                                    <h3>Film3</h3>
                                    <p>Purr-purr-purr</p>
                                </div>
                            </div>
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
