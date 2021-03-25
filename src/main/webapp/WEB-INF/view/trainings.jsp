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

<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>

<div class="info">
    <h1><fmt:message key="trainings" /></h1>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>
    <table class="table">
        <tr>
            <th><fmt:message key="table.name"/> </th>
            <th><fmt:message key="table.teacher"/></th>
        </tr>

        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.teacherName} ${course.teacherSurname}</td>
                <c:if test="${role.equals('ADMIN')}">
                    <td>
                        <form action="/WebApp/controller?command=deleteCourse" method="post">
                            <button class="lock-button" name="delete" type="submit" value="${course.id}">Delete</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${role.equals('USER')}">
                    <td>
                        <form action="/WebApp/controller?command=subscribe" method="post">
                            <button class="subscribe-button" name="course_id" type="submit" value="${course.id}">
                                <fmt:message key="button.subscribe"/>
                            </button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <ul id="pagination">
    </ul>
</div>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
<%-- Global variables --%>
<script>
    let currentPage = ${numOfPage};
    let pagesQuantity = ${pagesQuantity};
    let pagination = '${pagination}';
</script>
<script src="<c:url value="/static/js/coursesPagination.js"/>"></script>
</body>
</html>