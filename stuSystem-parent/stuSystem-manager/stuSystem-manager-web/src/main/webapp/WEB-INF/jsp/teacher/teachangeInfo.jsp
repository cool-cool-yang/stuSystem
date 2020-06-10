<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>信息修改</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <script type="text/javascript">
        function checkOldPwdEq(){
            var oldPwd = "${(User.user).getTeachPwd()}";
            //alert("原来的老密码："+oldPwd);

            var inputOLd = $("#pwd1").val();
            //alert("我输入的老密码："+inputOLd);
            if(oldPwd!=inputOLd){
                return false;
            }else{
                return true;
            }
        }
        function checklxfs(str)
        {
            var reg2= /^\d{11}$/;
            if(!reg2.test(str))
            {
                return false;
            }
            return true;
        }
        function checkemail(str){
            var sReg = /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;
            if ( !sReg.test(str) )
            {
                return false;
            }
            return true;
        }
        function sumbit()
        {
            document.getElementById("warn1").style.display="none";
            document.getElementById("warn2").style.display="none";
            document.getElementById("warn3").style.display="none";
            document.getElementById("warn4").style.display="none";
            var lxfs=document.getElementById("lxfs").value;
            var email=document.getElementById("email").value;
            var pwd1=document.getElementById("pwd1").value;
            var pwd2=document.getElementById("pwd2").value;
            if(!checklxfs(lxfs))//默认显示
            {
                alert("联系方式应为11位数字！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
                return false;
            }
            else if(!checkemail(email))//默认显示
            {
                alert("邮箱格式不正确！请重新输入。");
                document.getElementById("warn2").style.display="inline-block";
                return false;
            }
            else if(pwd1.length==0)//应再加个else if不等于原密码的报错
            {
                alert("原密码不能为空！请重新输入.");
                document.getElementById("warn3").style.display="inline-block";
                return false;
            }else if(!checkOldPwdEq()){
                alert("原密码输入错误，请重新输入");
                document.getElementById("warn3").style.display="inline-block";
                return false;
            }
            else if(pwd2.length==0)
            {
                alert("新密码不能为空！请重新输入.");
                document.getElementById("warn4").style.display="inline-block";
                return false;
            }
            else if(pwd1==pwd2)
            {
                alert("新密码与旧密码相同！请重新输入.");
                document.getElementById("warn3").style.display="inline-block";
                document.getElementById("warn4").style.display="inline-block";
                return false;
            }
            return true;
        }
    </script>
    <style>
        #container{
            width:777px;
            margin:0 auto;
            border:1px solid #A7F0FB;
            position:relative;
        }
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
        .import {
            background-color:#6495ED;
            font-weight:bold;
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
        table {
            width: 90%;
            background: #ccc;
            margin: 10px auto;
            border-collapse: collapse;/*border-collapse:collapse合并内外边距(去除表格单元格默认的2个像素内外边距*/
        }
        th,td {
            height: 25px;
            line-height: 25px;
            text-align: center;
            border: 1px solid #ccc;
        }
        th {
            background: #eee;
            font-weight: normal;
        }
        tr {
            background: #fff;
        }
        tr:hover {
            background: #cc0;
        }
        td a {
            color: #06f;
            text-decoration: none;
        }
        td a:hover {
            color: #06f;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div style="text-align:center">
            <img src="<c:url value='/img/fabu.png'/>" class="naviPic">
            <span style="font-weight:bold;font-size:23px">信息修改</span>
            <hr style="height:1px;">
        </div>
        <form action="<c:url value='/teacher/ModifyTeaInfo.action'/>" name="form" method="post" onsubmit="return sumbit()">
            <input type="hidden" name="teachId" value="${(User.user).getTeachId()}">
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            联系方式：  <input type="text" name="teachMobile" id="lxfs" class="input01" value="${(User.user).getTeachMobile()}"><span style="color:red">11位数字</span><br/><br/>
            <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
            电子邮箱：  <input type="text" name="teachEmail" id="email" class="input01" value="${(User.user).getTeachEmail()}"><span style="color:red">xx+@xx.+xx</span><br/><br/>
            <span id="warn3" style="color:red;font-weight:bold;display:none;">➤</span>
            当前密码：   <input type="password" name="pwd1" id="pwd1" class="input01"><br><br>
            <span id="warn4" style="color:red;font-weight:bold;display:none;">➤</span>
            新的密码：   <input type="password" name="teachPwd" id="pwd2" class="input01">
            <div style="text-align:center;width: 620px">
                <input type="submit" class="import" value="提交"><br/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>