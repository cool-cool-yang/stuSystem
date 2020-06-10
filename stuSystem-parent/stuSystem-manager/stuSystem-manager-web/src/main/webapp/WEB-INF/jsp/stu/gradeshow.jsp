<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>成绩展现</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <script type="text/javascript">
        var time=1;//序号
        function addtable()//有几门课就调用几次该方法
        {
            var grade=document.getElementById("grade");
            var cells = grade.rows.item(0).cells.length ;
            var data=new Array();//后台数据库调入数据
            var tr=document.createElement("tr");
            td1=document.createElement("td");
            td1.innerHTML=time;
            tr.append(td1);
            time++;
            for(var i=0;i<cells-1;i++)
            {
                var td=document.createElement("td");
                td.innerHTML=data[i];
                tr.append(td);
            }
            grade.append(tr);
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
            height:580px;
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
        table {
            width: 90%;
            background: #ccc;
            margin: 10px auto;
            border-collapse: collapse;/*border-collapse:collapse合并内外边距(去除表格单元格默认的2个像素内外边距*/
        }
        th,td {
            height: 25px;
            line-height: 25px;
            text-align: center;
            border: 1px solid #ccc;
        }
        th {
            background: #eee;
            font-weight: normal;
        }
        tr {
            background: #fff;
        }
        tr:hover {
            background: #cc0;
        }
        td a {
            color: #06f;
            text-decoration: none;
        }
        td a:hover {
            color: #06f;
            text-decoration: underline;
        }
        .oneitem{
            margin-top: 2px;
            height: 25px;
        }
        .searnavi{
            position: relative;
            margin-top: 70px;
            margin-left: 50px;
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
<jsp:include page="../head.jsp"/>
<div id="container">
    <div id="main">
        <div style="text-align:center">
            <img src="<c:url value='/img/search.png'/>" class="naviPic">
            <span style="font-weight:bold;font-size:23px">成绩查询</span>
            <hr style="height:1px;">
        </div>
        <%--使用分页效果实现--%>
        <table id="grade" border="1">
            <tr>
                <td rowspan='2' width="80px">序号</td>
                <td rowspan='2' width="160px">课程编号</td>
                <td rowspan='2' width="160px">课程名称</td>
                <td rowspan='2' width="80px">类别</td>
                <td rowspan='2' width="80px">成绩</td>
            </tr>
            <tr>
            </tr>
        </table>
        <div id="annoItems">
            <c:set var="count" value="1"/>
            <c:forEach items="${Pages.list}" var="one">
                <div class="oneitem">
                    &nbsp;<table id="grade2" border="1">
                    <tr>
                        <td rowspan='2' width="80px">${count}</td>
                        <td rowspan='2' width="160px">${one.scores.scoresTcid}</td>
                        <td rowspan='2' width="160px">${one.cstmCourse.courser.courserName}</td>
                        <td rowspan='2' width="80px">${one.cstmCourse.courseType.typeName}</td>
                        <td rowspan='2' width="80px">${one.scores.scoresRes}</td>
                    </tr>
                </table>
                </div>
                <c:set var="count" value="${count = count+1}"/>
            </c:forEach>
            <%--分页导航--%>
            <div class="searnavi">
                <a class="directgo" href="<c:url value='/student/gradeShow.action'/>?startPage=${Pages.pageNum>1?Pages.pageNum-1:Pages.pageNum}"><上一页  </a>&nbsp;||
                <a class="directgo" href="<c:url value='/student/gradeShow.action'/>?startPage=${Pages.pageNum<Pages.pages?Pages.pageNum+1:Pages.pageNum}"> 下一页>  </a>
                <c:choose>
                    <%-- 总页数小于10--%>
                    <c:when test="${Pages.pages<10}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${Pages.pages}"/>
                    </c:when>
                    <%-- 总页数大于10--%>
                    <c:otherwise>
                        <%--默认没有越界情况--%>
                        <c:set var="begin" value="${Pages.pageNum-5}"/>
                        <c:set var="end" value="${Pages.pageNum+4}"/>
                        <%--处理首溢出--%>
                        <c:if test="${begin<1}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="10"/>
                        </c:if>
                        <%--处理尾溢出情况--%>
                        <c:if test="${end>Pages.pages}">
                            <c:set var="begin" value="${Pages.pages-9}"/>
                            <c:set var="end" value="${Pages.pages}"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${begin}" end="${end}">
                    <c:choose>
                        <c:when test="${i eq Pages.pageNum}">
                            <a class="numgo" href="#" style="border: #dddddd">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="numgo" href="<c:url value='/student/gradeShow.action'/>?startPage=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <span style="color: #2b2b2b;font-weight: bold">第${Pages.pageNum}页/共${Pages.pages}页</span>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>