<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Reg</title>
</head>
<body>
	<jsp:include page="/jsp/Header.jsp" />
	<div class="ml-3 mt-3">
		<h1 class="text-center mb-3">Регистрация</h1>
		<form action="" method="post" class="w-25 mx-auto">
			<div class="mb-3">
				<label for="login" class="form-label">Login</label>
				<input name="login" type="text" class="form-control" id="login">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
				<input name="password" type="password" class="form-control" id="password">
			</div>

			<button class="btn btn-primary">Зарегаться</button>
		</form>
	</div>
</body>
</html>
