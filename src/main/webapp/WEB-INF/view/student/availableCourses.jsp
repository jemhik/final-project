<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 15.04.2022
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Courses</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/studentHeader.jsp"/>

<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center"><fmt:message key="availableCourses" bundle="${lang}"/></h3>
        <hr>
        <div class="container text-left">
            <a href="controller?action=notStartedCourses" class="btn btn-success"><fmt:message key="showNotStarted" bundle="${lang}"/></a>
            <a href="controller?action=coursesInProgress" class="btn btn-success"><fmt:message key="showInProgress" bundle="${lang}"/></a>
            <a href="controller?action=endedCourses" class="btn btn-success"><fmt:message key="showEnded" bundle="${lang}"/></a>
        </div>
        <div class="container text-center">
            <form method="post" action="controller?action=availableCourses">
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
            <form method="post" action="controller?action=availableCourses">
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
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Teacher Id</th>
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
                    <td><a href="controller?action=applyForCourse&teacher=${course.teacher}&name=${course.name}" class="btn btn-success"><fmt:message key="apply" bundle="${lang}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="container">
            <table id="pages" border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <td>
                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${currentPage != 1}">
                            <a href="controller?action=availableCourses&page=${currentPage - 1}">prev</a>
                        </c:if>
                    </td>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="controller?action=availableCourses&page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td>
                        <%--For displaying Next link --%>
                        <c:if test="${currentPage lt noOfPages}">
                            <a href="controller?action=availableCourses&page=${currentPage + 1}">next</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
