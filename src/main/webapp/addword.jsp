<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/edit.js"></script>
<br>
<img src="images/addtitle.jpg"/>
<hr color="#ACCE69" width="90%" style="border-style:solid;margin-top:-10px">

<a name="addword"></a>
<form action="add" name="editform" method="post">
    <table width="90%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="67%">
                <font color="#7F7F7F">
                    最多：<b><span id="ContentAll" style="width:40px;text-align:center">500</span></b>
                    已用：<b><span id="ContentUse" style="width:40px;text-align:center">0</span></b>
                    剩余：<b><span id="ContentRem" style="width:40px;text-align:center">500</span></b>
                </font>&nbsp;&nbsp;
                <b><span id="checkmessage" style="color:red"></span></b>
            </td>
            <td align="right">
                <input type="checkbox" name="noname" value="yes">匿名发表
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea rows="12" cols="99" name="content" onpropertychange="checkLen()"></textarea>
            </td>
        </tr>
        <tr height="30">
            <td colspan="2" align="center">
                <input type="button" name="submitb" onclick="editformsubmit()" style="background:url('images/submit.jpg');width:97px;height:26px;border:0px">
                <input type="reset" value="" style="background:url('images/reset.jpg');width:97px;height:26px;border:0px">
            </td>
        </tr>
    </table>
</form>
