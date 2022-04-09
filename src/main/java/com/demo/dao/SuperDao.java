package com.demo.dao;

import com.demo.tools.JDBConnection;
import com.demo.tools.PageBar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperDao {
    private PageBar daoPage=new PageBar();
    private int daoPerR=daoPage.getPerR();
    private int daoPerP=daoPage.getPerP();

    public void setDaoPerR(int daoPerR){
        this.daoPerR=daoPerR;
    }
    public void setDaoPerP(int daoPerP){
        this.daoPerP=daoPerP;
    }

    public void setDaoPage(String sql,String[] params,String strcurrentP,String strcurrentG,String goWhich) throws SQLException {
        daoPage.setAllR(getAllR(sql,params));
        daoPage.setPerR(daoPerR);
        daoPage.setPerP(daoPerP);
        daoPage.setPageBar(strcurrentP, strcurrentG, goWhich);
    }

    private int getAllR(String sql,String[] params) throws SQLException{
        int allR=0;
        JDBConnection connection = new JDBConnection();

        ResultSet rs=connection.queryByPsStatement(sql, params);
        if(rs!=null&&rs.next()){
            allR=rs.getInt(1);
            rs.close();
        }
        connection.closeAll();
        return allR;
    }

    public PageBar getDaoPage() {
        return daoPage;
    }
    public int getDaoPerR() {
        return daoPerR;
    }
    public int getDaoPerP() {
        return daoPerP;
    }
}
