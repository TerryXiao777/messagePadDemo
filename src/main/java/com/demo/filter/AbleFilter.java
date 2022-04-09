package com.demo.filter;

import com.demo.bean.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AbleFilter implements Filter {
    private FilterConfig fc;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行AbleFilter过滤器！");

        HttpServletRequest request=(HttpServletRequest)sRequest;
        HttpSession session=request.getSession();
        UserBean loginer=(UserBean)session.getAttribute("loginer");

        boolean mark=validateAble(loginer);
        if(mark){
            chain.doFilter(sRequest,sResponse);
            System.out.println("返回AbleFilter");
        }
        else{
            String message="<li>您没有权限进行该操作！</li>";
            message+="<a href='javascript:window.history.go(-1)'>返回</a>";
            request.setAttribute("message",message);
            RequestDispatcher rd=request.getRequestDispatcher("/fail.jsp");
            rd.forward(request,sResponse);
        }
    }

    private boolean validateAble(UserBean loginer){
        boolean mark=false;
        if("1".equals(loginer.getAble())){
            mark=true;
        }
        return mark;
    }

    @Override
    public void destroy() {
        this.fc=null;
    }
}
