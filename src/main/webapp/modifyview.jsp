<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>修改留言</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/edit.js"></script>

</head>
<body onload="checkLen()">
<center>
    <c:set var="word" value="${requestScope.single}"/>
    <c:if test="${!empty word}">
        <form action="modifyrun" name="editform" method="post">
            <input type="hidden" name="wordId" value="${word.id}">
            <input type="hidden" name="autoforward" value="${requestScope.autoforward}">
            <table width="90%" border="0" cellpadding="0" cellspacing="0">
                <tr height="50" valign="bottom">
                    <td>
                        <font color="#7F7F7F">
                            最多：<b><span id="ContentAll" style="width:40px;text-align:center">500</span></b>
                            已用：<b><span id="ContentUse" style="width:40px;text-align:center">0</span></b>
                            剩余：<b><span id="ContentRem" style="width:40px;text-align:center">500</span></b>
                        </font>
                        <b><span id="checkmessage" style="color:red"></span></b>
                    </td>
                    <td width="23%">
                        作者：${word.author}<br>
                        发表：${word.time}
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                    <textarea rows="12" cols="97" name="content" onpropertychange="checkLen()">${word.contentForEdit}</textarea>
                    </td>
                </tr>
                <tr height="30">
                    <td colspan="2" align="center">
                        <input type="button" name="submitb" value="提交修改" onclick="editformsubmit()">
                        <input type="reset" value="重新填写">
                        <input type="reset" value="放弃修改" onclick="window.history.go(-1)">
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
</center>
</body>
</html>
