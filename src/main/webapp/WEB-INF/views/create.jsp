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
			<form enctype="multipart/form-data" id='createForm'>
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
				<br>
				<button id='plsImg'>이미지 등록</button>
				<div id='imgPlace'></div>
				<br>
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
				    var files = $("[name=imgs]")[0].files;
				    var checks = [];

				    for (var i = 0; i < files.length ; i++){
				        checks.push(checkImg(files[i]));
				    }

				    Promise.all(checks).then(function(results) {
				        var exChk = results.reduce((sum, curr) => sum + curr, 0);

				        if (exChk === 0) {

							$('#createForm').attr({
									'action' : '/create',
									'method' : 'post'
								}).submit();
				        } else {
							event.preventDefault();
							alert("이미지의 크기가 너무 큽니다. 가로세로 500px을 넘기지 마세요.");
				        }
				    });
				}
			});
			
			$("#plsImg").on("click",function(event){
				event.preventDefault();
				var putImg = '<input type="file" id="imgBtn" name="imgs" accept="image/*" value="파일 찾기"> <button id="delImgPut">-</button><br>';
				
				$("#imgPlace").append(putImg);
			});
			
			$("#imgPlace").on("click", "#delImgPut", function(event){
			    event.preventDefault();
			    $(this).prev().remove(); // remove the input[type=file]
			    $(this).next().remove(); // remove the <br>
			    $(this).remove(); // remove the input[type=button]
			});
		})
		
		function goToPreviousPage(event) {
			event.preventDefault();
			
			window.history.back();
		}
		
		function checkImg(file) {
		    return new Promise(function(resolve, reject) {
		        var _URL = window.URL || window.webkitURL;
		        var img = new Image();

		        img.onload = function() {
		            if (this.width > 500 || this.height > 500) {
		                resolve(1);
		            } else {
		                resolve(0);
		            }
		        };

		        img.onerror = function() {
		            reject(new Error("Image Load Error"));
		        };

		        img.src = _URL.createObjectURL(file);
		    });
		}
	</script>

</body>
</html>