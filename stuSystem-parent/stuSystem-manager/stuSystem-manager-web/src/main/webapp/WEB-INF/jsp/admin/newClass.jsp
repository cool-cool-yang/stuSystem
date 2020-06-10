<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>开课</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <script type="text/javascript">


    /*
    当课程表太大时，使用异步请求更新输入栏。
    目的：减少数据的携带量
     */
   /* function mhcx1()
    {
        $.ajax({
            async:true,
            type:"POST",
            url:"“
            data:{
                courseName:$("#cname").val()
            },
            success:function (result) {
                //alert("异步查询："+result);
                $('#list1').datalist('getPanel').panel('clear');
                alert(result);
            }
        });


    for(var i=0;i<Cname.length;i++)
    {
    list1.options.add(new Option(Cname[i],Cname[i]));
    }
        xmlHttp.send();
    }

    function mhcx2()
    {
    var list2=document.getElementById("list2");
    var xmlHttp;
    if(window.XMLHttpRequest){
    xmlHttp=new XMLHttpRequest();
    }
    else{
    xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
    }
    var url;
    xmlHttp.open('get',url);
    xmlHttp.onreadystatechange=function(){
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
    {
        var Tname=xmlHttp.responseText;
        Tname=Tname.split(",");


    for(var i=0;i<Tname.length;i++)
    {
    list1.options.add(new Option(Tname[i],Tname[i]));
    }
    }
    }
    xmlHttp.send();
    }*/

    function fun1(){
       //alert($("#cname").val());
        var input_select=$("#cname").val();
        var option_length=$("option").length;
        for(var i=0;i<option_length;i++){
            var option_value=$("option").eq(i).attr('date-value');
            var optionT = $("option").eq(i).val();
            if(input_select==optionT){
                $("#courseId").val(option_value);
                break;
            }
        }
        //alert($("#courseId").val());
    }
    function fun2(){
        var input_select=$("#tname").val();
        var option_length=$("option").length;
        for(var i=0;i<option_length;i++){
            var option_value=$("option").eq(i).attr('date-value');
            var optionT = $("option").eq(i).val();
            if(input_select==optionT){
                $("#teachId").val(option_value);
                break;
            }
        }
        //alert( $("#teachId").val());
    }
    function sumbit()
    {
    var cname=document.getElementById("cname").value;
    var tname=document.getElementById("tname").value;
    var excel=document.getElementById("excel").value;
    document.getElementById("warn1").style.display="none";
    document.getElementById("warn2").style.display="none";
    if(cname.length==0)
    {
    alert("课程编号不能为空!请重新输入.");
    document.getElementById("warn1").style.display="inline-block";
    return false;
    }
    else if(tname.length==0)
    {
    alert("教师编号不能为空!请重新输入.");
    document.getElementById("warn2").style.display="inline-block";
    return false;
    }
    else if(excel=="")
    {
    alert("请上传excel文件!");
    return false;
    }
    return true;
    }
    </script>
    <style>

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
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value='/img/kc.png'/>" class="naviPic">
        <span style="font-weight:bold;font-size:23px">开设课程</span>
        <span style="font-weight:bold;font-size:20px;float:right;color:red;">上传选课学生表↓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span>
        <hr style="height:1px;">
    </div>
    <form action="<c:url value='/admin/insertOneClass.action'/>" name="form" method="post" onsubmit="return sumbit()" enctype="multipart/form-data">
        <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
        课程编号：<input type="text" name="courseName" id="cname" class="input01" onkeyup="fun1();" list="list1" autocomplete="off">
                <input type="hidden" name="courseId" id="courseId">
        <datalist id="list1" >
            <c:forEach items="${ac}" var="onec">
                <option date-value="${onec.get("courserId")}">${onec.get("courserName")}</option>
            </c:forEach>
        </datalist>
        <span  style="color:red;font-weight:bold;float:right;">仅上传学号，格式如下图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <br>
        <img src="<c:url value='/img/stueg2.png'/>" style="position:absolute;right:110px;" class="naviPic">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <br><br><br><br><br>
        <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
        教师编号：<input type="text" name="teachName" id="tname" onkeyup="fun2();"class="input01" list="list2" autocomplete="off">
                 <input type="hidden" name="teachId" id="teachId">
        <datalist id="list2">
            <c:forEach items="${tc}" var="onec">
                <option date-value="${onec.get("teachId")}">${onec.get("teachName")}</option>
            </c:forEach>
        </datalist>
        <input type="file" style="float:right" name="mFile" id="excel" accept=".xls,.xlsx"><br/>
        <br><br><br><br><br>
        <div style="text-align:center">
            <input type="submit" class="import" value="提交"><br/>
        </div>
    </form>
</div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>