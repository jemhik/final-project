<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 18.04.2022
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/teacherHeader.jsp"/>

<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="coursesList" bundle="${lang}"/></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="name" bundle="${lang}"/></th>
                <th><fmt:message key="numberOfStudents" bundle="${lang}"/></th>
                <th><fmt:message key="task" bundle="${lang}"/></th>
                <th><fmt:message key="actions" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.name}" /></td>
                    <td><c:out value="${course.numberOfStudents}" /></td>
                    <td><c:out value="${course.task}" /></td>
                    <td><a href="controller?action=courseJournal&name=${course.name}" class="btn btn-success"><fmt:message key="journal" bundle="${lang}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
