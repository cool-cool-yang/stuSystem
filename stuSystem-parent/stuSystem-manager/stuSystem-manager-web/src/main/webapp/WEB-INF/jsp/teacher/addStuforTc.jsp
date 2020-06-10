<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生补录</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <script type="text/javascript">

        function fun1(){
            //alert($("#cname").val());
            var input_select=$("#cname").val();
            var option_length=$("option").length;
            for(var i=0;i<option_length;i++){
                var option_value=$("option").eq(i).attr('date-value');
                var optionT = $("option").eq(i).val();
                if(input_select==optionT){
                    $("#teachCID").val(option_value);
                    break;
                }
            }
            //alert($("#courseId").val());
        }
        function checkxh(str)
        {
            var reg1= /^\d{10}$/;
            if(!reg1.test(str))
            {
                return false;
            }
            return true;
        }
        function sumbit()
        {
            var xh=document.getElementById("xh").value;
            var cname=document.getElementById("cname").value;
            var tname=document.getElementById("tname").value;
            document.getElementById("warn1").style.display="none";
            document.getElementById("warn2").style.display="none";
            document.getElementById("warn3").style.display="none";
            if(!checkxh(xh))//不存在应有相应提示报错
            {
                alert("学号应为10位数字！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
                return false;
            }
            else if(cname.length==0)//不存在应有相应提示报错
            {
                alert("课程编号不能为空!请重新输入.");
                document.getElementById("warn2").style.display="inline-block";
                return false;
            }
            else if(tname.length==0)//不存在应有相应提示报错
            {
                alert("教师编号不能为空!请重新输入.");
                document.getElementById("warn3").style.display="inline-block";
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
            height:400px;
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
        .input01 {
            border: 1px solid #CDC28D;
            height: 25px;
            width: 233px;
            padding-top: 4px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 14px;
            padding-left: 10px;
            margin-left:5px;
        }
        .import {
            background-color:#4CAF50;
            text-align:center;
            border: none;
            color: white;
            padding: 7px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
        }
    </style>
</head>
<body>
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div style="text-align:center">
            <img src="<c:url value='/img/kc.png'/>" class="naviPic">
            <span style="font-weight:bold;font-size:23px">学生补录</span>
            <hr style="height:1px;">
        </div>
        <form action="<c:url value='/teacher/insertOneTcItem.action'/>" name="form" method="post" onsubmit="return sumbit()">
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            课程名称：<input type="text" name="cname" id="cname" class="input01" onkeyup="fun1();" list="list1" autocomplete="off">
            <datalist id="list1">
                <c:forEach items="${tcInfoList}" var="tc">
                    <option date-value="${tc.tcId}">${tc.tcCourser}</option>
                </c:forEach>
            </datalist>
            &nbsp;&nbsp;授课编号：<input type="text" name="tcId" id="teachCID" class="input01" readonly="readonly"  placeholder="显示授课编号">
            <br><br><br><br><br>
            <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
            学生学号：<input type="text" name="stuId" id="xh" class="input01" onkeyup="mhcx2()" list="list2" autocomplete="off"><span style="color:red">10位数字</span>
            <br><br><br><br>
            <div style="text-align:center">
                <input type="submit" class="import" value="提交"><br/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>