<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- login / form.jsp 로그인 -->        

<%request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login::moonChild</title>
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
		<h2>로그인</h2>
		<form id="login" method="post">
			<div id="regForm">
					<!--input 의 width값을 길게 주면 아래 행으로 넘어가니까 효과 있음!-->
					<!--.inputbox는 박스 클릭시 색상 주기, .input은 width설정-->
	
					<div class="warning">
						<label for="id"><h4>아이디</h4></label> 
						<span class="inputbox">
							<input id="id" name="id" class="input" placeholder="id를 입력해주세요" type="text">
						</span>
						<h5></h5>
					</div>
	
					<div class="warning">
						<label for="pw"><h4>비밀번호</h4></label> 
						<span class="inputbox">
							<input id="pw" name="pw" class="input" placeholder="pw를 입력해주세요" type="password">
						</span>
						<h5></h5>
					</div>
					<!-- id pw 저장여부 -->
					<h4>
						<input type="checkbox"> 아이디 저장
					</h4>
					<input type="submit" value="로그인">
					<div class="btn_click">
	            	<span><a href="<c:url value='/join/form'/>">회원가입</a></span>
           			</div>
					
				</div>
			</form>
		
	</div>
<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->

</body>

</html>