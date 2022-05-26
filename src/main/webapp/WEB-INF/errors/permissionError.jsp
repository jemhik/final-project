<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 02.04.2022
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>

<html>
<head>
    <title>Permission error</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/headers/header.jsp"/>
<jsp:include page="/WEB-INF/view/buttons.html"/>
<div align="center">
    <h1><fmt:message key="errorMasage" bundle="${lang}"/></h1>
    <a href="${pageContext.request.contextPath}/login.jsp" class="button button"><fmt:message key="login" bundle="${lang}"/></a>
    <a href="${pageContext.request.contextPath}/register.jsp" class="button button"><fmt:message key="register" bundle="${lang}"/></a>

</div>

</body>
</html>
