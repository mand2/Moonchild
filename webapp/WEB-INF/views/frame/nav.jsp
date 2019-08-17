<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="nav">
		
		<ul>
			<li><a href="<c:url value='/'/>">홈</a></li>
			
			<c:if test="${loginInfo eq null}">
				<li><a href="<c:url value='/login/form'/>">로그인</a></li>
			</c:if>
			
			<li><a href="<c:url value='/join/form'/>">회원가입</a></li>
			
			<c:if test="${loginInfo ne null}">
				<li><a href="<c:url value='/member/mypage'/>">MyPage</a></li>
				<li><a href="<c:url value='/member/list'/>">member</a></li>
				<li><a href="#">board</a></li>
				<li><a href="<c:url value='/login/logout'/>">LOG OUT</a></li>
			</c:if>
		</ul>
		
</nav>
