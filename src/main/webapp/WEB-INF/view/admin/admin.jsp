<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 22.02.2022
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/adminHeader.jsp"/>
<jsp:include page="/WEB-INF/view/buttons.html"/>

<div align="center">
    <h1><fmt:message key="adminPage" bundle="${lang}"/></h1>
    <a href="controller?action=showTeachers" class="button button"><fmt:message key="teachers" bundle="${lang}"/></a>
    <a href="controller?action=showStudents" class="button button"><fmt:message key="students" bundle="${lang}"/></a>
    <a href="controller?action=showCourses" class="button button"><fmt:message key="courses" bundle="${lang}"/></a>
</div>
</body>
</html>
