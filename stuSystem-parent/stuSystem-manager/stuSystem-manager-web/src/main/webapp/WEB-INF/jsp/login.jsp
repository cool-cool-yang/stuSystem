<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录界面</title>
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>



    <script type="text/javascript">
        function changeImg() {
            var img = document.getElementById("yzmI");
            img.src =  "<c:url value='/code/showYzm.action'/>"+"?"+new Date().getTime();
        }
        /* 前端验证代码*/
        function sumbit()
        {
            var b=document.getElementById("identity");
            var identity;
            var username=document.getElementById("username").value;
            var pwd=document.getElementById("pwd").value;
            var yzm=document.getElementById("yzm").value;
            document.getElementById("info1").style.display="none";
            document.getElementById("info2").style.display="none";
            document.getElementById("info3").style.display="none";
            document.getElementById("info4").style.display="none";
            for(var i=0;i<b.length;i++)
            {
                if(b[i].selected&&b[i].value!="0")
                {
                    identity=b[i].value;
                }
            }
            if(identity==null)
            {
                alert("请选择身份！");
                document.getElementById("info1").style.display="block";
                return false;
            }
            else if(username.length==0)
            {
                alert("账号不能为空！请重新输入。");
                document.getElementById("info2").style.display="block";
                return false;
            }
            else if(pwd.length==0 )
            {
                alert("密码不能为空！请重新输入。");
                $("#info3").html("请输入密码！")
                document.getElementById("info3").style.display="block";
                return false;
            }
            else if(yzm.length==0)
            {
                document.getElementById("info4").style.display="block";
                alert("验证码不能为空！请重新输入。");
                return false;
            }
            var flag = true;
            $.ajax(
                {
                    async:false,  /*将ajax设置为同步执行,才可以获取到内部变量的值*/
                    type:'POST',
                    url:"<c:url value='/code/CodeValidate.action'/>",
                    data:{
                        inputCode: $("#yzm").val()
                    },
                    success:function(result){
                        if(result==0){
                            $("#info4").text("请正确输入验证码");
                            $("#info4").css("display","");
                            flag = false;
                        }else{
                            $("#info4").text("请输入验证码！");
                            $("#info4").css("display","none");
                        }
                    }});
            return flag;
        }
    </script>
    <style type="text/css">
        #wrap {
            height: 666px;
            width: 1050px;
            margin:0 auto;
            background-image: url(<c:url value='/img/loginbg.gif'/>);
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center center;
            position: relative;
        }
        #head {
            height: 80px;
            width: 1050px;
            margin:0 auto;
            background-color: #666666;
            text-align: center;
            position: relative;
        }
        #foot {
            width: 1050px;
            height: 120px;
            margin:0 auto;
            background-color: #CC9933;
            position: relative;
        }
        #wrap .logGet {
            height: 408px;
            width: 368px;
            position: absolute;
            background-color: #FFFFFF;
            top: 20%;
            right: 15%;
        }
        .logC a button {
            width: 100%;
            height: 45px;
            background-color: #ee7700;
            border: none;
            color: white;
            font-size: 18px;
        }
        .logGet .logD.logDtip .p1 {
            display: inline-block;
            font-size: 28px;
            margin-top: 30px;
            width: 86%;
        }
        #wrap .logGet .logD.logDtip {
            width: 86%;
            border-bottom: 1px solid #ee7700;
            margin-bottom: 60px;
            margin-top: 0px;
            margin-right: auto;
            margin-left: auto;
        }
        .logGet .lgD img {
            position: absolute;
            top: 12px;
            left: 8px;
        }
        .logGet .lgD input {
            width: 100%;
            height: 42px;
            text-indent: 2.5rem;
        }
        .logGet .lgE img {
            position: absolute;
            top: 12px;
            left: 300px;
        }
        .logGet .lgE input {
            width: 100%;
            height: 42px;
            text-indent: 2.5rem;
        }
        .logGet .logE.logDtip .p1 {
            display: inline-block;
            font-size: 28px;
            margin-top: 30px;
            width: 86%;
        }
        #wrap .logGet .logE.logDtip {
            width: 86%;
            border-bottom: 1px solid #ee7700;
            margin-bottom: 60px;
            margin-top: 0px;
            margin-right: auto;
            margin-left: auto;
        }
        #wrap .logGet .lgE {
            width: 86%;
            position: relative;
            margin-bottom: 30px;
            margin-top: 30px;
            margin-right: auto;
            margin-left: auto;
        }

        #wrap .logGet .lgE #yzImg{
            position: absolute;
            top:30px;
            left:150px;
        }

        #wrap .logGet .lgD {
            width: 86%;
            position: relative;
            margin-bottom: 30px;
            margin-top: 30px;
            margin-right: auto;
            margin-left: auto;
        }
        #wrap .logGet .logC {
            width: 86%;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
        }
        .btns {
            width: 100%;
            height: 45px;
            background-color: #ee7700;
            border: none;
            color: white;
            font-size: 18px;
        }

        .title {
            font-family: "宋体";
            color: #FFFFFF;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
            font-size: 36px;
            height: 40px;
            width: 30%;
        }

        .copyright {
            font-family: "宋体";
            color: #FFFFFF;
            position: absolute;
            top: 20%;
            left: 50%;
            transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
            height: 60px;
            width: 40%;
            text-align:center;
        }


        #foot .copyright .img {
            width: 100%;
            height: 24px;
            position: relative;
        }
        .copyright .img .icon {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            background-image: url(<c:url value='/img/1.png'/>);
            background-repeat: no-repeat;

            margin-right: 5px;
        }

        .copyright .img .icon1 {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            background-image: url(<c:url value='/img/2.png'/>);
            background-repeat: no-repeat;
            margin-right: 5px;
        }
        .copyright .img .icon2 {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            background-image: url(<c:url value='/img/3.png'/>);
            background-repeat: no-repeat;
            vertical-align: middle;
            margin-right: 5px;
        }
        #foot .copyright p {
            height: 24px;
            width: 100%;
        }

    </style>
