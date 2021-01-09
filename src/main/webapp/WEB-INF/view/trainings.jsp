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

<div class="info">
    <h1>Trainings list:</h1>
    <h2>${errorMessage}</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Teacher</th>
        </tr>

        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.teacherName}</td>
                <c:if test="${role.equals('ADMIN')}">
                    <td>
                        <form action="/WebApp/controller?command=deleteCourse" method="post">
                            <button name="delete" type="submit" value="${course.id}">Delete</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${role.equals('USER')}">
                    <td>
                        <form action="/WebApp/controller?command=subscribe" method="post">
                            <button name="course_id" type="submit" value="${course.id}">Subscribe</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <ul id="pages">

    </ul>
    <c:if test="${role.equals('ADMIN')}">
        <form action="/WebApp/controller?command=addCourse" method="post">
            <label>
                <input name="name" type="text"/>Name of course
            </label>
            <label>
                <input name="teacher_id" type="text"/>Teacher id
            </label>
            <button type="submit">Add course</button>
        </form>
    </c:if>
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