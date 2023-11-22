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
	<title>List Page</title>
</head>
<body>

	<header>
	<div class="container">
		<h1>List Page</h1>
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
			<button onclick='createBoardBtn(event)'>글쓰기</button>
			<table>
				<thead>
					<tr>
						<th><input type='checkbox' id='allChk'></th> <!-- 체크박스 -->
						<th>번호</th>
						<th>글쓴이</th>
						<th>제목</th>
						<th>만든 날짜</th>
						<th>수정 날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="boardList" items="${boardList}">
						<tr>
							<td><input type="checkbox" name="chkBox" value="${boardList.seq}"></td>
							<td>${boardList.boardSeq}</td>
							<td>${boardList.writer}</td>
							<td><a href="${path}/read/${boardList.boardSeq}">${boardList.title}</a></td> <!-- title -->
							<td><fmt:formatDate value="${boardList.regDate}" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${boardList.uptDate}" pattern="yyyy-MM-dd" /></td>
							<td>${boardList.viewCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
		function createBoardBtn(event){
			event.preventDefault();
			
			window.location.href = '/create';
		}
	</script>

</body>
</html>