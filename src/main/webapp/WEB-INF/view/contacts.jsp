<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="current" value="${param.lang}" scope="session" />
<c:if test="${not empty current}">
    <fmt:setLocale value="${param.lang}"/>
</c:if>
<fmt:setBundle basename="resources" scope="session" />
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/style.css">
</head>
<body>
<h2>Contacts page</h2>

<jsp:include page="parts/header.jsp" />
<jsp:include page="parts/menu.jsp" />

</body>
</html>