<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<div id="header">
    <div id="header-name">Online trainings</div>
    <div id="logout">
        <a href="/WebApp/controller?command=logout">
            <button>
                <fmt:message key="header.logout"/>
            </button>
        </a>
    </div>
    <div id="lang">
        <form action="/WebApp/controller?command=localization" method="post">
            <button name="lang" type="submit" value="ru">Ru</button>
            <button name="lang" type="submit" value="en">En</button>
            <button name="lang" type="submit" value="by">By</button>
        </form>
    </div>
</div>
<%--<div id = "header">--%>
<%--	<h2 id = "text-header">Online training</h2>--%>
<%--	<a href="/WebApp/controller?command=logout">--%>
<%--	<button class = "logout-button">--%>
<%--	<fmt:message key="header.logout" />--%>
<%--	</button>--%>
<%--	</a>--%>
<%--	<div id = "lang">--%>
<%--	<form action = "/WebApp/controller?command=localization" method = "POST">--%>
<%--		<button class = "lang-button" value = "ru" type = "submit">Ru</button>--%>
<%--		<button class = "lang-button" value = "en" type = "submit">En</button>--%>
<%--	</form>--%>
<%--	</div>--%>
<%--</div>--%>


