<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="current" value="${param.lang}" scope="session"/>
<%--<c:set var="courses" value="${param.courses}" scope="session"/>--%>
<c:if test="${not empty current}">
    <fmt:setLocale value="${param.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session"/>
<html>
<head>
    <title>Trainings page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/style.css">

</head>
<body>

<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>

<%--<table id="table"></table>--%>
<%--<ul id="pagination"></ul>--%>
<div class="info">
<h1>Trainings list:</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Teacher</th>
    </tr>

    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.name}</td>
            <td>${course.teacherName}</td>
        </tr>
    </c:forEach>

</table>
<ul id="pages">

</ul>
</div>
<script>
    let pages = document.querySelector('#pages');

    let pagesQuantity = '${pagesQuantity}';
    let form = document.createElement('form');
    form.setAttribute("action", "/WebApp/controller?command=trainings");
    form.setAttribute("method", "POST");
    for (let i = 1; i <= pagesQuantity; i++) {
        let button = document.createElement('button');
        button.setAttribute("name", "numOfPage")
        button.setAttribute("type", "submit")
        button.setAttribute("value", i);
        button.innerText = i;
        form.appendChild(button);
    }
    pages.appendChild(form);
</script>
</body>
</html>