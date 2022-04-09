package com.demo.servlet;

import com.demo.bean.UserBean;
import com.demo.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String servletPath=request.getServletPath();
        if("/login".equals(servletPath)){
            login(request,response);
        }
        else if("/logout".equals(servletPath)){
            logout(request,response);
        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String message="";
        String forward="";

        String name=request.getParameter("name");
        String pswd=request.getParameter("pswd");

        try {
            UserBean loginer = new UserDao().getLoginer(name,pswd);
            if(loginer!=null){
                request.getSession().setAttribute("loginer",loginer);
                forward=request.getContextPath()+"/listnew";
                response.sendRedirect(forward);
            }
            else{
                message="<li>用户名或密码错误！</li><br>";
                message+="<a href='javascript:window.history.go(-1)'>返回重试</a>";
                forward="/fail.jsp";

                request.setAttribute("message",message);
                RequestDispatcher rd=request.getRequestDispatcher(forward);
                rd.forward(request,response);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/listnew");
    }
}
