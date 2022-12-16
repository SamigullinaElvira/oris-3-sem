<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
<script src="https://kit.fontawesome.com/c174f8ecbd.js" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg bg-light mt-3 mr-3 ml-3">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Online.blog</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href="<c:url value="/home"/>">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/reg"/>">Registration</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/auth"/>">Authorization</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/posts"/>">All posts</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/posts/add"/>">Add post</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/blog"/>">My blog</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/favorites"/>">Favorites</a>
				</li>
			</ul>
		</div>
	</div>
</nav>