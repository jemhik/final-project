<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 05.04.2022
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/adminHeader.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="studentsList" bundle="${lang}"/></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th><fmt:message key="firstName" bundle="${lang}"/></th>
                <th><fmt:message key="lastName" bundle="${lang}"/></th>
                <th><fmt:message key="email" bundle="${lang}"/></th>
                <th><fmt:message key="courses" bundle="${lang}"/></th>
                <th><fmt:message key="status" bundle="${lang}"/></th>
                <th><fmt:message key="actions" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.firstName}" /></td>
                    <td><c:out value="${student.lastName}" /></td>
                    <td><c:out value="${student.email}" /></td>
                    <td><c:out value="${student.coursesNames}" /></td>
                    <c:choose>
                        <c:when test="${student.status == 'Blocked'}"> <td style="color: red"><c:out value="${student.status}" /></td></c:when>
                        <c:otherwise><td style="color: green"><c:out value="${student.status}" /></td></c:otherwise>
                    </c:choose>
                    <td><a href="controller?action=blockStudent&id=<c:out value='${student.id}'/>" class="btn btn-danger"><fmt:message key="block" bundle="${lang}"/> </a><a href="controller?action=unblockStudent&id=<c:out value='${student.id}'/>" class="btn btn-success"> <fmt:message key="unblock" bundle="${lang}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
