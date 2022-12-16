<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>All Posts</title>
</head>
<body>
	<jsp:include page="/jsp/Header.jsp" />
	<div class="mt-3 ml-3">
		<c:forEach var="post" items="${posts}">
			<div class="card w-25 mx-auto mt-3" >
				<img src="/images/${post.getImgName()}" alt="img" class="card-img" style="object-fit: contain" />
				<div class="card-body">
					<h5 class="card-title">${post.getTitle()}</h5>
					<p class="card-text">Click the button to read this post</p>
					<a href="/posts/${post.getId()}" class="btn btn-primary">Read</a>
					<div class="mt-3" style="font-size: 20px">
						<c:if test="${showLike != null}">
							<form action="/like" method="post">
								<button name="postId" value="${post.getId()}" style="background: none; border: none; outline: none">
									<c:if test="${post.isLiked()}">
										<i style="color: red" class="fa-solid fa-heart"></i>
									</c:if>
									<c:if test="${post.isLiked() == false}">
										<i class="fa-solid fa-heart"></i>
									</c:if>
								</button>
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
