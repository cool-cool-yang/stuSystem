<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>首页</title>

    <!--<link rel="stylesheet" type="text/css" href="#">-->
    <!--<script type="text/javascript" src="#">-->
    <script type="text/javascript" src="/stuSystem/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <%--涉及到获取session--%>
    <script type="text/javascript">
        window.onload= function loginOrExit() {
            var user = '<%= session.getAttribute("user")%>';
            alert(user)
            if (!user && typeof user != "undefined" && user != 0) {
                alert("已经登录");
                $("#logId").css("display","none");
                $("#extId").css("display","");
            } else {
                alert("还未登录");
                $("#extId").css("display","none");
                $("#logId").css("display","");
            }
        }
    </script>
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
    height:32px;
    background: #3B3B3B;
    color:white;
    text-align:justify;
    }
    #header #headerRight{
        width: 350px;
        height: 100%;
        text-align:justify;

    }
    #headerRight #myIdentityId{
        width: 90px;
        margin-right: 1px;
        padding: 2px;
    }
    #headerRight #showolog1{
        width: 120px;
        padding:5px;
        margin-left: 1px;
        text-align: left;
       /* 内容溢出省略*/
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        display: inline-block;
    }
    #headerRight .logOrExt{
        width: 80px;
        margin-left: 2px;
        padding-left: 0px;
    }
    #logo{
    height:150px;
    background:#FFFFFF;
    }
    #left{
    width:700px;
    height:600px;
    background: #FFFFFF;
    border:2px solid #999999;
    }

    #right{
    width:290px;
    height:600px;
    background: #FFFFFF;
    border:2px solid #999999;
    position:absolute;
    right:0;
    top:180px;
    }
    #footer{
    height:80px;
    background: #3B3B3B;
    }

    a:link,a:visited{
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
    a:hover,a:active{
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

    </style>
</head>

<%--使用js调整登录按钮--%>

<body>
<div id="container">
    <div id="header">
        <div id="headerLeft" style="float:left;">
            <a href="#首 页" style="float:left;">首 页</a>
            <a href="#聊天交流" style="float:left;">聊天交流</a>
            <a href="#资源下载" style="float:left;">资源下载</a>
            <a href="#个人服务" style="float:left;">个人服务</a>
        </div>
        <div id="headerRight" style="float:right;">
            <h3 style="float:left;" class="MyIdentity" id="myIdentityId" value="当前身份" >当前身份:</h3>
            <h4  style="float:left"  id="showolog1" href="">游客</h4>
            <a href="<c:url value='/user/goLoginUI.action'/>" style="float:right;" class="logOrExt" id="logId">[登录]</a>
            <a href="javascript:void(0);"  style="float:right;display: none" class="logOrExt" id="extId">[退出]</a>
        </div>

    </div>
    <div id="logo" style=background:url(/stuSystem/img/background.png)></div>
    <div id="left">公告</div>
    <div id="right">额外公用功能</div>
    <div id="footer">
        <br>
        <div style="color:#FFFFFF"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Copyright © 2020 - 重庆邮电大学 计算机科学与技术学院 (+86)13340262405</h5></div>
        <br>
        <div style="color:#FFFFFF;float:right;"><h5>知行苑7舍408设计制作 学生管理系统Version 1.0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5></div>
     </div>
</div>
</body>
</html>