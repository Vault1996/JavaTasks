<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.07.2016
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Exception</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<!--
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
            <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
-->

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">
            <h1>Exception</h1>
            <p>
                ${pageContext.session.getAttribute("exception")}
            </p>
        </div>
        <div class="col-sm-2 sidenav">

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
