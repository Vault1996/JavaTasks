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
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_main_page">Home</a></li>
                        <li><a href="#">${movie.name}</a></li>
                    </ul>
                    <c:if test="${reviewEditedStatus == true}">
                        <div class="alert alert-info">
                            <span class="close" data-dismiss="alert">&times;</span>
                            <fmt:message key="message.reviewEdited" bundle="${ rb }" />
                        </div>
                    </c:if>
                    <c:if test="${reviewAddedStatus == true}">
                        <div class="alert alert-info">
                            <span class="close" data-dismiss="alert">&times;</span>
                            <fmt:message key="message.reviewAdded" bundle="${ rb }" />
                        </div>
                    </c:if>
                    <c:if test="${deleteReviewStatus == true}">
                        <div class="alert alert-info">
                            <span class="close" data-dismiss="alert">&times;</span>
                            <fmt:message key="message.reviewDeleted" bundle="${ rb }" />
                        </div>
                    </c:if>
                    <h1>${movie.name}</h1>
                    <hr>
                    <div class="clearfix">
                        <img class = "main img-rounded img-thumbnail" src = "${pageContext.request.contextPath}/${movie.poster}" alt="${movie.name}"/>
                        <h3>Rating: <small>${movie.rating}</small></h3>
                        <h3>Country: <small>${movie.country}</small></h3>
                        <h3>Year: <small>${movie.year}</small></h3>
                        <h3><a name="discription"></a>Description</h3>
                        <p>${movie.description}</p>
                    </div>
                    <hr>
                    <div>
                        <ctg:not-guest>
                            <c:if test="${empty activeUserReview.review}">
                                <div>
                                    <h3>Add Review</h3>
                                    <form method="POST" class="form-horizontal review" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="leave_review"/>
                                        <input type="hidden" name="movie_id" value="${movie.movieId}"/>
                                        <input type="hidden" name="user_id" value="${activeUser.userId}"/>
                                        <div class="form-group">
                                            <label class="control-label">Rating:</label>
                                            <c:forEach var="i" begin="1" end="10">
                                                <c:if test="${i == 6}">
                                                    <label class="radio-inline"><input type="radio" name="rating" value="${i}" checked>${i}</label>
                                                </c:if>
                                                <c:if test="${i != 6}">
                                                    <label class="radio-inline"><input type="radio" name="rating" value="${i}">${i}</label>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="review">Review:</label>
                                            <textarea class="form-control" name="review" rows="3" maxlength="512" id="review"></textarea>
                                        </div>
                                        <input class="btn btn-primary btn-block" type="submit" value="Leave Review" />
                                    </form>
                                    <hr>
                                </div>
                            </c:if>
                        </ctg:not-guest>
                        <c:if test="${not empty activeUserReview.review}">
                            <h3>My Review</h3>
                            <div class="well">
                                <div class="container-fluid">
                                    <div class="picture col-sm-3">
                                        <img src="${pageContext.request.contextPath}/${activeUserReview.user.photo}" class="img-rounded img-thumbnail mini" alt="user">
                                    </div>
                                    <div class="col-sm-9 text-container">
                                        <a href="${pageContext.request.contextPath}/controller?command=show_user&user_id=${activeUserReview.user.userId}">
                                            <h2><c:out value="${activeUserReview.user.login}"/></h2>
                                        </a>
                                        <h3>
                                            <small>
                                                <fmt:formatDate value="${activeUserReview.review.time}" type="both"/>
                                            </small>
                                        </h3>
                                        <h4 class="sub-text">
                                            <fmt:message key="label.rating" bundle="${rb}"/>: ${activeUserReview.rating.rating}
                                        </h4>
                                        <div class="justify"><c:out value="${activeUserReview.review.review}"/></div>
                                    </div>
                                </div>
                                <hr>
                                <div class="clearfix">
                                    <form style="float: left;" method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="show_edit_review"/>
                                        <input type="hidden" name="movie_id" value="${movie.movieId}"/>
                                        <input type="hidden" name="user_id" value="${activeUserReview.user.userId}"/>
                                        <input class="btn btn-primary" type="submit" value="<fmt:message key="button.editReview" bundle="${ rb }" />" />
                                    </form>
                                    <form style="float: right;" method="POST" class="form-horizontal" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="delete_review"/>
                                        <input type="hidden" name="movie_id" value="${movie.movieId}"/>
                                        <input type="hidden" name="user_id" value="${activeUserReview.user.userId}"/>
                                        <input class="btn btn-primary" type="submit" value="<fmt:message key="button.deleteReview" bundle="${ rb }" />" />
                                    </form>
                                </div>
                            </div>
                            <hr>
                        </c:if>
                        <div>
                            <h3>Reviews</h3>
                            <c:forEach var="review" items="${reviews}">
                                <div class="container-fluid well">
                                    <div class="picture col-sm-3">
                                        <img src="${pageContext.request.contextPath}/${review.user.photo}" class="img-rounded img-thumbnail mini" alt="user">
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
