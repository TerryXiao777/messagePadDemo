<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% String contextPath = request.getContextPath();%>
<html>
<head>
    <title>显示所有留言</title>

</head>
<body>
<table border="0" width="90%" style="word-break:break-all;margin-top:3px" cellpadding="2" cellspacing="0">
    <tr height="50" valign="bottom">
        <td width="10%"><img src="images/moretitle.jpg" height="28"></td>
        <form action="listmore" method="post">
            <td align="right">
                按
                <select name="field">
                    <c:if test="${param.field eq 'author'}">
                        <option value="word_posttime">发表时间</option>
                        <option value="word_author" selected>作者</option>
                    </c:if>

                    <c:if test="${param.field ne 'author'}">
                        <option value="word_posttime" selected>发表时间</option>
                        <option value="word_author">作者</option>
                    </c:if>
                </select>
                <select name="way">
                    <c:if test="${param.way eq 'desc'}">
                        <option value="desc" selected>降序</option>
                        <option value="asc">升序</option>
                    </c:if>

                    <c:if test="${param.way ne 'desc'}">
                        <option value="desc">降序</option>
                        <option value="asc" selected>升序</option>
                    </c:if>
                </select>
                <input type="submit" value="显示">
            </td>
        </form>
    </tr>
</table>

<c:set var="words" value="${requestScope.morelist}"/>
<c:if test="${!empty words}">
    <c:forEach var="word" varStatus="wvs" items="${words}">
        <c:if test="${wvs.index%2==0}"><c:set var="bgc" value="#F5FFE8"/></c:if>
        <c:if test="${wvs.index%2!=0}"><c:set var="bgc" value="#F7FAF4"/></c:if>
        <table bgcolor="#ACCE69" border="0" width="90%" style="word-break:break-all;margin-top:3px" cellpadding="8" cellspacing="1">
            <tr bgcolor="${bgc}">
                <td>
                    <div style="margin-top:0">☆ <b><u>${word.author}</u></b></div>
                    <div style="margin-top:-20px;color:gray" align="right">${word.post_time}</div>
                    <hr color="black">
                        ${word.content}
                    <br><br>

                    <div align="right">
                        <a href="#addword">发表留言</a>&nbsp;&nbsp;|&nbsp;
                        <a href="<%=contextPath%>/modifyview?wordId=${word.id}">修改留言</a>&nbsp;|&nbsp;
                        <a href="<%=contextPath%>/delete?wordId=${word.id}">删除留言</a>
                    </div>
                </td>
            </tr>
        </table>
    </c:forEach>
    <table border="0" width="90%" cellpadding="0" cellspacing="0">
        <tr><td><c:out value="${requestScope.pagebar.pageLink}" escapeXml="false"/></td></tr>
    </table>
</c:if>

<c:if test="${empty words}">没有留言可显示！</c:if>

<%@include file="/addword.jsp" %>
</body>
</html>
