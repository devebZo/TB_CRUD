<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${ pageContext.request.contextPath }" />

					<button onclick='createBoardBtn(event)'>글쓰기</button>
					<button onclick='deleteBoardBtn(event)'>삭제</button>
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
									<td><input type="checkbox" name="chkBox" value="${boardList.boardSeq}"></td>
									<td>${boardList.boardSeq}</td>
									<td>${boardList.writer}</td>
									<td><a href="${path}/read/${boardList.boardSeq}">${boardList.title}</a></td> <!-- title -->
									<td><fmt:formatDate value="${boardList.regDate}" pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${boardList.uptDate}" pattern="yyyy-MM-dd" /></td>
									<td>${boardList.viewCnt}</td>
								</tr>
							</c:forEach>
						</tbody>
						
						<tfoot>
							<tr>
					            <td colspan="7">
					                <!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
					                <c:if test="${pager.curPage > 1}">
					                    <a href="javascript:list('1')">[처음]</a>
					                </c:if>
					                
					                <!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
					                <c:if test="${pager.curBlock > 1}">
					                    <a href="javascript:list('${pager.prevPage}')">[이전]</a>
					                </c:if>
					                
					                <!-- **하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
					                <c:forEach var="num" begin="${pager.blockBegin}" end="${pager.blockEnd}">
					                    <!-- **현재페이지이면 하이퍼링크 제거 -->
					                    <c:choose>
					                        <c:when test="${num == pager.curPage}">
					                            <span style="color: red">${num}</span>&nbsp;
					                        </c:when>
					                        <c:otherwise>
					                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
					                        </c:otherwise>
					                    </c:choose>
					                </c:forEach>
					                
					                <!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
					                <c:if test="${pager.curBlock <= pager.totBlock}">
					                    <a href="javascript:list('${pager.nextPage}')">[다음]</a>
					                </c:if>
					                
					                <!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
					                <c:if test="${pager.curPage < pager.totPage}">
					                    <a href="javascript:list('${pager.totPage}')">[끝]</a>
					                </c:if>
					            </td>
					        </tr>
						</tfoot>
						
					</table>