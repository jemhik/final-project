<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 31.03.2022
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/view/headers/adminHeader.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title><fmt:message key="newCourse" bundle="${lang}"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <form action="controller?action=addCourse" method="post">
                    <caption>
                        <h2>
                            <fmt:message key="newCourse" bundle="${lang}"/>
                        </h2>
                    </caption>

                <fieldset class="form-group">
                    <label><fmt:message key="name" bundle="${lang}"/></label> <input type="text" value="<c:out value='${course.name}' />" class="form-control" name="name" pattern="^[a-zA-Z]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="description" bundle="${lang}"/></label> <input type="text" value="<c:out value='${course.description}' />" class="form-control" name="description" pattern="^[a-zA-Z ]+[1-9]*" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="teach" bundle="${lang}"/></label> <input type="number" value="<c:out value='${course.teacher_id}' />" class="form-control" name="teacher_id">
                </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="duration" bundle="${lang}"/></label> <input type="number" value="<c:out value='${course.duration}' />" class="form-control" name="duration">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="material" bundle="${lang}"/></label> <textarea type="text" value="<c:out value='${course.materials}' />" class="form-control" name="materials" pattern="^[a-zA-Z ]+[1-9]*" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required"></textarea>
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="task" bundle="${lang}"/></label> </label> <textarea type="text" value="<c:out value='${course.task}' />" class="form-control" name="task" pattern="^[a-zA-Z ]+[1-9]*" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required"></textarea>
                    </fieldset>

                    <button type="submit" class="btn btn-success"><fmt:message key="save" bundle="${lang}"/><</label> </label></button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