</head>
<div class="header" id="head">
    <div class="title">学生管理系统</div>

</div>
<form action="<c:url value='/user/UserLogin.action'/>" name="form1" method="post" onsubmit="return sumbit()">
    <div class="wrap" id="wrap">
        <div class="logGet">
            <!-- 头部提示信息 -->
            <div class="logD logDtip">
                <p class="p1">登录</p>
            </div>
            <!-- 输入框 -->
            <div class="lgD">
                请选择您的身份：<select name ="identity" id="identity">
                <option value="0">--请选择--</option>
                <option value="1">学生</option>
                <option value="2">老师</option>
            </select>
                <font color="red"><span id="info1" style="display:none">请选择身份！</span></font>
            </div>
            <div class="lgD">
                <img src="<c:url value='/img/logName.png'/>" width="20" height="20" alt=""/>
                <input type="text" name="username" id="username" value="${user.username }"
                       placeholder="输入账号"  />
                <font color="red"><span id="info2" style="display:none">请输入账号！</span></font>
            </div>
            <div class="lgD">
                <img src="<c:url value='/img/logPwd.png'/>" width="20" height="20" alt=""/>
                <input type="password" name="pwd" id="pwd" value="${user.pwd }"
                       placeholder="输入密码" />
                <font color="red"><span id="info3" >${err}</span></font>
            </div>
            <div class="lgE">
                <img src="<c:url value='/code/showYzm.action'/>" width="100" height="50" alt="" id="yzmI" onclick="changeImg();"/>
                <input type="text" name="yzm" id="yzm"
                       placeholder="输入验证码" />
                <font color="red"><span id="info4" style="display:none">请输入验证码！</span></font>
            </div>
            <div>
                <input type="submit" class="btns" value="登录">
            </div>
        </div>
    </div>

    <div class="footer" id="foot">
        <div class="copyright">
            <p>Copyright © 2020 Qunar.com Inc. All Rights Reserved.</p>
            <div class="info" style="float: left;">
                <div class="img">
                    <i class="icon"></i><span>联系邮箱：474620125@qq.com</span>
                </div>

                <div class="img">
                    <i class="icon1"></i><span>联系地址：重庆邮电大学</span>
                </div>

                <div class="img">
                    <i class="icon2"></i><span>联系电话：13340262405</span>
                </div>
            </div>
        </div>

    </div>
</form>
</body>
</html>