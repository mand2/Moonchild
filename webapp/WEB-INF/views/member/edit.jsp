<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link href="<c:url value='../css/default.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='../css/regform.css'/>" rel="stylesheet" type="text/css">
<style>
	
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

	<div class = "wrap">
        <h2>회원 정보</h2>
        
        <form method="post" enctype="multipart/form-data" >
	        <div id="regForm">
	        
	        <input type="hidden" name="idx" value="${member.idx}">

	            <div class="warning">
	                <label for="id"><h4>아이디</h4></label>
	                <span class="inputbox">${member.id}</span>
	                <h5></h5>
	            </div>
	            
	            
	            <div class="warning">
	                <label for="name"><h4>이름</h4></label>
	                <span class="inputbox"><input id="name" name="name" class="input" placeholder="${member.name}"></span>
	                <h5></h5>
	            </div>
	            
	            <div class="warning">
	                <label for="pw"><h4>비밀번호</h4></label>
	                <span class="inputbox"><input id="pw" name="pw" class="input" type="password"></span>
	                <h5></h5>
	            </div>
	
	            <div class="warning">
	                <label for="photo"><h4>사진</h4></label>
	                <img src = "<c:url value='/uploadfile/userphoto/${member.photo}' />" alt="회원사진" style="width: 100px;">
	                <span class="inputbox"><input type="file" name = "photo" id="photo"></span>
	                <input type="hidden" name="oldFile" value="${member.photo}">
	                <h5></h5>
	            </div>
	            
	            <div class="warning">
	                <h4>가입시각</h4>
	                <span class="inputbox"><fmt:formatDate value="${member.regDate}" pattern ="yyyy.MM.dd H:mm a" /></span>
	                <h5></h5>
	            </div>
	            <input type="submit" value="수정">
	        </div>
        </form>
	            <div class="btn_click">
	            	<span><a href="<c:url value='/member/delete?id=${member.id}' />">탈퇴</a></span>
	            </div>
    </div>

<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->
</body>

</html>