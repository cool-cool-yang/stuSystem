<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生主界面</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <style>
        #container{
            width:1000px;
            margin:0 auto;
            border:1px solid #A7F0FB;
            position:relative;
        }
        #left{
            width:250px;
            height:500px;
            background: #FFFFFF;
            border:2px solid #999999;
        }
        #right{
            width:740px;
            height:500px;
            background: #FFFFFF;
            border:2px solid #999999;
            position:absolute;
            right:0;
            top:30px;
        }
        .navi{
            width: 180px;
            height: 40px;
            text-align: left;
            padding: 2px;
            display: inline-block;
            border: 0px dotted;
            letter-spacing: 2px;
            font-size:10px;
            color:#969696  ;
        }
        .navi .naviPic{
            vertical-align: middle;
            width: 40px;
            height: 40px;
        }
        .navi a#two:link{
            color: black;
            text-decoration: none;
        }
        .navi a#two:hover{
            color: white;
        }
        .navi:hover{
            background: #9ABC32;
            color: white;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="left">
        <!--头像区域--><br><br><br><br><br><br><br><br><br><br><hr/><br><br>
        <h4>学号：${(User.user).stuId}</h4><br><br>
        <h4>姓名：${(User.user).stuName}</h4><br><br>
        <h4>联系邮箱：${(User.user).stuEmail}</h4><br><br>
        <h4>入学时间：<fmt:formatDate value="${(User.user).stuEnsch}"/></h4><br><br>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <img src="<c:url value='/img/admin.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:25px">学生服务</span><hr/>
        </div>
        <div style="magin:3px">
            <img src="<c:url value='/img/search.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">个人数据查询</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="<c:url value='/img/search2.png'/>" class="naviPic">
            <a href="<c:url value='/student/gradeShow.action'/>" id="two">成绩查询</a>
        </div>
        <div class="navi" style="float:right;margin-right:100px;">
            <img src="<c:url value='/img/search2.png'/>" class="naviPic">
            <a href="<c:url value='/student/gradeAnalyse.action'/>" id="two">成绩分析</a><br>
        </div>
        <div style="magin:3px"><br><br>
            <img src="<c:url value='/img/fabu.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">个人信息修改</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="<c:url value='/img/fabu2.png'/>" class="naviPic">
            <a href="<c:url value='/student/goModifyInfoUI.action'/>" id="two">信息修改</a>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>