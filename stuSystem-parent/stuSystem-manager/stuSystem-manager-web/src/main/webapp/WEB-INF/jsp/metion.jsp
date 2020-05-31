<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>提示</title>
    <link rel="stylesheet" type="text/css" href="/stuSystem/css/common.css">
    <script type="text/javascript" src="/stuSystem/js/jquery.js"></script>
    <script type="text/javascript" src="/stuSystem/js/public.js"></script>
    <style type="text/css">
        #metion{
            margin-top: 10%;
            margin-left: 50%;
            color: red;
        }

    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container" style="width: 1000px;height:500px">
    <h1 id="metion">${msg}</h1>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>