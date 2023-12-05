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

	<form id='updateForm'>
		<header>
			<div class="container">
				<h1 id='title1'>Read Page : ${board.title}</h1>
				<input type='hidden' id='title2' name='title' value='${board.title}'>
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
				<input type='hidden' name='seq' value='${board.boardSeq }'>
				<table>
					<thead>
						<tr>
							<th rowspan="2">작성자</th>
							<td rowspan="2">${board.writer }</td>
							<th>업로드 날짜</th>
							<td><fmt:formatDate value="${board.regDate }"
									pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<th>업데이트 날짜</th>
							<td><fmt:formatDate value="${board.uptDate }"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td id="readTd" colspan="4">${board.content }</td>
							<td id="updateTd" style="display: none;" colspan="4"><textarea
									name='content'>${board.content }</textarea></td>
						</tr>
					</tbody>

					<tfoot>
						<tr>
							<td colspan='4'>
								<c:forEach items='${imgList }' var='imgs' varStatus='status'>
									<c:if test='${status.count <= imgNum }'>
										<img src="${imgs.savePath}/${imgs.saveName}" />
										<c:if test="${status.count != imgNum}">
											<br>
										</c:if>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</tfoot>
				</table>
				<button onclick='letsUpdateTime(event)' id='updateBtn'>수정</button>
				<button onclick='goToPrevPage(event)' id='prevBtn'>이전</button>
				<button onclick='doneUpdate(event)' id='doneUBtn'
					style="display: none;">수정</button>
				<button onclick='cancelUpdate(event)' id='cancelBtn'
					style="display: none;">취소</button>
			</div>
		</main>
	</form>

	<aside>
		<div class='container'>
			<h2>Sidebar</h2>
			<p>This is the sidebar</p>
		</div>
	</aside>

	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
	<script>
		function goToPrevPage(event) {
			event.preventDefault();

			window.history.back();
		}

		function letsUpdateTime(event) {
			event.preventDefault();

			$('#updateBtn').hide();
			$('#prevBtn').hide();
			$('#doneUBtn').show();
			$('#cancelBtn').show();

			$('#readTd').hide();
			$('#updateTd').show();
			$('#title1').hide();
			$('#title2').attr('type', 'text');
		}

		function doneUpdate(event) {
			event.preventDefault();

			$('#updateForm').attr({
				'action' : '/update',
				'method' : 'post'
			}).submit();
		}

		function cancelUpdate(event) {
			event.preventDefault();

			$('#doneUBtn').hide();
			$('#cancelBtn').hide();
			$('#updateBtn').show();
			$('#prevBtn').show();

			$('#updateTd').hide();
			$('#readTd').show();
			$('#title1').show();
			$('#title2').attr('type', 'hidden');
		}
	</script>

</body>
</html>