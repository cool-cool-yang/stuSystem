<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <%--涉及到获取session--%>
    <script type="text/javascript">
        window.onload= function loginOrExit() {
            var user = '<%= session.getAttribute("user")%>';
            alert(user);
            if (!user && typeof user != "undefined" && user != 0) {
                alert("已经登录");
                $("#logId").css("display","none");
                $("#extId").css("display","");
            } else {
                alert("还未登录");
                $("#extId").css("display","none");
                $("#logId").css("display","");
            }
        };
    </script>
 <style>
    #logo{
    height:150px;
    background:#FFFFFF;
    }
    #left{
    width:700px;
    height:600px;
    background: #FFFFFF;
    border:2px solid #999999;
    }

    #right{
    width:290px;
    height:600px;
    background: #FFFFFF;
    border:2px solid #999999;
    position:absolute;
    right:0;
    top:150px;
    }
    #annoItems{
        height: 580px;
    }
    #search{
        border-bottom: #2b2b2b solid 2px;
    }
     .oneitem{
         margin-top: 15px;
     }
     .oneitem .link{
         width: 600px;
         text-align: left;
         /* 内容溢出省略*/
         white-space: nowrap;
         text-overflow: ellipsis;
         overflow: hidden;
         display: inline-block;
     }
     .oneitem .annotime{
         text-align: right;
         float: right;
     }
    .oneitem a:link,.oneitem a:visited{
        color:lightblue;
        text-transform: uppercase;
        background-color: #ffffff;
        font-weight:bold;
        text-align: left;
        text-decoration: none;
    }
     .searnavi{
         position: relative;
         margin-top: 70px;
         width: 100%;
         height: 55px;
     }
    .searnavi a:link,.searnavi a:visited{
        color:#3B3B3B;
        text-transform: uppercase;
        background-color: #ffffff;
        font-weight:bold;
        text-align: center;
        text-decoration: none;
    }
    .searnavi a:hover,.searnavi a:active{
        color:#ffffff;
        text-transform: uppercase;
        background-color:#A7F0FB;
        font-weight:bold;
        text-align: center;
        text-decoration: none;
    }
     .searnavi .directgo{
         height: 30px;
         width: 80px;
         border: #6495ED solid 1px;
     }
    .searnavi .numgo{
        width: 65px;
        height: 30px;
        padding: 2px 8px 2px 8px;
        margin-left: 10px;
        border: #6495ED solid 1px;
    }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div id="container">

    <div id="logo" style=background:url(<c:url value="/img/background.png"/>)></div>
    <div id="left">
        <div id="search">
            <input type="text" name="wanSearch"><button id="but1" onclick="searchDo();">搜索</button>
        </div>
        <%--公告栏处理--%>
        <div id="annoItems">
            <h1>公告!</h1><br>
            <c:forEach items="${annoPage.list}" var="announce">
            <div class="oneitem">
                    &nbsp;<a class="link" href="<c:url value='/user/announceShow.action'/>?annouId=${announce.annouId}">${announce.annouName}</a>
                    <span class="annotime"><fmt:formatDate value="${announce.annouIntime}" pattern="yyyy-MM-dd"/></span>
            </div>
            </c:forEach>
                <div class="searnavi">

                    <a class="directgo" href="<c:url value='/user/goMainUI.action'/>?startPage=${annoPage.pageNum>1?annoPage.pageNum-1:annoPage.pageNum}"><上一页  </a>&nbsp;||
                    <a class="directgo" href="<c:url value='/user/goMainUI.action'/>?startPage=${annoPage.pageNum<annoPage.pages?annoPage.pageNum+1:annoPage.pageNum}"> 下一页>  </a>
                    <c:choose>
                       <%-- 总页数小于10--%>
                        <c:when test="${annoPage.pages<10}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="${annoPage.pages}"/>
                        </c:when>
                       <%-- 总页数大于10--%>
                        <c:otherwise>
                            <%--默认没有越界情况--%>
                            <c:set var="begin" value="${annoPage.pageNum-5}"/>
                            <c:set var="end" value="${annoPage.pageNum+4}"/>
                            <%--处理首溢出--%>
                            <c:if test="${begin<1}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="10"/>
                            </c:if>
                            <%--处理尾溢出情况--%>
                            <c:if test="${end>annoPage.pages}">
                                <c:set var="begin" value="${annoPage.pages-9}"/>
                                <c:set var="end" value="${annoPage.pages}"/>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${begin}" end="${end}">
                       <c:choose>
                           <c:when test="${i eq annoPage.pageNum}">
                               <a class="numgo" href="#" style="border: #dddddd">${i}</a>
                           </c:when>
                           <c:otherwise>
                               <a class="numgo" href="<c:url value='/user/goMainUI.action'/>?startPage=${i}">${i}</a>
                           </c:otherwise>
                       </c:choose>
                    </c:forEach>
                    <span style="color: #2b2b2b;font-weight: bold">第${annoPage.pageNum}页/共${annoPage.pages}页</span>
                </div>
        </div>
    </div>
    <div id="right">额外公用功能</div>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>