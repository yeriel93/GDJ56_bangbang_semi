<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List,com.user.model.vo.User,com.broker.model.vo.Broker,com.account.model.vo.AlertListC" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
	Broker loginBroker = (Broker)session.getAttribute("loginBroker");
%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>방방(방 구해줘~방)</title>

    <!-- 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
	
    <!-- css -->
    <link href="<%=request.getContextPath() %>/css/common/headerStyle.css" type="text/css" rel="stylesheet">

	<!-- jQuery -->
	<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
	
	
</head>
<body>
    <!-- 상단바 고정메뉴 -->
    <header>
        <div id="logoContainer">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="" width="140px" height="100px">
        </div>
        <div class="menu">
            <p><a href="<%=request.getContextPath()%>/searchAddress.bb">지도</a></p>
        </div>
        <div class="menu">
            <p><a href="<%=request.getContextPath()%>/account.bb">마이페이지</a></p>
        </div>
        <div class="menu">
            <p><a href="<%=request.getContextPath()%>/property/insertProperty.bb" target="_blank">방내놓기</a></p>
        </div>
        <div class="menu">
            <p><a href="<%=request.getContextPath()%>/user/enrollBroker.bb">중개사 등록</a></p>
        </div>
        <%if(loginUser == null){ %>
	        <div class="buttonContainer" id="loginBtn">
	            <button><p><a href="<%=request.getContextPath()%>/user/login.bb" style="color:white">로그인 | 회원가입</a></p></button>
	        </div>
        <%} else { %>
       		<div class="buttonContainer" id="logoutBtn">
            	<div>
           			<div id="loginUser">
           				<%if(!loginUser.getId().equals("admin")){ %>
           					<div style="width=35px;height=35px;">
           						<img id="alertImage" src="<%=request.getContextPath()%>/images/noalert.png" width="35px" height="35px" style="">
           					</div>
           				<%} %>
           				<div style="margin:3px 20px 0px 5px;">
           					<%=loginUser.getName()%>님
           				</div>
           			</div>
            		<button><p><a href="<%=request.getContextPath()%>/user/logout.bb" style="color:white">로그아웃</a></p></button>
            		<%if(loginUser.getId().equals("admin")) {%>
       			     	<br>
       			     	<div id="adminButtonContainer">
       			     		<button><p><a href="<%=request.getContextPath()%>/admin/adminDashBoard.bb" style="color:white">관리자페이지</a></p></button>
           				</div>
           			<%} %>
        		</div>
        	</div>
        <%} %>
    </header>
    <script>
    	$("div#logoContainer>img").click(e=>{
    		location.assign("<%=request.getContextPath()%>/");
    	});
    	$("img#alertImage").click(e=>{
    		location.assign("<%=request.getContextPath()%>/account/inquiry.bb");
    	});
    </script>
    