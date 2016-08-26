<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.07.2016
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Result</title>
</head>
    <body>
        <p>
            ${result}
        </p>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Thema</th>
                    <th>Type</th>
                    <th>Country</th>
                    <th>Author</th>
                </tr>
            </thead>

            <c:forEach items="${set}" var="card">
                <tr>
                    <td><c:out value ="${card.id}"/></td>
                    <td><c:out value ="${card.thema}"/></td>
                    <td><c:out value ="${card.type}"/></td>
                    <td><c:out value ="${card.country}"/></td>
                    <td><c:out value ="${card.author}"/></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
