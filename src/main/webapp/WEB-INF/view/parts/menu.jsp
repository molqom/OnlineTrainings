<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="current" value="${sessionScope.lang}" scope="session"/>
<c:set var="role" value="${sessionScope.role}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session"/>
<div class="menu">
    <ul class="menu-ul">
        <li><a href="/WebApp/controller?command=main"><fmt:message key="menu.main"/></a></li>
        <c:if test="${role.equals('ADMIN')}">
            <li><a href="/WebApp/controller?command=usersManage"><fmt:message key="menu.usersManage"/></a></li>
        </c:if>
        <li><a href="/WebApp/controller?command=trainings"><fmt:message key="menu.trainings"/></a></li>
        <c:if test="${role.equals('USER')}">
            <li><a href="/WebApp/controller?command=subscriptions"><fmt:message key="menu.subscriptions"/></a></li>
        </c:if>
        <c:if test="${role.equals('TEACHER')}">
            <li><a href="/WebApp/controller?command=students"><fmt:message key="menu.students"/></a></li>
        </c:if>
    </ul>
</div>