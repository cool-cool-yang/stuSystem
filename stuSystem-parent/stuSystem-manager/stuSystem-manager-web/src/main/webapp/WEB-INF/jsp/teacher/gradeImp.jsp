<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生成绩导入</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <script type="text/javascript">

        //根据课程编号显示学生
        function showStu() {
            $.ajax({
                type:"POST",
                url:"<c:url value='/teacher/asyncAllStuScores.action'/>",
                data:{
                    tcId:$("#teachCID").val()
                },
                success:function (result) {
                    var list2 = $("#list2");
                     list2.empty();
                    for (var key in result) {
                        var stuID = result[key].student.stuId;
                        var stuRes= result[key].scoresRes;
                         var one = '<option date-value="'+stuRes+'">'+stuID+'</option>';
                        list2.append(one);
                    }
                }
            });
        }
       /* 根据选中的学生显示分数:没分数时不处理*/
        function showStuGrade(){
            //alert($("#cname").val());
            var input_select=$("#xh").val();
            var option_length=$("option").length;
            for(var i=0;i<option_length;i++){
                var option_value=$("option").eq(i).attr('date-value');
                var optionT = $("option").eq(i).val();
                if(input_select==optionT){
                    $("#grade").val(option_value);
                    break;
                }
            }
        }
        //单项成绩导入的动态设置
        function fun1(){
            //alert($("#cname").val());
            var input_select=$("#tcname").val();
            var option_length=$("option").length;
            for(var i=0;i<option_length;i++){
                var option_value=$("option").eq(i).attr('date-value');
                var optionT = $("option").eq(i).val();
                if(input_select==optionT){
                    $("#teachCID").val(option_value);
                    showStu();
                    break;
                }
            }
            //alert($("#courseId").val());
        }
        //excel表导入的课程编号显示
        function fun2(){
            //alert($("#cname").val());
            var input_select=$("#tcname2").val();
            var option_length=$("option").length;
            for(var i=0;i<option_length;i++){
                var option_value=$("option").eq(i).attr('date-value');
                var optionT = $("option").eq(i).val();
                if(input_select==optionT){
                    $("#teachCID2").val(option_value);
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
        function checkgrade(str)
        {
            var reg = /^(0|([1-9]\d)|(100))(\.\d{0,1})?$/;
            if(!reg.test(str))
            {
                return false;
            }
            return true;
        }

        function sumbit1(){

            var cname=document.getElementById("tcname").value;
            var tname=document.getElementById("teachCID").value;
            var xh=document.getElementById("xh").value;
            var grade=document.getElementById("grade").value;
            document.getElementById("warn1").style.display="none";
            document.getElementById("warn2").style.display="none";
            document.getElementById("warn3").style.display="none";
            document.getElementById("warn4").style.display="none";
            if(cname.length==0){ //不存在应有相应提示报错
                alert("课程名称不能为空!请重新输入.");
                document.getElementById("warn1").style.display="inline-block";
                return false;
            }
            else if(tname.length==0)//不存在应有相应提示报错
            {
                alert("课程编号不能为空!请重新输入.");
                document.getElementById("warn2").style.display="inline-block";
                return false;
            }
            else if(!checkxh(xh))//不存在应有相应提示报错
            {
                alert("学号应为10位数字！请重新输入。");
                document.getElementById("warn3").style.display="inline-block";
                return false;
            }
            else if(!checkgrade(grade))
            {
                alert("成绩格式不正确！请重新输入.");
                document.getElementById("warn4").style.display="inline-block";
                return false;
            }
            //alert("方法测试末尾");
            return true;
        }
        function sumbit2(){
            var cname=document.getElementById("tcname2").value;
            var tname=document.getElementById("teachCID2").value;
            var excel=document.getElementById("excel").value;
            document.getElementById("warn5").style.display="none";
           if(cname.length==0){
               alert("课程名称不能为空!请重新输入.");
               document.getElementById("warn5").style.display="inline-block";
               return false;
           }else if(tname.length==0){
               alert("课程编号不能为空!请重新输入.");
               return false;
           }else if(excel==""){
                alert("请上传excel文件！")
                return false;
            }
                return true;
        }
    </script>
    <style>
        #left{
            width:485px;
            height:500px;
            background: #FFFFFF;
            border:1px solid #999999;
        }
        #right{
            width:400px;
            height:500px;
            background: #FFFFFF;
            border:1px solid #999999;
            position:absolute;
            right:0;
            top:0px;
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
    <div id="left">
        <div style="text-align:center;">
            <h2>单项导入学生成绩</h2><br/>
        </div>
        <form action="<c:url value='/teacher/updateOneStuGrade.action'/>" name="form" method="post" onsubmit="return sumbit1();">
            <input type="hidden" name="teacherId" value="#">
            <span id="warn1" style="color:red;font-weight:bold;display:none;">➤</span>
            课程名称：<input type="text" name="cname" id="tcname" class="input01" onkeyup="fun1();" list="list1" autocomplete="off">
            <datalist id="list1">
                <c:forEach items="${tcInfoList}" var="tc">
                    <option date-value="${tc.tcId}">${tc.tcCourser}</option>
                </c:forEach>
            </datalist>
            <br><br><br><br>
            <span id="warn2" style="color:red;font-weight:bold;display:none;">➤</span>
            课程编号：<input type="text" name="scoresTcid" id="teachCID" class="input01" readonly="readonly" placeholder="显示授课编号">
            <br><br><br><br>
            <span id="warn3" style="color:red;font-weight:bold;display:none;">➤</span>
            学生学号：<input type="text" name="scoresStuid" id="xh" class="input01" onkeyup="showStuGrade()" list="list2" autocomplete="off"><span style="color:red">10位数字</span>
            <datalist id="list2">
            </datalist>
            <br><br><br><br>
            <span id="warn4" style="color:red;font-weight:bold;display:none;">➤</span>
            课程成绩：<input type="text" name="scoresRes" id="grade" class="input01" autocomplete="off"><span style="color:red">0~100的1位小数</span>
            <div style="text-align:center;width: 450px;margin-top: 5px">
                <input type="reset" class="import" value="重置">&nbsp;
                <input type="submit" class="import" value="提交"><br/>
            </div>
        </form>
    </div>
    <div id="right">
        <div style="text-align:center;">
            <h2>批量导入学生成绩</h2><br/>
            <form action="<c:url value='/teacher/updateStuGrasWithExcel.action'/>" name="form2" method="post" enctype="multipart/form-data" onsubmit="return sumbit2()">
                <span id="warn5" style="color:red;font-weight:bold;display:none;">➤</span>
                课程名称：<input type="text" name="tcname2" id="tcname2" class="input01" onkeyup="fun2();" list="list1" autocomplete="off">
                <datalist id="list1">
                    <c:forEach items="${tcInfoList}" var="tc">
                        <option date-value="${tc.tcId}">${tc.tcCourser}</option>
                    </c:forEach>
                </datalist>
                <br/><br/>
                课程编号：<input type="text" name="scoresStuid" id="teachCID2" class="input01" readonly="readonly"/>
                <br><br>
                <div id="impExcel" style="width: 240px;text-align: left; margin-left: 30px">
                    <input type="file" name="mFile" id="excel" accept=".xls,.xlsx"><br/>
                </div>
                <div style="text-align:center;margin-top: 5px">
                    <input type="reset" class="import" value="重置"/>&nbsp;
                    <input type="submit" class="import"  value="提交"><br/>
                </div>
            </form>
            <br/><hr/>
            <h4 style="color:red">注：上传excel表，其后缀为.xls或.xlsx <br>表中格式参考单项导入和下图</h4><br>
            <img src="<c:url value='/img/stueg3.png'/>">
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>