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
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">CinemaRating</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Contact</a></li>
                        <li>
                            <a>
                                <form class="form-inline">
                                    <div class="form-group">
                                        <input class="form-control" type="text" name="search" placeholder="Search..">
                                    </div>
                                </form>
                            </a>
                        </li>
                    </ul>
                </div>

                <ul class="nav navbar-nav navbar-right">
                    <%-- my tag --%>
                    <li><ctg:role-name role="Admin" name="Vasya"/></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <!-- EMPTY -->
                    <p><a href="##welcome">Welcome</a></p>
                    <p><a href="##top3">Top 3 Movies</a></p>
                </div>
                <div class="col-sm-8 text-left">
                    <ul class="breadcrumb">
                        <li><a href="#">Home</a></li>
                    </ul>
                    <h1><a name="#welcome"></a>Welcome</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    <hr>
                    <h3> <a name="#top3"></a> Top 3 Movies</h3>
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
                                <a href="movie.html">
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
        <footer class="container-fluid text-center">
            <div>
                <p>Contact info:</p>
            </div>
            <p>Email: cinema@gmail.com</p>
        </footer>
    </body>
</html>
