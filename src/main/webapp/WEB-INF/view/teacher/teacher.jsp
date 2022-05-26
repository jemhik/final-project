<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 22.02.2022
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/WEB-INF/view/headers/teacherHeader.jsp"/>
<jsp:include page="/WEB-INF/view/buttons.html"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<div align="center">
<h1><fmt:message key="teacherPage" bundle="${lang}"/></h1>
<br>
<div class="col-lg-8">
    <div class="card mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-3">
                    <p class="mb-0"><fmt:message key="firstName" bundle="${lang}"/></p>
                </div>
                <div class="col-sm-9">
                    <p class="text-muted mb-0"><%=request.getAttribute("firstName")%></p>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <p class="mb-0"><fmt:message key="lastName" bundle="${lang}"/></p>
                </div>
                <div class="col-sm-9">
                    <p class="text-muted mb-0"><%=request.getAttribute("lastName")%></p>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <p class="mb-0"><fmt:message key="email" bundle="${lang}"/></p>
                </div>
                <div class="col-sm-9">
                    <p class="text-muted mb-0"><%=request.getAttribute("email")%></p>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-sm-3">
                    <p class="mb-0"><fmt:message key="role" bundle="${lang}"/></p>
                </div>
                <div class="col-sm-9">
                    <p class="text-muted mb-0"><%=request.getAttribute("role")%></p>
                </div>
            </div>
        </div>
    </div>
</div>
    <br>
    <a href="controller?action=showTeachersCourses" class="button button"><fmt:message key="courses" bundle="${lang}"/></a>
</div>

</body>
</html>
