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
    </head>
    <body>
        <h1>Admin page</h1>
        <h2>List of users:</h2>
        <table>
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
                    <td><form action="/WebApp/controller?command=lock" method="post">
                        <button name ="lock" type="submit" value="${user.id}">Lock</button>
                        <button name ="unlock" type="submit" value="${user.id}">Unlock</button>
                    </form></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>