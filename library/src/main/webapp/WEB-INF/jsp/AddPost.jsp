
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Add post</title>
</head>
<body>
	<jsp:include page="/jsp/Header.jsp" />
	<div class="mt-3 ml-3">
		<h2 class="text-center">Добавить пост</h2>
		<form action="" method="post" class="w-50 mx-auto" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="title" class="form-label">Название поста</label>
				<input name="title" type="text" class="form-control" id="title">
			</div>

			<div class="mb-3">
				<label for="text" class="form-label">Текст поста</label>
				<textarea rows="9" name="text" class="form-control" id="text"></textarea>
			</div>

			<div class="mb-3">
				<label for="file" class="form-label">Картинка</label>
				<input type="file" name="img" class="form-control" id="file" />
			</div>

			<button class="btn btn-outline-primary">Добавить</button>
		</form>
	</div>
</body>
</html>
