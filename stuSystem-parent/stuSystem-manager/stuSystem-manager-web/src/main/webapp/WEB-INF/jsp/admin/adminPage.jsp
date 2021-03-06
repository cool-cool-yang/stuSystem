<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.stuSystem.manager.pojo.Teacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>超级管理员页面</title>
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
            height:600px;
            background: #FFFFFF;
            border:2px solid #999999;
        }
        #right{
            width:740px;
            height:600px;
            background: #FFFFFF;
            border:2px solid #999999;
            position:absolute;
            right:0;
            top:0px;
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
        .infoPanel{
            background: white;border: 1px solid #C0C0C0;padding: 5px;
            margin-top:10px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="left">
        <!--头像区域--><br><br><br><br><br><br><br><br><br><br><hr/><br><br>
        <h4>工号：${(User.user).getTeachId()}</h4><br><br>
        <h4>姓号：${(User.user).getTeachName()}</h4><br><br>
        <h4>联系邮箱：${(User.user).getTeachEmail()}</h4><br><br>
        <h4>入职时间：<fmt:formatDate value="${(User.user).teachEnsch}"/></h4><br><br>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <img src="<c:url value='/img/admin.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:25px">管理员服务</span><hr/>
        </div>
        <div style="magin:3px">
            <img src="<c:url value='/img/import.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">相关数据导入服务</span><hr/>
        </div>
        <div style="margin:5px;">
            <div class="navi">
                <img src="<c:url value='/img/import2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goStuImportUI.action'/>" id="two">导入学生信息</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="<c:url value='/img/import2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goTeaImporUi.action'/>" id="two">导入老师信息</a><br>
            </div>
            <div style="magin:3px"><br>
                <img src="<c:url value='/img/search.png'/>" style="vertical-align:middle;">
                <span style="font-weight:bold;font-size:15px">查询服务</span><hr style="height:1px;">
            </div>
            <div class="navi">
                <img src="<c:url value='/img/search2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goStuSearchUI.action'/>" id="two">查询学生</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="<c:url value='/img/search2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goTeaSearchUI.action'/>" id="two">查询老师</a>
            </div>
        </div>
        <div style="magin:3px"><br>
            <img src="<c:url value='/img/check.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">审核申请</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="<c:url value='/img/check.png'/>" class="naviPic">
            <a href="#审核相关申请" id="two">审核相关申请</a>
        </div>
        <div style="magin:3px"><br><br>
            <img src="<c:url value='/img/fabu.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">发布公告</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="<c:url value='/img/fabu2.png'/>" class="naviPic">
            <a href="<c:url value='/admin/goApUI.action'/>" id="two">发布主页公告</a>
        </div>
        <div style="magin:3px"><br><br>
            <img src="<c:url value='/img/kc.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">课程管理</span><hr/>
        </div>
        <div style="margin:5px;">
            <div class="navi">
                <img src="<c:url value='/img/kc2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goNewCourseUI.action'/>" id="two">添加新课程</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="<c:url value='/img/kc2.png'/>" class="naviPic">
                <a href="<c:url value='/admin/goNewClassUI.action'/>" id="two">开设课程</a><br>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>