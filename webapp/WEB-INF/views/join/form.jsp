<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!-- join / form.jsp 회원가입 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member REG FORM</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<!-- Font -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
	<!-- Stylesheets -->
	<link href="../css/common-css/bootstrap.css" rel="stylesheet">
	<link href="../css/common-css/ionicons.css" rel="stylesheet">
	<link href="../css/category/styles.css" rel="stylesheet">
	<link href="../css/category/responsive.css" rel="stylesheet">	
<link href="<c:url value='/css/regform.css'/>" rel="stylesheet" type="text/css">

<style>
	.color_red {
		color: red;
	}
	.color_blue {
		color:blue;
	}
</style>
</head>

<body>
<!-- header 시작 -->
<%@ include file="../frame/header.jsp" %>
<!-- header 끝-->

<!-- contents 시작 -->


<div class = "wrap">
        <h2>회원가입</h2>
        <form method="post" enctype="multipart/form-data" id="">
            <div id="regForm">
                <!--input 의 width값을 길게 주면 아래 행으로 넘어가니까 효과 있음!-->
                <!--.inputbox는 박스 클릭시 색상 주기, .input은 width설정-->
                
                <div class="warning">
                    <label for="id"><h4>아이디</h4></label>
                    <span class="inputbox"><input id="id" name="id" class="input"></span>
                    <input type="checkbox" id="idcheck">
                    <h5></h5>
                </div>
                
                <div class="warning">
                    <label for="pw"><h4>비밀번호</h4></label>
                    <span class="inputbox"><input id="pw" name="pw" class="input" type="password"></span>
                    <h5></h5>
                </div>
                <div class="warning">
                    <label for="pw2"><h4>비밀번호확인</h4></label>
                    <span class="inputbox"><input id="pw2" name="pw2" class="input" type="password"></span>
                    <h5></h5>
                </div>
                
                <div class="warning">
                    <label for="name"><h4>이름</h4></label>
                    <span class="inputbox"><input id="name" name="name" class="input"></span>
                    <h5></h5>
                </div>

                <div class="warning">
                    <label for="photo"><h4>사진</h4></label>
                    <span class="inputbox"><input type="file" name = "photo" id="photo"></span>
                    <h5></h5>
                </div>
                <div class="warning">
                    <label for="y"><h4>전화번호</h4></label>
                    <!-- <span class="inputbox"><input id="phone" class="input"></span> -->
                    <div id="phoneBox">
	                    <span class="inputbox pnum"><input id="y" class="input" placeholder="010"></span>
	                    <span class="inputbox pnum"><input id="m" class="input" placeholder="1234"></span>
	                    <span class="inputbox pnum"><input id="d" class="input" placeholder="5678"></span>
                    </div>
                    <h5></h5>
                </div>
                
                <input type="submit" value="회원가입">
            </div>
        </form>
    </div>



<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->

<script>
	$(document).ready(function(){
		$('#id').focusout(function(){
			//ajax id전송 사용유무에 대한 결과 값 반환
			$.ajax({
				url: 'idcheck2', /* jsp를 사용하지 않고 바로 연결 */
				method: 'get',
				data: {
					id : $(this).val()
				},
				success: function(data){
						$(this).closest('h5').html('');
						$('#idcheck +h5').removeClass('color_red');
						$('#idcheck +h5').removeClass('color_blue');
						
					
					if(data == 'Y'){
						$('#idcheck').prop('checked', true);
						$('#idcheck +h5').html('사용가능한 멋진 아이디!');
						$('#idcheck +h5').addClass('color_blue');
					} else {
						$('#idcheck').prop('checked', false);
						$('#idcheck +h5').html('사용중인 아이디이거나 탈퇴한 회원입니다.');
						$('#idcheck +h5').addClass('color_red');
					}
				}
			});
		});
		
		$('form').submit(function() {
			if(!$('#idcheck').prop('checked')){
				alert('아이디체크');
				return false;
			}
		});
		
	});
</script>

</body>

</html>