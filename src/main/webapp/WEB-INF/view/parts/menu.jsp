<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="current" value="${sessionScope.lang}" scope="session" />
<c:if test="${not empty current}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session" />
<div class="menu">
    <h3><fmt:message key="menu.menu" /></h3>
    <ul>
        <li><a href = "/WebApp/controller?command=main"><fmt:message key="menu.main" /></a></li>
        <li><a href = "/WebApp/controller?command=trainings"><fmt:message key="menu.trainings" /></a></li>
        <li><a href = "/WebApp/controller?command=teachers"><fmt:message key="menu.teachers" /></a></li>
        <li><a href = "/WebApp/controller?command=news"><fmt:message key="menu.news" /></a></li>
        <li><a href = "/WebApp/controller?command=info"><fmt:message key="menu.info" /></a></li>
        <li><a href = "/WebApp/controller?command=contacts"><fmt:message key="menu.contacts" /></a></li>
    </ul>
</div>