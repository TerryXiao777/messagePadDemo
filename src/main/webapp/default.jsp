<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String mainPage=(String)request.getAttribute("mainPage");
    if(mainPage==null||mainPage.equals(""))
        mainPage="fail.jsp";
    String showmenu="";
%>
<html>
<title></title>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<center>
    <table width="1004px" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" colspan="2">
                <%@ include file="top.jsp" %>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <%@ include file="menu.jsp"%>
            </td>
        </tr>
        <tr>
            <td valign="top" style="padding-top:20px" align="right">
                <img src="images/ad.jpg"><br><br>
                <img src="images/ad.jpg"><br><br>
                <img src="images/ad.jpg"><br><br>
            </td>
            <td align="center" valign="top" width="78%">
                <jsp:include page="<%=mainPage%>"/>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <%@ include file="end.jsp" %>
            </td>
        </tr>
    </table>
</center>
</body>
</html>
