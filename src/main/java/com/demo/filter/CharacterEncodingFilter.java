package com.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    private FilterConfig fc;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)sRequest;
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request,sResponse);
    }

    @Override
    public void destroy() {
        this.fc=null;
    }
}
