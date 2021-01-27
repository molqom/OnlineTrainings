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
    <title>Students</title>
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>
<div class="info">
    <h1>Students:</h1>
    <table class="table">
        <tr>
            <th>Course name</th>
            <th>Student name</th>
            <th>Student surname</th>
            <th>Grade</th>
            <th>Feedback</th>
        </tr>

        <c:forEach items="${subscriptions}" var="subscription">
            <tr>
                <td>${subscription.courseName}</td>
                <td>${subscription.userName}</td>
                <td>${subscription.userSurname}</td>
                <c:if test="${subscription.grade == 0}">
                    <td>
                        <form action="/WebApp/controller?command=rate" method="post">
                            <select name="grade">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                            <input type="hidden" name="subscription_id" value="${subscription.id}">
                            <button class="submit" type="submit">Confirm</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${subscription.grade != 0}">
                    <td>${subscription.grade}</td>
                </c:if>
                <c:if test="${empty subscription.feedback}">
                    <td>
                        <form action="/WebApp/controller?command=feedback" method="post">
                            <label>
                                <input type="text" name="feedback">
                            </label>
                            <input type="hidden" name="subscription_id" value="${subscription.id}">
                            <button class="submit" type="submit">Confirm</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${not empty subscription.feedback}">
                    <td>${subscription.feedback}</td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <div id="footer">
        <jsp:include page="parts/footer.jsp"/>
    </div>
</div>
</body>
</html>
