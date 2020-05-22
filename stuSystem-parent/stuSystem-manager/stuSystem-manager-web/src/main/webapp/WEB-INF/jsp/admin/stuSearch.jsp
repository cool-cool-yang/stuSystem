<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.stuSystem.manager.pojo.Teacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>查询学生</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.min.css'/>">
    <script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/public.js'/>"></script>
    <script type="text/javascript">
        //判断工号是否存在
        function teaIshas() {
            var flag = false;
            $.ajax({
                async:false,
                type:"POST",
                url:"<c:url value='/student/findOneStu.action'/>",
                data:{
                    stuId:$("#xh").val()
                },
                success:function (data) {
                    if(data==''){
                        flag = false;
                    }else {
                        flag = true;
                        var res = JSON.stringify(data);
                        //alert(res);
                        $("#stuid").text(data.stuId);
                        $("#stuname").text(data.stuName);
                        $("#stusex").text(data.stuSex);
                        $("#stuclass").text(data.stuClass);
                        $("#stumobile").text(data.stuMobile);
                        $("#stuemail").text(data.stuEmail);

                        $("#stubirthday").text(convertDate(data.stuBirthday));
                        $("#stuensch").text(convertDate(data.stuEnsch));
                        document.getElementById("xxss").style.display = "inline-block";
                    }
                }
            });
            alert(flag)
            return flag;
        }
        function getDateTime(date) {
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hh = date.getHours();
            var mm = date.getMinutes();
            var ss = date.getSeconds();
            return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
        }
        function  convertDate(time) {
            var oldDate = "/Date("+time+")/";
            var realDate = new Date(parseInt(oldDate.replace("/Date(", "").replace(")/", ""), 10));
            return  getDateTime(realDate);
        }
        //检查工号格式
        function checkgh(str)
        {
            var reg1= /^\d{10}$/;
            if(!reg1.test(str))
            {
                return false;
            }
            return true;
        }
        function submit()
        {
            var gh=document.getElementById("xh").value;
            document.getElementById("warn1").style.display="none";
            if(!checkgh(gh))
            {
                alert("学号应为10位数字！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
            }
            else if(!teaIshas())
            {
                alert("该学号不存在！请重新输入.");
                document.getElementById("warn1").style.display="inline-block";
                document.getElementById("xxss").style.display="none";
            }
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
            height:500px;
            background: #FFFFFF;
            border:2px solid #999999;
            right:0;
            top:180px;
        }
        #juzhong{
            text-align:center;
        }
        .border{
            border:1px solid #666;
            width:500px;
            margin-top:10px;
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
        .navi .naviPic{
            vertical-align: middle;
            width: 40px;
            height: 40px;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div id="juzhong"><img src="/stuSystem/img/search2.png" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:23px">查询学生</span><hr style="height:1px;">
        </div>
        <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
        请输入要查询的学生学号：  <input type="text" name="xh" id="xh" class="input01"><span style="color:red">10位数字</span>
        <input type="button" class="search" value="提交" onclick="submit();"><br/>
        <br/>
        <div id="xxss" style="display: none;">
            学生信息如下：<br/><br/>
            <div class="border" >

                学号：<span id="stuid"></span>
                <br/><br/>
                姓名：<span id="stuname"></span>
                <br/><br/>
                性别：<span id="stusex"></span>
                <br/><br/>
                班号：<span id="stuclass"></span>
                <br/><br/>
                联系方式：<span id="stumobile"></span>
                <br/><br/>
                电子邮箱：<span id="stuemail"></span>
                <br/><br/>
                出生日期：<span id="stubirthday"></span>
                <br/><br/>
                入职时间：<span id="stuensch"></span>

            </div>
        </div>
    </div>
    <jsp:include page="../foot.jsp"/>
</body>
</html>