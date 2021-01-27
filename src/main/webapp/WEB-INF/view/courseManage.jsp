
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Course manage</title>
</head>
<body>
<h2>List of courses:</h2>
<table class="table">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>teacher id</th>
    </tr>

    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.teacher_id}</td>
            <td><form action="/WebApp/controller?command=deleteCourse" method="post">
                <button class="lock-button" name ="delete" type="submit" value="${course.id}">Delete</button>
            </form></td>
        </tr>
    </c:forEach>

</table>
<form action="/WebApp/controller?command=addCourse" method="post">
    <input name="name" type="text"/>
    <input name="teacher-id" type="text"/>
    <button type="submit">Add course</button>
</form>
<div id="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>
