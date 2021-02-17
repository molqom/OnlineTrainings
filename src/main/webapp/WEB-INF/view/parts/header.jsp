<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ttt" uri="trainingsTags" %>
<%@ page isELIgnored="false" %>

<c:set var="current" value="${sessionScope.lang}" scope="session"/>
<c:set var="role" value="${sessionScope.role}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${sessionScope.lang}"/>
</c:if>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<div id="header">
    <div id="header-name"><fmt:message key="header.name"/></div>
    <div id="date">
        <ttt:date/>
    </div>
    <c:if test="${not empty role}">
        <div id="logout">
            <a href="/WebApp/controller?command=logout">
                <button>
                    <fmt:message key="header.logout"/>
                </button>
            </a>
        </div>
    </c:if>
    <div id="lang">
        <form action="/WebApp/controller?command=localization" method="post">
            <button name="lang" type="submit" value="ru">Ru</button>
            <button name="lang" type="submit" value="en">En</button>
            <button name="lang" type="submit" value="by">By</button>
        </form>
    </div>
</div>


