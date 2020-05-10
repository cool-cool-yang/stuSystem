<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
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
    top:150px;
    }
    #footer{
    height:80px;
    background: #3B3B3B;
    }

    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div id="container">

    <div id="logo" style=background:url(/stuSystem/img/background.png)></div>
    <div id="left">公告</div>
    <div id="right">额外公用功能</div>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>