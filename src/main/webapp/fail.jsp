<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>错误提示！</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<center>
    <c:set var="mess" value="${requestScope.message}"/>
    <c:if test="${empty mess}">
        <c:set var="mess" value="●访问失败！"/>
    </c:if>

    <table border="1" width="360" height="150" style="margin-top:120px">
        <tr><td align="center">${mess}</td></tr>
    </table>
</center>
</body>
</html>
