<%@ page import="org.example.utils.UserUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>Post</title>
</head>
<body>
	<jsp:include page="/jsp/Header.jsp" />
	<div class="mt-3 ml-3 w-75 mx-auto">
		<h2 class="text-center">${post.getTitle()}</h2>
		<hr class="mt-3 mb-3">
		<div class="mb-5">
			${post.getText()}
		</div>

		<div class="mb-5">
			<h3 class="text-center">Comments</h3>
			<hr class="mt-3 mb-3">
			<ul class="list-group">
				<c:forEach var="comment" items="${recension}">
					<li class="list-group-item">
						${UserUtils.getRecensionAuthorName(comment.getUserID())}: ${comment.getText()}
					</li>
				</c:forEach>
			</ul>
		</div>

		<c:if test="${UserUtils.isPostBelongsToAuthUser(post.getUserID()) || UserUtils.isAdmin()}">
			<div class="mb-5">
				<form action="<c:url value="/posts/edit"/>" method="get" class="d-inline-block">
					<button name="postID" value="${post.getId()}" class="btn btn-outline-info mr-3">Edit</button>
				</form>
				<form action="<c:url value="/posts/delete"/>" method="post" class="d-inline-block">
					<button name="postID" value="${post.getId()}" class="btn btn-outline-danger">Delete</button>
				</form>
			</div>
		</c:if>

		<c:if test="${UserUtils.isAuth()}">
			<div class="mb-5">
				<div class="mb-3">
					<form method="post" action="">
						<label for="recension" class="form-label">Оставить комментарий</label>
						<input name="recension" type="text" class="form-control" id="recension">
						<button class="btn btn-outline-info mt-3">Добавить</button>
					</form>

				</div>
			</div>
		</c:if>


	</div>
</body>
</html>
