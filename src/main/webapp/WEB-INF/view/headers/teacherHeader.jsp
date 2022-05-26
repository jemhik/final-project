<%--
  Created by IntelliJ IDEA.
  User: Сергій
  Date: 12.04.2022
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" var="lang"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Header</title>
    <style>
        * {box-sizing: border-box;}

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .header {
            overflow: hidden;
            background-color: #f1f1f1;
            padding: 20px 10px;
        }

        .header a {
            float: left;
            color: black;
            text-align: center;
            padding: 12px;
            text-decoration: none;
            font-size: 18px;
            line-height: 25px;
            border-radius: 4px;
        }

        .header a.logo {
            font-size: 25px;
            font-weight: bold;
        }

        .header a:hover {
            background-color: #ddd;
            color: black;
        }

        .header a.active {
            background-color: dodgerblue;
            color: white;
        }

        .header-right {
            float: right;
        }

        @media screen and (max-width: 500px) {
            .header a {
                float: none;
                display: block;
                text-align: left;
            }

            .header-right {
                float: none;
            }
        }
    </style>
    <title>Title</title>
</head>
<body>
<div class="header">
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo"><fmt:message key="training.center" bundle="${lang}"/></a>
    <div class="header-right">
        <a class="active" href="controller?action=logout"><fmt:message key="logout" bundle="${lang}"/></a>
        <a href="controller?action=teacher"><fmt:message key="profile" bundle="${lang}"/></a>
        <a href="controller?action=changeLanguage&lang=en">en</a>
        <a href="controller?action=changeLanguage&lang=ua">ua</a>
    </div>
</div>
</body>
</html>
