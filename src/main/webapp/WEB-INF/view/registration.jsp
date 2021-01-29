<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:set var="current" value="${sessionScope.lang}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/cubestyle.css">
    <title>Online trainings</title>
</head>
<body>
<div class="form">
<form method="POST" action="/WebApp/controller?command=registration">
    <h1>Registration page</h1>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>
    <input type="text" placeholder="Enter login" name="login" pattern="\w{4,16}"
           title="Login must have from 4 to 16 symbols" required/>
    <input type="text" placeholder="Enter name" name="name" pattern="\w{1,16}"
           title="Name must have from 1 to 16 symbols" required/>
    <input type="text" placeholder="Enter surname" name="surname" pattern="\w{4,16}"
           title="Surname must have from 4 to 16 symbols" required/>
    <input type="password" placeholder="Enter password" name="password" pattern="\w{8,32}"
           title="Password must have from 8 to 32 symbols" required/>
    <input type="password" placeholder="Repeat password" name="repeat-password" pattern="\w{8,32}"
           title="Username must have from 8 to 32 symbols" required/>

    <button class="submit" type="submit"><fmt:message key="button.register"></button>
</form>
</div>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>