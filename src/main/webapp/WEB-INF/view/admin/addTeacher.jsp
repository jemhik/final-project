<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 31.03.2022
  Time: 22:18
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
    <title>Add Teacher</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <c:if test="${user == null}">
                <form action="controller?action=addTeacher" method="post">
                    </c:if>

                    <fieldset class="form-group">
                        <label><fmt:message key="firstName" bundle="${lang}"/></label> <input type="text" value="<c:out value='${user.firstName}' />" class="form-control" name="firstName" pattern="^[a-zA-Z]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="lastName" bundle="${lang}"/></label> <input type="text" value="<c:out value='${user.lastName}' />" class="form-control" name="lastName" pattern="^[a-zA-Z]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="email" bundle="${lang}"/></label> <input type="email" value="<c:out value='${user.email}' />" class="form-control" name="email">
                    </fieldset>

                        <fieldset class="form-group">
                            <label><fmt:message key="pass" bundle="${lang}"/></label> <input type="password" value="<c:out value='${user.password}' />" class="form-control" name="password" pattern="^[a-zA-Z1-9]+" title="<fmt:message key="validPass" bundle="${lang}"/>" required="required">
                        </fieldset>

                    <button type="submit" class="btn btn-success"><fmt:message key="save" bundle="${lang}"/></button>
                </form>
        </div>
    </div>
</div>

</body>
</html>
