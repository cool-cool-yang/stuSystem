<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <!--<link rel="stylesheet" type="text/css" href="#">-->
    <!--<script type="text/javascript" src="#">-->
</head>
<body>
    <h1>hello，开始测试</h1>
    <h1>显示：${info}</h1>
    <table border="1">
        <form action="<c:url value='demo/hello.action'/>"  method="post">
            <tr>
                <td>输入姓名:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td><input type="submit" value="测试"></td>
            </tr>
        </form>
    </table>
</body>
</html>
