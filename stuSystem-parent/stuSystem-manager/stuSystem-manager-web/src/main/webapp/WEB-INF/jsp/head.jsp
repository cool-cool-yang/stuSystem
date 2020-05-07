<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.stuSystem.manager.pojo.Teacher" %>
<link rel="stylesheet" type="text/css" href="/stuSystem/css/head.css">
<script type="text/javascript">
    window.onload= function loginOrExit() {
        var user = '<%= ((Teacher)session.getAttribute("admin")).getTeachName()%>';

       /* alert(user);*/
        if (user!=null && typeof user != "undefined" && user != 0) {
            alert("已经登录");
            $('#showolog1').html(user);
            $("#logId").css("display","none");
            $("#extId").css("display","");
        } else {
            alert("还未登录");
            $("#extId").css("display","none");
            $("#logId").css("display","");
        }
    }
</script>
<body>
<div id="container">
    <div id="header">
        <div id="headerLeft" style="float:left;">
            <a href="#首 页" class="one" style="float:left;">首 页</a>
            <a href="#聊天交流" class="one" style="float:left;">聊天交流</a>
            <a href="#资源下载" class="one"style="float:left;">资源下载</a>
            <a href="#个人服务" class="one" style="float:left;">个人服务</a>
        </div>
        <div id="headerRight" style="float:right;">
            <h3 style="float:left;" class="MyIdentity" id="myIdentityId" value="当前身份" >当前身份:</h3>
            <h4  style="float:left"  id="showolog1" >游客</h4>
            <a href="<c:url value='/user/goLoginUI.action'/>" style="float:right;" class="logOrExt" id="logId">[登录]</a>
            <a href="javascript:void(0);"  style="float:right;display: none" class="logOrExt" id="extId">[退出]</a>
        </div>
    </div>
</div>
</body>