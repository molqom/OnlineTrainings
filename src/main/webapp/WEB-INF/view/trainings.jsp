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
    <title>Trainings page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/style.css">

</head>
<body>

<jsp:include page="parts/header.jsp"/>
<jsp:include page="parts/menu.jsp"/>

<div class="info">
    <h1>Trainings list:</h1>
    <h2>${errorMessage}</h2>
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Teacher</th>
        </tr>

        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.name}</td>
                <td>${course.teacherName} ${course.teacherSurname}</td>
                <c:if test="${role.equals('ADMIN')}">
                    <td>
                        <form action="/WebApp/controller?command=deleteCourse" method="post">
                            <button class="delete-button" name="delete" type="submit" value="${course.id}">Delete</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${role.equals('USER')}">
                    <td>
                        <form action="/WebApp/controller?command=subscribe" method="post">
                            <button class="subscribe-button" name="course_id" type="submit" value="${course.id}">Subscribe</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <ul id="pagination">

    </ul>
    <c:if test="${role.equals('ADMIN')}">
        <form action="/WebApp/controller?command=addCourse" method="post">
            <label>
                <input name="name" type="text"/>Name of course
            </label>
            <label>
                <input name="teacher_id" type="text"/>Teacher id
            </label>
            <button class="submit" type="submit">Add course</button>
        </form>
    </c:if>
</div>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
<%--<script src="../../static/js/coursesPagination.js">--%>
<script>
    let pages = document.querySelector('#pagination');
    //add
    let currentPage = '${numOfPage}';
    //
    let pagesQuantity = '${pagesQuantity}';
    let form = document.createElement('form');
    form.setAttribute("action", '${pagination}');
    form.setAttribute("method", "POST");
    if (pagesQuantity < 6) {
        for (let i = 1; i <= pagesQuantity; i++) {
            addButton(i);
        }
    } else {
        if (currentPage > 2) {
            addButton(1);
        }
        if (currentPage > 3) {
            addEllipsis();
        }
        if (currentPage != 1) {
            addButton(currentPage - 1);
        }
        addButton(currentPage);
        if (currentPage != pagesQuantity) {
            addButton(currentPage + 1);
        }
        if (currentPage < pagesQuantity - 2) {
            addEllipsis();
        }
        if (currentPage < pagesQuantity - 1)
            addButton(pagesQuantity);
    }

    function addButton(buttonValue) {
        let button = document.createElement('button');
        if (currentPage == buttonValue) {
            button.setAttribute("style", "border: 1px solid blue")
        }
        button.setAttribute("name", "numOfPage")
        button.setAttribute("type", "submit")
        button.setAttribute("value", buttonValue);
        button.innerText = buttonValue;
        form.appendChild(button);
    }

    function addEllipsis() {
        let b = document.createElement('b');
        b.innerText = '...';
        form.appendChild(b);
    }

    pages.appendChild(form);

</script>
</body>
</html>