<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:set var="current" value="${sessionScope.lang}" scope="session"/>
<c:set var="pagination" value="/WebApp/controller?command=usersManage"/>
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
<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>
<div class="info">
    <h1>Admin page</h1>
    <h2>List of users:</h2>
    <table class="table">
        <tr>
            <th>id</th>
            <th>login</th>
            <th>name</th>
            <th>surname</th>
            <th>role</th>
            <th>active</th>
        </tr>

        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.role}</td>
                <td>${user.active}</td>
                <td>
                    <form action="/WebApp/controller?command=lock" method="post">
                        <button class="lock-button" name="lock" type="submit" value="${user.id}">Lock</button>
                    </form>
                </td>
                <td>
                    <form action="/WebApp/controller?command=unlock" method="post">
                        <button class="unlock-button" name="unlock" type="submit" value="${user.id}">Unlock</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
    <ul id="pagination">
    </ul>
</div>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
<script>
    let currentPage = ${numOfPage};
    let pagesQuantity = ${pagesQuantity};
    let pagination = '${pagination}';
</script>
<script src="<c:url value="/static/js/coursesPagination.js"/>"></script>
</body>
</html>