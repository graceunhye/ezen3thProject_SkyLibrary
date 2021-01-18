<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공지사항 상세보기</title>
<link rel="stylesheet" href="/css/mCommon.css" type="text/css"/>
<link rel="stylesheet" href="/css/mNotice/mnoticeView.css" type="text/css"/>

<script>
	function deleteFn(){
		if(confirm("삭제하시겠습니까?")){
			location.href = "noticeDelete?no=${list.noticeNo}";
		}
	}
</script>
</head>
<body style="margin: 0px;">
	<div class="wrap">
		<header>
			<div class="header">
				<div class="title_box"><span class="head_title">관리자 페이지 </span><span class="name">하늘도서관</span></div>
			</div>
		</header>
		<section>
			<jsp:include page="../include/nav.jsp" />
			<div class="section">
				<div class="user_info">
					<span class="info_title"><span class="point">*</span>공지사항 </span>
					<br>
					<br>
					<table border="1" style="border-collapse:collapse" width="1000">
						<tr>
							<td align="center" width="7%" class="view_td">글번호</td>
							<td align="center" width="10%">${noticeView.noticeNo}</td>
							<td align="center" width="7%" class="view_td">작성자</td>
							<td align="center" width="10%">${noticeView.userID}</td>
						</tr>		
						<tr class="mid_tr">
							<td align="center" width="7%" class="view_td">작성일</td>
							<td align="center" width="10%">${noticeView.noticeDate}</td>
							<td align="center" width="7%" class="view_td">조회수</td>
							<td align="center" width="10%">${noticeView.noticeHit}</td>
						</tr>
						<tr>
							<td align="center" height="40px" class="view_td" width="7%">제목</td>
							<td colspan="3"><c:out value="${noticeView.noticeTitle}"/></td>	
						</tr>
						<tr>
							<td align="center" height="400px" class="view_td" width="7%">내용</td>
							<td colspan="3">
							<c:choose>  
							    <c:when test="${noticeView.noticeFile != null}">  
									<img src="noticeFileDown?type=img&nfile=${noticeView.noticeFile}">
									<c:out value="${noticeView.noticeBody}"/>
							    </c:when>  
							    <c:otherwise>  
							        <c:out value="${noticeView.noticeBody}"/>
							    </c:otherwise>   
							</c:choose> 	
							</td>	
						</tr>
						<tr>
							<td align="center" height="50px" class="view_td" width="7%">첨부파일</td>
							<td colspan="3">
								<c:choose>  
								    <c:when test="${noticeView.noticeFile != null}">  
										<a href="noticeFileDown.jsp?nfile=${noticeView.noticeFile}"><c:out value="${noticeView.noticeFile}"/></a>
								    </c:when>  
								    <c:otherwise>  
								    </c:otherwise>   
								</c:choose>							
							</td>
						</tr>
					</table>
					<br>
					<br>
					<div class="view_btn">
						<input type="button" value="수정" id="nmodify" class="optionBox_btn_free" onclick="location.href='noticeModify?no=${list.noticeNo}'">
						<input type="button" value="삭제" id="ndelete"  class="optionBox_btn_free" onclick="deleteFn()">
						<input type="button" value="목록"  class="optionBox_btn_free" onclick="location.href='mNotice'">
					</div>	
				</div>
			</div>
		</section>
	</div>
</body>
</html>