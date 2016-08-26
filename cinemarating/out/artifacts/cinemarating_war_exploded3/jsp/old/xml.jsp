<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.07.2016
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <form method="get" action="${pageContext.request.contextPath}/controller" class="form-horizontal login-form">
        <div class="form-group">
            <label for="parser" class="control-label compulsory">
                Parser
            </label>
            <input id="parser" class="form-control" type="text" name="parser" required placeholder="Parser"/><!--pattern="((dom)|(sax)|(stax)|(DOM)|(SAX)|(STAX))" />-->
        </div>
        <input type="submit" value="Parse" />
        <hr>
        <span style="color:#FF0000">${err}</span>
    </form>
</div>
</body>
</html>