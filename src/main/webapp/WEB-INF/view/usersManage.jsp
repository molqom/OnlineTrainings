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
    <h2><fmt:message key="users"/> </h2>
    <table class="table">
        <tr>
            <th><fmt:message key="table.login" /></th>
            <th><fmt:message key="table.name" /></th>
            <th><fmt:message key="table.surname" /></th>
            <th><fmt:message key="table.role" /></th>
            <th><fmt:message key="table.active" /></th>
        </tr>

        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.role}</td>
                <c:if test="${user.active == 'TRUE'}">
                    <td>Active</td>
                </c:if>
                <c:if test="${user.active == 'FALSE'}">
                    <td>Ban</td>
                </c:if>
                <td>
                    <form action="/WebApp/controller?command=lock" method="post">
                        <button class="lock-button" name="lock" type="submit" value="${user.id}">
                            <fmt:message key="button.lock"/>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="/WebApp/controller?command=unlock" method="post">
                        <button class="unlock-button" name="unlock" type="submit" value="${user.id}">
                            <fmt:message key="button.unlock"/>
                        </button>
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