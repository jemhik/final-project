<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            background-image: url("images/background.webp");
        }
    </style>
    <title>Training Center</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/header.jsp"/>
<jsp:include page="/WEB-INF/view/buttons.html"/>
<div align="center">
<h1><%= "Main Page" %>
</h1>
<a href="${pageContext.request.contextPath}/login.jsp" class="button button"><fmt:message key="login" bundle="${lang}"/></a>
    <a href="${pageContext.request.contextPath}/register.jsp" class="button button"><fmt:message key="register" bundle="${lang}"/></a>
</div>
</body>
</html>