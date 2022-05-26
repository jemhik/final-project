<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 30.03.2022
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>

    <title>Teachers</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/adminHeader.jsp"/>

<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="teachersList" bundle="${lang}"/></h3>
        <hr>
        <div class="container text-left">
            <a href="controller?action=openAddTeacher" class="btn btn-success"><fmt:message key="newTeacher" bundle="${lang}"/></a>
        </div>
        <br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Id</th>
        <th><fmt:message key="firstName" bundle="${lang}"/></th>
        <th><fmt:message key="lastName" bundle="${lang}"/></th>
        <th><fmt:message key="email" bundle="${lang}"/></th>
        <th><fmt:message key="courses" bundle="${lang}"/></th>
        <th><fmt:message key="actions" bundle="${lang}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr>
            <td><c:out value="${teacher.id}" /></td>
            <td><c:out value="${teacher.firstName}" /></td>
            <td><c:out value="${teacher.lastName}" /></td>
            <td><c:out value="${teacher.email}" /></td>
            <td><c:out value="${teacher.coursesNames}" /></td>
            <td><a href="controller?action=openAddCourse"><fmt:message key="newCourse" bundle="${lang}"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    </div>
</div>
</body>
</html>
