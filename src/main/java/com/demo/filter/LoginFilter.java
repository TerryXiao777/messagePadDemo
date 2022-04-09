package com.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private FilterConfig fc;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)sRequest;
        HttpSession session=request.getSession();
        Object loginer=session.getAttribute("loginer");

        String servletPath=request.getServletPath();
        if("/add".equals(servletPath)){
            if("yes".equals(request.getParameter("noname")))
                chain.doFilter(sRequest,sResponse);
            else{
                if(loginer==null && !(loginer instanceof com.demo.bean.UserBean)){
                    RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
                    rd.forward(request,sResponse);
                }
                else{
                    chain.doFilter(sRequest,sResponse);
                }

            }
        }
        else{
            if(loginer==null && !(loginer instanceof com.demo.bean.UserBean)){
                RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
                rd.forward(request,sResponse);
            }
            else{
                chain.doFilter(sRequest,sResponse);
            }

        }
    }

    @Override
    public void destroy() {
        this.fc=null;
    }
}
