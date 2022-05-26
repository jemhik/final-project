<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 30.03.2022
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Courses</title>
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
        <h3 class="text-center"><fmt:message key="coursesList" bundle="${lang}"/></h3>
        <hr>
        <div class="container text-left">
            <a href="controller?action=openAddCourse" class="btn btn-success"><fmt:message key="newCourse" bundle="${lang}"/></a>
        </div>
        <div class="container text-center">
            <form method="post" action="controller?action=showCourses">
                <select name="order" class="form-select">
                <option selected disabled><fmt:message key="orderBy" bundle="${lang}"/></option>
                <option value="number_of_students"><fmt:message key="byNumberOfStudents" bundle="${lang}"/></option>
                <option value="duration_en"><fmt:message key="byDuration" bundle="${lang}"/></option>
                <option value="name"><fmt:message key="nameA-Z" bundle="${lang}"/></option>
                <option value="name Desc"><fmt:message key="nameZ-A" bundle="${lang}"/></option>
            </select>
                <input type="submit" value="<fmt:message key="submit" bundle="${lang}"/>">
            </form>
        </div>
        <div class="container text-center">
            <form method="post" action="controller?action=showCourses">
                <select name="filter" class="form-select">
                    <option selected disabled><fmt:message key="filterBy" bundle="${lang}"/></option>
                    <option disabled><fmt:message key="teach" bundle="${lang}"/></option>
                    <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.id}">
                        <c:out value="${teacher.firstName}" /> <c:out value="${teacher.lastName}" />
                    </option>
                    </c:forEach>
                    <option disabled><fmt:message key="courseTopic" bundle="${lang}"/></option>
                    <c:forEach var="byCourse" items="${byCourses}">
                        <option value="${byCourse.name}">
                            <c:out value="${byCourse.name}" />
                        </option>
                    </c:forEach>
                </select>
                <input type="submit" value="<fmt:message key="submit" bundle="${lang}"/>">
            </form>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="teach" bundle="${lang}"/></th>
                <th><fmt:message key="name" bundle="${lang}"/></th>
                <th><fmt:message key="description" bundle="${lang}"/></th>
                <th><fmt:message key="duration" bundle="${lang}"/></th>
                <th><fmt:message key="numberOfStudents" bundle="${lang}"/></th>
                <th><fmt:message key="actions" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td><c:out value="${course.teacher}" /></td>
                    <td><c:out value="${course.name}" /></td>
                    <td><c:out value="${course.description}" /></td>
                    <td><c:out value="${course.duration}" /></td>
                    <td><c:out value="${course.numberOfStudents}" /></td>
                    <td><a class="btn btn-danger" href="controller?action=deleteCourse&id=<c:out value="${course.teacher}" />&name=<c:out value="${course.name}" />"><fmt:message key="delete" bundle="${lang}"/>  </a><a class="btn btn-success" href="controller?action=openUpdateCourse&id=<c:out value='${course.teacher}' />">  <fmt:message key="update" bundle="${lang}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
