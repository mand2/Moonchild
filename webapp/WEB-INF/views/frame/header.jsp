<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<header>
		<div class="container-fluid position-relative no-side-padding">

			<a href="<c:url value='/'/>" class="logo"><img src="<c:url value='/images/logo.png'/>" alt="Logo Image"></a>

			<div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>

            <ul class="main-menu visible-on-click" id="main-menu">
					<li><a href="<c:url value='/'/>">Home</a></li>
				<c:if test="${loginInfo eq null}">
					<li><a href="<c:url value='/login/form'/>">Login</a></li>
				</c:if>	
					<li><a href="<c:url value='/join/form'/>">Join</a></li>
					<li><a href="#">Categories</a></li>
				<c:if test="${loginInfo ne null}">
					<li><a href="#">MyPage</a></li>
					<li><a href="#">Members</a></li>
					<li><a href="#">Logout</a></li>
				</c:if>
			</ul><!-- main-menu -->

			<div class="src-area">
				<form>
					<button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
					<input class="src-input" type="text" placeholder="Type of search">
				</form>
			</div>

		</div><!-- container -->
	</header>