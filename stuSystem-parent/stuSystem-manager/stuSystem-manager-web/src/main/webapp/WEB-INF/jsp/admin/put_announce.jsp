<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公告发布</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/public.js'/>"></script>
    <script type="text/javascript">
        function yulan()
        {
            var t=new Date();
            var year=t.getFullYear();
            var month=t.getMonth()+1;
            var day=t.getDate();
            var hour=t.getHours();
            var minute=t.getMinutes();
            var second=t.getSeconds();
            var showTime=year+"-"+month+"-"+day+"  "+hour+":"+minute+":"+second;
            console.log(showTime);
            var biaoti=document.getElementById("biaoti").value;
            var zhengwen=document.getElementById("zhengwen").value;
            var identity = document.getElementById("identity").value;
            var s1,s2,s3,s4;
            localStorage.setItem("s1",biaoti);
            localStorage.setItem("s2",zhengwen);
            localStorage.setItem("s3",showTime);
            localStorage.setItem("s4",identity);
            window.open("<c:url value='/admin/annoPreUI.action'/>");
        }
        function sumbit()
        {
            var biaoti=document.getElementById("biaoti").value;
            var zhengwen=document.getElementById("zhengwen").value;
            if(biaoti.length==0)
            {
                alert("请填写标题!");
                return false;
            }
            else if(zhengwen.length==0)
            {
                alert("请输入正文!");
                return false;
            }
            return true;
        }
    </script>
    <style>

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
        .import {
            background-color:#6495ED;
            font-weight:bold;
            text-align:center;
            border: none;
            color: white;
            padding: 7px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-left:5px;
        }
        .input01 {
            border: 1px solid #CDC28D;
            height: 30px;
            width: 495px;
            padding-top: 4px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
            padding-left: 10px;
            margin-left:5px;
        }
        .input02 {
            border: 1px solid #CDC28D;
            height: 425px;
            width: 720px;
            padding-top: 4px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
            padding-left: 10px;
            margin-left:5px;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div style="text-align:center">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="<c:url value='/img/fabu.png'/>" class="naviPic">
            <span style="font-weight:bold;font-size:23px">发布公告</span>
            <span style="font-weight:bold;font-size:20px;float:right">附件上传↓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
            <hr style="height:1px;">
        </div>
        <form action="<c:url value='/admin/putAnnounce.action'/>" name="form1" method="post" enctype="multipart/form-data" onsubmit="return sumbit()">
            <input type="hidden" name="annouAuth"  value="${(User.user).getTeachId()}">
            <input type="hidden" name="annouAuthName" id = "identity" value="${(User.user).getTeachName()}">
            <input type="text" name="annouName" id="biaoti" class="input01" placeholder="请填写标题">
            <input type="file" name="otherfile" id="excel" accept=".xls,.xlsx,.doc,.docx">
            <br><br>
            <textarea name="annouContent" id="zhengwen" class="input02" placeholder="请输入正文"></textarea>
            <br><br>
            <input type="submit" class="import" value="发 布">
            <input type="button" class="import" value="预览发布界面" onclick="yulan()"><br/>
        </form>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>