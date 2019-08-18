<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%
	LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
%> --%>

<!-- EL로 쓴다면 session.getAttribute를
${loginInfo}
 -->
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
        <div id="regForm">
            
            <div class="warning">
                <h4>아이디</h4>
                <span class="inputbox"> ${loginInfo.id} </span>
                <h5></h5>
            </div>
            
            
            <div class="warning">
                <h4>이름</h4>
                <span class="inputbox">${loginInfo.name} </span>
                <h5></h5>
            </div>

            <div class="warning">
                <h4>사진</h4>
                <img src = "<c:url value='/uploadfile/userphoto/${loginInfo.photo}' />" alt="회원사진" style="width: 100px;">
                <h5></h5>
            </div>
            
            <div class="warning">
                <h4>가입시각</h4>
                <span class="inputbox"><fmt:formatDate value="${loginInfo.regDate}" pattern ="yyyy.MM.dd H:mm a" /></span>
                <h5></h5>
            </div>
            
            <div class="btn_click">
            	<span><a href="<c:url value='/member/edit?id=${loginInfo.id}' />">수정</a></span>
            </div>
        </div>
    </div>

<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->
</body>

</html>