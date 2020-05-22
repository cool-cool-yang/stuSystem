<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.stuSystem.manager.pojo.Teacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>超级管理员页面</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <!--<script type="text/javascript" src="#">-->
    <style>
        *{
            padding:0;
            margin:0;
        }
        #p{
            color:#000000;
        }
        #container{
            width:1000px;
            margin:0 auto;
            border:1px solid #A7F0FB;
            position:relative;
        }
        #header{
            height:30px;
            background: #3B3B3B;
            color:white;
            text-align:justify;
        }
        #left{
            width:250px;
            height:520px;
            background: #FFFFFF;
            border:2px solid #999999;
        }
        #tubiao{
            float:contour;
            height:39px;
            width:37px;
            background:url(/stuSystem/img/admin.png) no-repeat;
        }

        #right{
            width:740px;
            height:520px;
            background: #FFFFFF;
            border:2px solid #999999;
            position:absolute;
            right:0;
            top:0px;
        }
        #footer{
            height:80px;
            background: #3B3B3B;
        }
        .naviHead2{
            font-weight: bold;
            color: #000000 !important;
            letter-spacing: 2px;
            border-bottom: 1px solid #D0D0D0;
            padding-bottom: 3px;
            text-indent: 5px;
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
        <h4>入职时间：${(User.user).teachEnsch}</h4><br><br>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <img src="/stuSystem/img/admin.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:25px">管理员服务</span><hr/>
        </div>
        <div style="magin:3px">
            <img src="/stuSystem/img/import.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">相关数据导入服务</span><hr/>
        </div>
        <div style="margin:5px;">
            <div class="navi">
                <img src="/stuSystem/img/import2.png" class="naviPic">
                <a href="<c:url value='/admin/goStuImportUI.action'/>" id="two">单项导入学生信息</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="/stuSystem/img/import2.png" class="naviPic">
                <a href="<c:url value='/admin/goTeaImporUi.action'/>" id="two">单项导入老师信息</a><br>
            </div>
            <div>
                <div class="navi" >
                    <img src="/stuSystem/img/import2.png" class="naviPic">
                    <a href="<c:url value='/admin/goStuImportUI.action'/>" id="two">批量导入学生信息</a>
                </div>
                <div class="navi" style="float:right;margin-right:100px;">
                    <img src="/stuSystem/img/import2.png" class="naviPic">
                    <a href="<c:url value='/admin/goTeaImporUi.action'/>" id="two">批量导入老师信息</a>
                </div>
            </div>
            <div style="magin:3px"><br><br>
                <img src="/stuSystem/img/search.png" style="vertical-align:middle;">
                <span style="font-weight:bold;font-size:15px">查询服务</span><hr style="height:1px;">
            </div>
            <div class="navi">
                <img src="/stuSystem/img/search2.png" class="naviPic">
                <a href="<c:url value='/admin/goStuSearchUI.action'/>" id="two">查询学生</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="/stuSystem/img/search2.png" class="naviPic">
                <a href="<c:url value='/admin/goTeaSearchUI.action'/>" id="two">查询老师</a>
            </div>
        </div>
        <div style="magin:3px"><br><br><br>
            <img src="/stuSystem/img/check.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">审核申请</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="/stuSystem/img/check.png" class="naviPic">
            <a href="#审核相关申请" id="two">审核相关申请</a>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>