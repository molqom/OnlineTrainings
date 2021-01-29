<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="current" value="${param.lang}" scope="session"/>
<c:set var="pagination" value="/WebApp/controller?command=trainings"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${param.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session"/>
<html>
<head>
    <title>Online trainings</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/style.css">

</head>
<body>
<div class="info">
    <h1><fmt:message key="ban.oops"/></h1>
    <h2><fmt:message key="ban.info"/></h2>

    <a href="/WebApp/controller?command=login">
        <button class="to-registration">Login page</button>
    </a>
</div>

</body>
</html>
