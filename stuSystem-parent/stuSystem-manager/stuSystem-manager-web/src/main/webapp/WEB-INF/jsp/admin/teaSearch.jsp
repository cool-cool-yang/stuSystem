<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.stuSystem.manager.pojo.Teacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>查询老师</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.min.css'/>">
    <script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/public.js'/>"></script>
    <script type="text/javascript">
        //判断工号是否存在,如果存在则显示数据
        function teaIshas() {
            var flag = false;
            $.ajax({
                async:false,
                type:"POST",
                url:"<c:url value='/teacher/findOneTeacher.action'/>",
                data:{
                    teacId:$("#gh").val()
                },
                success:function (data) {
                    if(data==''){
                        flag = false;
                    }else {
                        flag = true;
                        //var res = JSON.stringify(data);
                        $("#teachid").text(data.teachId);
                        $("#teachname").text(data.teachName);
                        $("#teachsex").text(data.teachSex);
                        $("#teachtitle").text(data.teachTitle);
                        $("#teachmobile").text(data.teachMobile);
                        $("#teachemail").text(data.teachEmail);
                        $("#teachbirthday").text(getDate(data.teachBirthday));
                        $("#teachensch").text(getDate(data.teachEnsch));
                        document.getElementById("xxss").style.display = "inline-block";
                    }
                }
            });
            //alert(flag)
            return flag;
        }
       /* 提交数据*/
        function submit()
        {
            var gh=document.getElementById("gh").value;
            document.getElementById("warn1").style.display="none";
            if(!checkgh(gh))
            {
                alert("工号应为10位数字！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
            }
            else if(!teaIshas())
            {
                alert("该工号不存在！请重新输入.");
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
            <span style="font-weight:bold;font-size:23px">查询老师</span><hr style="height:1px;">
        </div>
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            请输入要查询的老师工号：  <input type="text" name="gh" id="gh" class="input01"><span style="color:red">10位数字</span>
            <input type="button" class="search" value="提交" onclick="submit();"><br/>
        <br/>
            <div id="xxss" style="display: none;">
                老师信息如下：<br/><br/>
                <div class="border" >

                    工号：<span id="teachid"></span>
                    <br/><br/>
                    姓名：<span id="teachname"></span>
                    <br/><br/>
                    性别：<span id="teachsex"></span>
                    <br/><br/>
                    职称：<span id="teachtitle"></span>
                    <br/><br/>
                    联系方式：<span id="teachmobile"></span>
                    <br/><br/>
                    电子邮箱：<span id="teachemail"></span>
                    <br/><br/>
                    出生日期：<span id="teachbirthday"></span>
                    <br/><br/>
                    入职时间：<span id="teachensch"></span>

                </div>
            </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>