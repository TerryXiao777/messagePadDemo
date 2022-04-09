<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.bean.UserBean" %>
<%	String contextPath = request.getContextPath();%>

<table width="1004px" height="35px" border="0" cellpadding="5" cellspacing="0">
    <tr bgcolor="#F5F5F5">
        <td style="padding-left:20px;background:url(images/menu.jpg)">
            <b>
                <%
                    Object ob=session.getAttribute("loginer");
                    if(ob!=null && ob instanceof UserBean){
                        showmenu="欢迎(<font color='red'>"+((UserBean)ob).getAccount()+"</font>)访问！&nbsp;|&nbsp;&nbsp";
                        showmenu+="<a href='"+contextPath+"/logout'>注销</a>&nbsp;&nbsp;|&nbsp;&nbsp;";
                    }
                    else{
                        showmenu="欢迎(<font color='red'>游客</font>)访问！&nbsp;|&nbsp;&nbsp";
                        showmenu+="<a href='login.jsp'>登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;";
                    }
                %>
                <%=showmenu%>
                <a href="#addword">发表留言</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="<%=contextPath%>/listnew">最新留言</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="<%=contextPath%>/listmore">所有留言</a>
            </b>
        </td>
    </tr>
</table>

