<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.08.2016
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <c:set var="page" value="/jsp/movie.jsp" scope="session"/>
        <%@include file="jspf/navigationBar.jspf"%>

        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a href="#discription">Discription</a></p>
                    <p><a href="#review">Review</a></p>
                </div>
                <div class="col-sm-8 text-left">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/jsp/main.jsp">Home</a></li>
                        <li><a href="#">Film1</a></li>
                    </ul>
                    <h1>Film1</h1>
                    <hr>
                    <h3><a name="discription"></a>Discription</h3>
                    <div class="clearfix">
                        <img class = "main" src = "../images/movies/film1.jpg" alt="film1"/>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                    </div>
                    <hr>
                    <div>
                        <h3>Review</h3>
                        <form method="GET" class="form-horizontal review">
                            <div class="form-group">
                                <label class="control-label">Rating:</label>
                                <label class="radio-inline"><input type="radio" name="rating">1</label>
                                <label class="radio-inline"><input type="radio" name="rating">2</label>
                                <label class="radio-inline"><input type="radio" name="rating">3</label>
                                <label class="radio-inline"><input type="radio" name="rating">3</label>
                                <label class="radio-inline"><input type="radio" name="rating">3</label>
                                    <%--
                                    <input id="rating1" type="radio" name="rating" value="1">
                                    <label for="rating1">1</label>
                                    <input id="rating2" type="radio" name="rating" value="2">
                                    <label for="rating2">2</label>
                                    <input id="rating3" type="radio" name="rating" value="3" checked>
                                    <label for="rating3">3</label>
                                    <input id="rating4" type="radio" name="rating" value="4">
                                    <label for="rating4">4</label>
                                    <input id="rating5" type="radio" name="rating" value="5">
                                    <label for="rating5">5</label>
                                    --%>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="review">Review:</label>
                                <textarea class="form-control" name="review" rows="3" maxlength="512" id="review"></textarea>
                            </div>
                            <input class="btn btn-primary btn-block" type="submit" value="Leave Review" />
                        </form>
                    </div>
                </div>
                <div class="col-sm-2 sidenav">
                    <%--
                    <div class="well">
                        <a href="http://www.w3schools.com/bootstrap/default.asp"><img src = "../images/ads/bootstrap.jpg" width="100%" /></a>
                    </div>
                    --%>
                </div>
            </div>
        </div>

        <%@include file="jspf/footer.jspf"%>

    </body>
</html>
