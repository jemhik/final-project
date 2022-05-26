<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 18.04.2022
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Course Journal</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/teacherHeader.jsp"/>

<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="studentAnswers" bundle="${lang}"/></h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Student Id</th>
                <th><fmt:message key="answer" bundle="${lang}"/></th>
                <th><fmt:message key="asmnt" bundle="${lang}"/></th>
                <th><fmt:message key="rate" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.student_id}" /></td>
                    <td><c:out value="${course.studentSolution}" /></td>
                    <td><c:out value="${course.assessment}" /></td>
                    <td>
                        <form method="post" action="controller?action=rateStudent&id=${course.student_id}&name=${course.name}">
                            <input type="number" value="assessment" name="assessment">
                            <input type="submit" value="Ok" class="btn btn-success">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
