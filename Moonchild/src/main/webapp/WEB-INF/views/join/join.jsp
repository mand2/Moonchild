<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 chk</title>
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
<c:if test="${result>0}">
	<div class = "wrap">
        <h2>회원가입</h2>
        <div id="regForm">
            
            <div class="warning">
                <label for="id"><h4>아이디</h4></label>
                <span class="inputbox">${member.id} </span>
                <input type="checkbox" id="idcheck">
                <h5></h5>
            </div>
            
            <div class="warning">
                <label for="pw"><h4>비밀번호</h4></label>
                <span class="inputbox">${member.pw} </span>
                <h5></h5>
            </div>
            
            <div class="warning">
                <label for="name"><h4>이름</h4></label>
                <span class="inputbox">${member.name} </span>
                <h5></h5>
            </div>

            <div class="warning">
                <label for="name"><h4>사진</h4></label>
                <span class="inputbox">${member.photo} </span>
                <h5></h5>
            </div>
            
            <div class="warning">
                <label for="name"><h4>가입시각</h4></label>
                <span class="inputbox">${member.regDate} </span>
                <h5></h5>
            </div>
            
            <h1>가입을 축하드립니다.</h1>
            <div class="btn_click">
            	<span><a href="<c:url value='/'/>">홈으로</a></span>
            	<span><a href="<c:url value='/login/form'/>">로그인</a></span>
            </div>
        </div>
    </div>
</c:if>


<c:if test="${!(result>0)}">
		<script>
			alert('회원가입이 정상적으로 완료되지 않았습니다.');
		</script>
        <p><a href = "/home">홈으로</a></p>
</c:if>
	
<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->

</body>

</html>