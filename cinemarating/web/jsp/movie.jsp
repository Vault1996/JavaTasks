<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.08.2016
  Time: 16:46
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
        <c:set var="page" value="/jsp/movie.jsp" scope="session"/>
        <%@include file="jspf/navigationBar.jspf"%>

        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-sm-8 col-sm-offset-2 text-left">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/jsp/main.jsp">Home</a></li>
                        <li><a href="#">${movie.name}</a></li>
                    </ul>
                    <h1>${movie.name}</h1>
                    <hr>
                    <div class="clearfix">
                        <img class = "main img-rounded img-thumbnail" src = "${movie.poster}" alt="${movie.name}"/>
                        <h3>Rating: <small>${movie.rating}</small></h3>
                        <h3>Country: <small>${movie.country}</small></h3>
                        <h3>Year: <small>${movie.year}</small></h3>
                        <h3><a name="discription"></a>Description</h3>
                        <p>${movie.description}</p>
                    </div>
                    <hr>
                    <div>
                        <ctg:not-guest>
                            <div>
                                <h3>Add Review</h3>
                                <form method="GET" class="form-horizontal review">
                                    <div class="form-group">
                                        <label class="control-label">Rating:</label>
                                        <label class="radio-inline"><input type="radio" name="rating">1</label>
                                        <label class="radio-inline"><input type="radio" name="rating">2</label>
                                        <label class="radio-inline"><input type="radio" name="rating">3</label>
                                        <label class="radio-inline"><input type="radio" name="rating">4</label>
                                        <label class="radio-inline"><input type="radio" name="rating">5</label>
                                        <label class="radio-inline"><input type="radio" name="rating" checked>6</label>
                                        <label class="radio-inline"><input type="radio" name="rating">7</label>
                                        <label class="radio-inline"><input type="radio" name="rating">8</label>
                                        <label class="radio-inline"><input type="radio" name="rating">9</label>
                                        <label class="radio-inline"><input type="radio" name="rating">10</label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" for="review">Review:</label>
                                        <textarea class="form-control" name="review" rows="3" maxlength="512" id="review"></textarea>
                                    </div>
                                    <input class="btn btn-primary btn-block" type="submit" value="Leave Review" />
                                </form>
                                <hr>
                            </div>
                        </ctg:not-guest>
                        <div>
                            <h3>Reviews</h3>
                            <c:forEach var="review" items="${reviews}">
                                <div class="container-fluid well">
                                    <div class="picture col-sm-3">
                                        <img src="${pageContext.request.contextPath}/${review.user.photo}" class="img-rounded img-thumbnail mini" alt="movie">
                                    </div>
                                    <div class="col-sm-8 text-container">
                                        <a href="${pageContext.request.contextPath}/controller?command=show_user&user_id=${review.user.userId}">
                                            <h2><c:out value="${review.user.login}"/></h2>
                                        </a>
                                        <h3>
                                            <small>
                                                <fmt:formatDate value="${review.review.time}" type="both"/>
                                            </small>
                                        </h3>
                                        <h4 class="sub-text">
                                            <fmt:message key="label.rating" bundle="${rb}"/>: ${review.rating.rating}
                                        </h4>
                                        <div class="justify"><c:out value="${review.review.review}"/></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="jspf/footer.jspf"%>

    </body>
</html>
