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
    <title>Subscriptions</title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>
<div class="info">
    <h1>Subscriptions</h1>

    <table class="table">
        <tr>
            <th>Course name</th>
            <th>Teacher name</th>
            <th>Teacher surname</th>
            <th>Grade</th>
            <th>Feedback</th>
        </tr>

        <c:forEach items="${subscriptions}" var="subscription">
            <tr>
                <td>${subscription.courseName}</td>
                <td>${subscription.teacherName}</td>
                <td>${subscription.teacherSurname}</td>
                <c:if test="${subscription.grade != 0}">
                    <td>${subscription.grade}</td>
                </c:if>
                <c:if test="${subscription.grade == 0}">
                    <td></td>
                </c:if>
                <td>${subscription.feedback}</td>
                <td>
                    <form action="/WebApp/controller?command=unsubscribe" method="post">
                        <button class="lock-button" name="subscription_id" type="submit" value="${subscription.id}">
                            Unsubscribe
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>
