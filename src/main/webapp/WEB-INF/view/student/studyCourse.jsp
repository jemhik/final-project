<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 16.04.2022
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Learning</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/WEB-INF/view/headers/studentHeader.jsp"/>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
<blockquote class="blockquote">
    <p>${course.materials}</p>
    <label><fmt:message key="task" bundle="${lang}"/></label>
    <p>${course.task}</p>
</blockquote>
            <form action="controller?action=finishCourse&name=${name}" method="post">
            <fieldset class="form-group">
                 <textarea type="text" value="studentSolution" class="form-control" name="studentSolution" pattern="^[a-zA-Z]+[1-9]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required"></textarea>
            </fieldset>
            <button type="submit" class="btn btn-success"><fmt:message key="finish" bundle="${lang}"/></button>
        </form>
        </div>
    </div>
</div>
</body>
</html>
