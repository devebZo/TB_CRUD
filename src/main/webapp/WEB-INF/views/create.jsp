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
	<title>Create Page</title>
</head>
<body>

	<header>
		<div class="container">
			<h1>Create Page</h1>
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
			<form id='createForm'>
				<table>
					<tr>
						<th>작성자</th>
						<td><input type='text' id='writer' name='writer'></td>
					</tr>
					<tr>
						<th>글 제목</th>
						<td><input type='text' id='title' name='title'></td>
					</tr>
					<tr>
						<th rowspan="2">글 내용</th>
						<td rowspan="2"><textarea id='content' name='content'></textarea></td>
					</tr>
				</table>
				<button id='createBtn'>글쓰기</button> 
				<button onclick='goToPreviousPage(event)'>이전</button>
			</form>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		$(function(){
			$('#createBtn').on('click',function(event){
				event.preventDefault();
				
				var chkWriter = $('#writer').val();
				var chkTitle = $('#title').val();
				
				if(chkTitle == '' || chkWriter == ''){
					alert('작성하지 않은 내용이 있습니다.');
				} else {
					$('#createForm').attr({
							'action' : '/create',
							'method' : 'post'
						}).submit();
				}
			});
		})
		
		function goToPreviousPage(event) {
			event.preventDefault();
			
			window.history.back();
		}
	</script>

</body>
</html>