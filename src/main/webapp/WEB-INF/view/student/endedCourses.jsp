<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 18.04.2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Ended</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/studentHeader.jsp"/>

<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="endedCourses" bundle="${lang}"/></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="name" bundle="${lang}"/></th>
                <th><fmt:message key="progress" bundle="${lang}"/></th>
                <th><fmt:message key="asmnt" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.name}" /></td>
                    <td><c:out value="${course.progress}" /></td>
                    <td><c:out value="${course.assessment}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="controller?action=availableCourses" class="btn btn-primary"><fmt:message key="findNew" bundle="${lang}"/></a>
    </div>
</div>
</body>
</html>
