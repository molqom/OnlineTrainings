<html>
<body>
<h2>Registration page</h2>
<form method="POST" action="/WebApp/controller?command=registration">
<input type="text" name="login" />
<input type="password" name="password" />
<input type="password" name="repeat-password" />
<input type="submit" />
</form>
${errorMessage}
</body>
</html>