<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>超级管理员页面</title>
    <!--<link rel="stylesheet" type="text/css" href="#">-->
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
            background:url(/stuSystem/img/admin.jpg) no-repeat;
        }

        #right{
            width:740px;
            height:520px;
            background: #FFFFFF;
            border:2px solid #999999;
            position:absolute;
            right:0;
            top:30px;
        }
        #footer{
            height:80px;
            background: #3B3B3B;
        }
        a#one:link,a#one:visited{
            color:#ffffff;
            text-transform: uppercase;
            background-color: #3B3B3B;
            font-weight:bold;
            display: block;
            width:120px;
            padding: 5px;
            text-align: center;
            text-decoration: none;
        }
        a#one:hover,a#one:active{
            color:#ffffff;
            text-transform: uppercase;
            background-color:red;
            font-weight:bold;
            display: block;
            width:120px;
            padding: 5px;
            text-align: center;
            text-decoration: none;
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
<div id="container">
    <div id="header">
        <div id="headerLeft" style="float:left;">
            <a href="#首 页" id="one" style="float:left;">首 页</a>
            <a href="#聊天交流" id="one" style="float:left;">聊天交流</a>
            <a href="#资源下载" id="one" style="float:left;">资源下载</a>
            <a href="#个人服务" id="one" style="float:left;">个人服务</a>
        </div>
        <div id="headerRight" style="float:right;">
            <h3 style="float:left;" class="MyIdentity" id="myIdentityId" value="当前身份" >当前身份:</h3>
            <h4  style="float:left"  id="showolog1" href="">游客</h4>
            <a href="javascript:void(0); "id="one" onclick="loginOrExit();" style="float:right;" class="logOrExt" id="logOrExtId">[登录]</a>
        </div>
    </div>
    <div id="left">
        <!--头像区域--><br><br><br><br><br><br><br><br><br><br><hr/><br><br>
        <h4>工号：</h4><br><br>
        <h4>姓号：</h4><br><br>
        <h4>联系邮箱：</h4><br><br>
        <h4>入职时间：</h4><br><br>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <img src="img/admin.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:25px">管理员服务</span><hr/>
        </div>
        <div style="magin:3px">
            <img src="img/import.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">相关数据导入服务</span><hr/>
        </div>
        <div style="margin:5px;">
            <div class="navi">
                <img src="img/import2.png" class="naviPic">
                <a href="#单项导入学生信息" id="two">单项导入学生信息</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="img/import2.png" class="naviPic">
                <a href="#单项导入老师信息" id="two">单项导入老师信息</a><br>
            </div>
            <div>
                <div class="navi" >
                    <img src="img/import2.png" class="naviPic">
                    <a href="#批量导入学生信息" id="two">批量导入学生信息</a>
                </div>
                <div class="navi" style="float:right;margin-right:100px;">
                    <img src="img/import2.png" class="naviPic">
                    <a href="#批量导入老师信息" id="two">批量导入老师信息</a>
                </div>
            </div>
            <div style="magin:3px"><br><br>
                <img src="img/search.png" style="vertical-align:middle;">
                <span style="font-weight:bold;font-size:15px">查询服务</span><hr style="height:1px;">
            </div>
            <div class="navi">
                <img src="img/search2.png" class="naviPic">
                <a href="#查询学生" id="two">查询学生</a>
            </div>
            <div class="navi" style="float:right;margin-right:100px;">
                <img src="img/search2.png" class="naviPic">
                <a href="#查询老师" id="two">查询老师</a>
            </div>
        </div>
        <div style="magin:3px"><br><br><br>
            <img src="img/check.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:15px">审核申请</span><hr style="height:1px;">
        </div>
        <div class="navi">
            <img src="img/check.png" class="naviPic">
            <a href="#审核相关申请" id="two">审核相关申请</a>
        </div>
    </div>
    <div id="footer">
        <br>
        <div style="color:#FFFFFF"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Copyright © 2020 - 重庆邮电大学 计算机科学与技术学院 (+86)13340262405</h5></div>
        <br>
        <div style="color:#FFFFFF;float:right;"><h5>知行苑7舍408设计制作 学生管理系统Version 1.0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5></div>
    </div>
</div>
</body>
</html>