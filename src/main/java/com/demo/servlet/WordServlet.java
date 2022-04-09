package com.demo.servlet;

import com.demo.bean.UserBean;
import com.demo.bean.WordBean;
import com.demo.dao.WordDao;
import com.demo.tools.StringHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class WordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String servletpath=request.getServletPath();
        if("/listnew".equals(servletpath)){
            listnew(request,response);
        }
        else if("/listmore".equals(servletpath)){
            listmore(request,response);
        }
        else if("/add".equals(servletpath)){
            add(request,response);
        }
        else if("/modifyview".equals(servletpath)){
            modifyview(request,response);
        }
        else if("/modifyrun".equals(servletpath)){
            modifyrun(request,response);
        }
        else if("/delete".equals(servletpath)){
            delete(request,response);
        }

    }

    private void listnew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("mainPage","/listnewword.jsp");
        try {
            List newlist=new WordDao().listnew();
            request.setAttribute("newlist",newlist);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd=request.getRequestDispatcher("/default.jsp");
        rd.forward(request,response);
    }
    private void listmore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("mainPage","/listmoreword.jsp");

        String field=request.getParameter("field");
        String way=request.getParameter("way");
        if(field==null||field.equals("")){
            field="word_posttime";
        }
        if(way==null||way.equals("")){
            way="asc";
        }
        String strcurrentP=request.getParameter("currentP");
        String strcurrentG=request.getParameter("currentG");
        String goWhich=request.getContextPath()+"/listmore?field="+field+"&way="+way;

        try {
            WordDao wordDao=new WordDao();
            List morelist=wordDao.listmore(field, way, strcurrentP, strcurrentG, goWhich);

            request.setAttribute("morelist",morelist);
            request.setAttribute("pagebar",wordDao.getDaoPage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd=request.getRequestDispatcher("/default.jsp");
        rd.forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String content=request.getParameter("content");
        String time= StringHandler.timeTostr(new Date());
        String author="";
        String noname=request.getParameter("noname");
        if("yes".equals(noname)){
            author="留言板网友";
        }
        else{
            author=((UserBean)request.getSession().getAttribute("loginer")).getAccount();
        }

        String[] params={content,author,time};

        int i=new WordDao().add(params);
        if(i==0){
            String message="●发表留言失败！";
            message+="<a href='javascript:window.history.go(-1)'></a>";

            RequestDispatcher rd=request.getRequestDispatcher("/fail.jsp");
            rd.forward(request,response);
        }
        else{
            response.sendRedirect(request.getContextPath()+"/listnew");
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String wordId=request.getParameter("wordId");
        int i=new WordDao().delete(wordId);
        if(i==0){
            String message="●删除留言失败！";
            message+="<a href='javascript:window.history.go(-1)'></a>";

            RequestDispatcher rd=request.getRequestDispatcher("/fail.jsp");
            rd.forward(request,response);
        }
        else{
            String autoforward=request.getHeader("referer");
            response.sendRedirect(autoforward);
        }
    }

    private void modifyview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("mainPage","modifyview.jsp");

        try {
            String wordId=request.getParameter("wordId");
            WordBean single = new WordDao().getSingle(wordId);
            request.setAttribute("single",single);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String autoforward=request.getHeader("referer");
        request.setAttribute("autoforward",autoforward);

        RequestDispatcher rd=request.getRequestDispatcher("/default.jsp");
        rd.forward(request,response);
    }

    private void modifyrun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String wordId=request.getParameter("wordId");
        String content=request.getParameter("content");
        String[] params={content,wordId};
        int i=new WordDao().modify(params);
        if(i==0){
            String message="●修改留言失败！";
            message+="<a href='javascript:window.history.go(-1)'></a>";

            RequestDispatcher rd=request.getRequestDispatcher("/fail.jsp");
            rd.forward(request,response);
        }
        else{
            String autoforward=request.getParameter("autoforward");
            if(autoforward==null||autoforward.equals("")){
                autoforward=request.getContextPath()+"/listnew";
            }
            response.sendRedirect(autoforward);
        }
    }
}
