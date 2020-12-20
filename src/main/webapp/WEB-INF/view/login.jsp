<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Login page
    <h1>
        ${successMessage}
        <form method="POST" action="/WebApp/controller?command=login">
            <input type="text" name="login"/>
            <input type="password" name="password"/>
            <input type="submit"/>
        </form>
        ${errorMessage}
        <p>
            <a href="registration.jsp">Registration</a>
        </p>
</body>
</html>