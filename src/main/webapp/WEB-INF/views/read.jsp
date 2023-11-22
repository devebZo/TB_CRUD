<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/assets/css/style.css">
	<title>Read Page</title>
</head>
<body>

	<header>
	<div class="container">
		<h1>Read Page : ${board.title }</h1>
	</div>
	</header>
	
	<nav>
		<div class='container'>
			<ul>
				<li><a href='#'>Home</a></li>
				<li><a href='#'>About</a></li>
				<li><a href='#'>Contact</a></li>
			</ul>
		</div>
	</nav>
	
	<main>
		<div class='container'>
			<table>
				<thead>
					<tr>
						<th rowspan="2">작성자</th>
						<td rowspan="2">${board.writer }</td>
						<th>업로드 날짜</th>
						<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<th>업데이트 날짜</th>
						<td><fmt:formatDate value="${board.uptDate }" pattern="yyyy-MM-dd"/></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">${board.content }</td>
					</tr>
				</tbody>
			</table>
			<button>수정</button>
			<button onclick='goToPrevPage()'>이전</button>
		</div>
	</main>
	
	<aside>
		<div class='container'>
			<h2>Sidebar</h2>
			<p>This is the sidebar</p>
		</div>
	</aside>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		function goToPrevPage(){
			window.history.back();
		}
	</script>

</body>
</html>