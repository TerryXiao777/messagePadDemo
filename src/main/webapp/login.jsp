<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body onload="loginform.name.focus()">
<center>
    <form action="login" name="loginform" method="post">
        <table width="50%" bgcolor="#999999" border="0" width="100%" style="word-break:break-all;margin-top:120px" cellpadding="3" cellspacing="1">
            <tr bgcolor="#717171" class="listhead">
                <td colspan="2">请输入用户和密码进行登录！</td>
            </tr>
            <tr bgcolor="#F6F6F6">
                <td colspan="2"><b><span id="checkmessage" style="color:#ff0000"></span></b></td>
            </tr>
            <tr bgcolor="white">
                <td width="30%" align="right">用户名：</td>
                <td><input type="text" name="name" class="login" size="50" onkeydown="clearmessage()"></td>
            </tr>
            <tr bgcolor="#F6F6F6">
                <td align="right">密码：</td>
                <td><input type="password" name="pswd" class="login" size="50" onkeydown="clearmessage()"></td>
            </tr>
            <tr bgcolor="lightgrey" height="25">
                <td colspan="2" align="center">
                    <input type="button" name="submitb" value="登录" style="border:1px solid" onclick="myloginsubmit()">
                    <input type="reset" value="重置" style="border:1px solid">&nbsp;&nbsp;
                    <a href="listnew">首页</a>
                </td>
            </tr>
        </table>
    </form>
</center>
</body>

</html>
