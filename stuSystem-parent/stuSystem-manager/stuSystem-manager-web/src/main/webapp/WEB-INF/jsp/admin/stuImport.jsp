<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生信息导入</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.min.css'/>">

    <script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/public.js'/>"></script>
    <script type="text/javascript">

        //判断学号是否存在。
        function stuIshas() {
            var flag = false;
            $.ajax({
                async:false,
                type:"POST",
                url:"<c:url value='/student/hasStudent.action'/>",
                data:{
                    stuId:$("#xh").val()
                },
                success:function (result) {
                    if(result==1){
                        flag = true;
                    }
                }
            });
            //alert(flag);
            return flag;
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
        function checklxfs(str)
        {
            var reg2= /^\d{11}$/;
            if(!reg2.test(str))
            {
                return false;
            }
            return true;
        }
        function checkbjh(str)
        {
            var reg3= /^\d{8}$/;
            if(!reg3.test(str))
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
        function checkdate(str){
            return (new Date(str).getDate()==str.substring(str.length-2));
        }
        function sumbit1()
        {
            var xh=document.getElementById("xh").value;
            var username=document.getElementById("username").value;
            var bjh=document.getElementById("bjh").value;
            var lxfs=document.getElementById("lxfs").value;
            var email=document.getElementById("email").value;
            var sr=document.getElementById("sr").value;
            var rxsj=document.getElementById("rxsj").value;
            var a=document.getElementsByName("userSex");
            var sex;
            for(var i=0;i<a.length;i++)
            {
                if(a[i].checked)
                {
                    sex=a[i].value;
                }
            }
            document.getElementById("warn1").style.display="none";
            document.getElementById("warn2").style.display="none";
            document.getElementById("warn3").style.display="none";
            document.getElementById("warn4").style.display="none";
            document.getElementById("warn5").style.display="none";
            document.getElementById("warn6").style.display="none";
            document.getElementById("warn7").style.display="none";
            document.getElementById("warn8").style.display="none";
            if(!checkxh(xh))
            {
                alert("学号应为10位数字！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
                return false;
            }else if(stuIshas()){
                alert("该学生已经存在！请重新输入。");
                document.getElementById("warn1").style.display="inline-block";
            }
            else if(username.length<2||username.length>20)
            {
                alert("姓名应在2～20位！请重新输入。");
                document.getElementById("warn2").style.display="inline-block";
                return false;
            }
            else if(!checkbjh(bjh))
            {
                alert("班级号应为8位数字！请重新输入。");
                document.getElementById("warn3").style.display="inline-block";
                return false;
            }
            else if(sex==null)
            {
                alert("请选择性别！");
                document.getElementById("warn4").style.display="inline-block";
                return false;
            }
            else if(!checklxfs(lxfs))
            {
                alert("联系方式应为11位数字！请重新输入。");
                document.getElementById("warn5").style.display="inline-block";
                return false;
            }
            else if(!checkemail(email))
            {
                alert("邮箱格式不正确！请重新输入。");
                document.getElementById("warn6").style.display="inline-block";
                return false;
            }
            else if(!checkdate(sr))
            {
                alert("出生日期格式不正确！请重新输入。");
                document.getElementById("warn7").style.display="inline-block";
                return false;
            }
            else if(!checkdate(rxsj))
            {
                alert("入学时间日期格式不正确！请重新输入。");
                document.getElementById("warn8").style.display="inline-block";
                return false;
            }
            return true;
        }
        function sumbit2(){
            var excel=document.getElementById("excel").value;
            if(excel=="")
            {
                alert("请上传excel文件！")
                return false;
            }
            else
                return true;
        }
    </script>
    <style>
        #left{
            width:393px;
            height:490px;
            background: #FFFFFF;
            border:2px solid #999999;
        }
        #right{
            width:407px;
            height:490px;
            background: #FFFFFF;
            border:2px solid #999999;
            position:absolute;
            right:0;
            top:0px;
        }
        .naviHead2{
            font-weight: bold;
            color: #000000 !important;
            letter-spacing: 2px;
            border-bottom: 1px solid #D0D0D0;
            padding-bottom: 3px;
            text-indent: 5px;
        }
        .navi{
            width: 180px;
            height: 40px;
            text-align: left;
            padding: 2px;
            display: inline-block;
            border: 0px dotted;
            letter-spacing: 2px;
            font-size:10px;
            color:#969696  ;
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
        .navi:hover{
            background: #9ABC32;
            color: white;
        }
        .infoPanel{
            background: white;border: 1px solid #C0C0C0;padding: 5px;
            margin-top:10px;
            margin-left: 10px;
        }
        .input01 {
            border: 1px solid #CDC28D;
            background-color: #F2F9FD;
            height: 20px;
            width: 180px;
            padding-top: 4px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            padding-left: 10px;
        }
        .input02 {
            border: 1px solid #CDC28D;
            background-color: #F2F9FD;
            height: 20px;
            width: 180px;
            padding-top: 4px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            padding-left: 10px;
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
    <div id="left">
        <div style="text-align:center;">
            <h2>单项导入学生数据</h2><br/>
        </div>
        <form action="<c:url value='/student/insertOneStu.action'/>" name="form1" method="post" onsubmit="return sumbit1()">
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            学生学号：  <input type="text" name="userId" id="xh" class="input01" value="${importStu.userId}"><span style="color:red">10位数字</span><br/><br/>
            <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
            学生姓名：  <input type="text" name="username" id="username" class="input01" value="${importStu.username}"><span style="color:red">2～20位</span><br/><br/>
            <span id="warn3" style="color:red;font-weight:bold;display:none;">➤</span>
            学生班号：  <input type="text" name="userClass" id="bjh" class="input01"  value="${importStu.userClass}"><span style="color:red">8位数字</span><br/><br/>
            <span id="warn4" style="color:red;font-weight:bold;display:none;">➤</span>
            性别：<input type="radio" name="userSex" value="男">男
            <input type="radio" name="userSex" value="女">女<br/><br/>
            <span id="warn5" style="color:red;font-weight:bold;display:none;">➤</span>
            联系方式：  <input type="text" name="userMobile" id="lxfs" class="input01" value="${importStu.userMobile}"><span style="color:red">11位数字</span><br/><br/>
            <span id="warn6" style="color:red;font-weight:bold;display:none;">➤</span>
            电子邮箱：  <input type="text" name="userEmail" id="email" class="input01" value="${importStu.userEmail}"><span style="color:red">xx+@xx.+xx</span><br/><br/>
            <span id="warn7" style="color:red;font-weight:bold;display:none;">➤</span>
           <%-- onfocus="WdatePicker({startDate:'yyyy/MM/dd',dateFmt:'yyyy/MM/dd',isShowWeek:true})"--%>
            出生日期：  <input type="text" name="userBirthday" id="sr" class="input02"  value="${importStu.userBirthday}">
            <span style="color:red">xxxx-xx-xx</span><br/><br/>
            <span id="warn8" style="color:red;font-weight:bold;display:none;">➤</span>
            入学时间：  <input type="text" name="userEnSch" id="rxsj" class="input02" value="${importStu.userEnSch}"><span style="color:red">xxxx-xx-xx</span><br/><br/>
            <div style="text-align:center;">
                <input type="reset" class="import" value="重置">&nbsp;
                <input type="submit" class="import" value="提交"><br/>
                <span style="color:red">${infor}</span>
            </div>
        </form>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <h2>批量导入学生数据</h2>
            <h4 style="color:red">注：上传excel表，其后缀为.xls或.xlsx <br>表中格式参考单项导入和下图</h4><br>
            <img src="<c:url value='/img/temp1.png'/>"/>
            <form action="<c:url value='/student/insertStuTable.action'/>" name="form2" method="post" enctype="multipart/form-data" onsubmit="return sumbit2()">
                <input type="file" name="mFile" id="excel" accept=".xls,.xlsx"><br/>
                <div style="text-align:center;">
                    <input type="submit" class="import"  value="提交"><br/>
                </div>
            </form>
            <div>
                <span  style="color:red">${tableInfo}</span>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>