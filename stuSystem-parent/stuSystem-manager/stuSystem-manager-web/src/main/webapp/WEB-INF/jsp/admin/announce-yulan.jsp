<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公告预览</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            var biaoti=localStorage.getItem("s1");
            var zhengwen=localStorage.getItem("s2");
            var showTime=localStorage.getItem("s3");
            var identity=localStorage.getItem("s4");
            document.getElementById("biaoti").innerText=biaoti;
            document.getElementById("zhengwen").innerText=zhengwen;
            document.getElementById("fbsj").innerText=showTime;
            document.getElementById("fbr").innerText=identity;
        })
</script>

    <style>
        #main{
            height:580px;
            background: #FFFFFF;
            border:2px solid #999999;
            right:0;
            top:180px;
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

    </style>
</head>
<body onload="load()">
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div style="text-align:center">
            <span style="font-weight:bold;font-size:23px" id="biaoti">标题</span>
            <hr style="height:1px;">
        </div>
        <div style="text-align:center">
            发布人: <span id="fbr"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间:<span id="fbsj"></span>
        </div>
        <div>
            <span style="font-size:15px;margin-left:77px;" id="zhengwen">正文</span><br/>
            <a href="#" style="margin-left:77px;">公告附件</a>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>