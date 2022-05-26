
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="WEB-INF/view/headers/header.jsp"/>
<jsp:include page="WEB-INF/view/buttons.html"/>
<div align="center">
<h1><fmt:message key="register" bundle="${lang}"/></h1>
<form method="post" action="controller?action=register">
<table>
    <tr><td class="text-center"><fmt:message key="firstName" bundle="${lang}"/>: </td><td><input type="text" name="firstName" class="form-control" pattern="^[a-zA-Z]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required=""></td></tr>
    <tr><td class="text-center"><fmt:message key="lastName" bundle="${lang}"/>: </td><td><input type="text" name="lastName" class="form-control" pattern="^[a-zA-Z]+" title="<fmt:message key="englishLetters" bundle="${lang}"/>" required=""></td></tr>
    <tr><td class="text-center"><fmt:message key="email" bundle="${lang}"/>: </td><td><input type="email" name="email" class="form-control" required=""></td></tr>
    <tr><td class="text-center"><fmt:message key="pass" bundle="${lang}"/>: </td><td><input type="password" name="password" class="form-control" pattern="^[a-zA-Z1-9]+" title="<fmt:message key="validPass" bundle="${lang}"/>" required="required"></td></tr>
    <tr><td></td><td><input class="btn btn-primary btn-block mb-3" type="submit" value="<fmt:message key="register" bundle="${lang}"/>" class="form-control"></td></tr>
</table>
</form>
    <tr>
        <td><span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
    </tr>
</div>
</body>
</html>
