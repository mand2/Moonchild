<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 LIST::YONNY's JOURNEY</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link href="<c:url value='../css/default.css' />" rel="stylesheet" type="text/css">

<style>
	.thumnail {
		height: 30px;
		margin: 5px;
	}
	
	table {
		width: 850px;
		margin: 0 auto;
		border: 0;
		border-collapse: collapse;
	}
	
	table tr {
		height : 40px;
	}
	
	table td{
		padding: 3px;
		border: 1px solid #ccc;
		text-align: center;
	}
	
	table tr:first-child > td{
		background-color: #ccc;
		color: white;
		font-weight: bold;
		font-size: 1.15em;
	}
	
	td>span {
		margin: 0 auto;
		display: inline-block;
	}
	
	#pageBox{
		overflow: hidden;
		margin: 20px auto;
		width : 300px;
	}
	
	#pageBox div {
		float: left;
		width: 30px;
		line-height: 30px;
		text-align: center;
		box-sizing: border-box;
		border: 1px solid #ccc;
		margin: 0 5px;
	}
	
	.searchBox {
		width: 300px;
		padding: 15px;
		margin: 30px auto;
		border: 1px solid #ccc;
	}
	
	#pageBox div a {
		display: inline-block;
	}
	
</style>
</head>
<body>
<!-- nav 시작 -->
<%@ include file="../frame/nav.jsp" %>
<!-- nav 끝-->

<!-- header 시작 -->
<%@ include file="../frame/header.jsp" %>
<!-- header 끝-->

<!-- contents 시작 -->
	<div id="contents">
	
	<div class="searchBox">
		<form>
		<h3>검색</h3>
		
		<select name="type" id="type">
			<option value="1">name</option>
			<option value="2">ID</option>
			<option value="3">name+ID</option>
		</select>
		
		<input type="text" name="keyword" id="keyword" placeholder="검색어입력">
		<input type="submit" value="검색">
		</form>
	</div>
		<table>
			<tr>
				<td>순번</td>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>이미지</td>
				<td>등록일</td>
				<td>관리</td>
			</tr>
			<!-- 리스트 반복 시작 -->
			
			<c:forEach items="${list.list}" var="member" varStatus="stat">
					<tr>
						<td>${list.no - stat.index }<%-- ${member.idx} / ${stat.index} / ${stat.count} / ${list.totalCnt } --%>
						</td>
						<td>${member.id}</td>
						<td>${member.pw}</td>
						<td>${member.name}</td>
						<td>
							<c:if test="${member.photo ne null}">
								<img alt="회원사진" src = "<c:url value='/uploadfile/userphoto/${member.photo}' />" style="height: 30px; border-radius: 30px;">
							</c:if>
							
							<c:if test="${member.photo eq 'noimg.jpg'}">
								<img alt="회원사진" src = "<c:url value='/images/noimg.jpg' />" style="height: 30px; border-radius: 30px;">
							</c:if>
						</td>
						<td>
							<fmt:formatDate value="${member.regDate}" pattern="yyyy.MM.dd H:mm a" />
						</td>	
						<td>
							<span>
								<a href="<c:url value='/member/edit?id=${member.id}' />">수정</a> | 
								<a onclick="delMember('${member.id}')">삭제</a>
								<%-- <a href="<c:url value='/member/delete?id=${member.id}' />" class="delete">삭제</a> --%>
								<!-- javascript에 ''써주는 이유: var id를 문자열로 처리하기 위해. -->
							</span>
						</td>
						
					</tr>
			</c:forEach>
			<!-- 리스트 반복 끝 -->
			
		</table>
		
			<c:if test="${list.totalCnt > 0}">
				<div id="pageBox">
					<c:forEach begin="1" end="${list.pageTotalCnt}" var="page">
						<div>
							<a href="list?pno=${page}&type=${param.type}&keyword=${param.keyword}">
								${page}  
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
	</div>
<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->

</body>
</html>

<script>
	function delMember(id) {
		
		if(confirm("삭제하시나요?")){
			location.href = "delete/"+id;	
		}
			
	}

</script>
