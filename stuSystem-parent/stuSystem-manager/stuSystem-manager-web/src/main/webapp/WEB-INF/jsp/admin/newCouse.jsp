<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加课程</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>

    <script type="text/javascript">
        //使用异步检验课程名称是否在数据发生重读
        function courseNamehas(cname) {
            var flag = false;
            $.ajax({
                async:false,
                type:"POST",
                url:"<c:url value='/admin/courseHas.action'/>",
                data:{
                    courseName:cname
                },
                success:function (result) {
                   //alert("异步查询："+result);
                    if(result==1){
                        flag = true;
                    }
                }
            });
            //alert(flag);
            return flag;
        }
        function sumbit()
        {
            var cname=document.getElementById("cname").value;
            var kcjs=document.getElementById("kcjs").value;
            var b=document.getElementById("ctype");
            var ctype;
            for(var i=0;i<b.length;i++)
            {
                if(b[i].selected&&b[i].value!="0")
                {
                    ctype=b[i].value;
                }
            }
            document.getElementById("warn1").style.display="none";
            document.getElementById("warn2").style.display="none";
            document.getElementById("warn3").style.display="none";
            if(cname.length==0) {
                alert("课程名称不能为空!请重新输入。");
                document.getElementById("warn1").style.display = "inline-block";
                return false;
            }else if(ctype==null)
            {
                alert("请选择课程类型!");
                document.getElementById("warn2").style.display="inline-block";
                return false;
            }
            else if(kcjs.length==0)
            {
                alert("课程介绍不能为空!请重新输入.");
                document.getElementById("warn3").style.display="inline-block";
                return false;
            }else if(courseNamehas(cname)){
                alert(cname+"课程已经存在，请重新输入。");
                document.getElementById("warn1").style.display = "inline-block";
                return false;
            }
            return true;
        }
    </script>
    <style>
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
        .input02 {
            border: 1px solid #CDC28D;
            height: 250px;
            width: 720px;
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
        <div id="juzhong"><img src="<c:url value='/img/kc2.png'/>" style="vertical-align:middle;">
            <span style="font-weight:bold;font-size:23px">添加新课程</span><hr style="height:1px;">
        </div>
        <form action="<c:url value='/admin/insertOneCour.action'/>" name="form" method="post" onsubmit="return sumbit()">
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            课程名称：  <input type="text" name="courserName" id="cname" class="input01"><br/><br/>
            <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
            选择课程类型：<select name ="courserType" id="ctype" class="input01">
            <option value="0">--请选择--</option>
            <option value="1">专业理论</option>
            <option value="2">数学计算</option>
            <option value="3">实践操作</option>
            <option value="4">其他</option>
        </select><br/><br/>
            <span id="warn3" style="color:red;font-weight:bold;display:none;">➤</span>
            课程介绍：<textarea name="courserDesc" id="kcjs" class="input02" placeholder="请输入介绍"></textarea>
            <br><br>
            <div style="text-align:center">
                <input type="submit" class="import" value="提交"><br/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>