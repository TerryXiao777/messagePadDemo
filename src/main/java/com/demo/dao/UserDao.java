package com.demo.dao;

import com.demo.bean.UserBean;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private JDBConnection connection = null;

    public UserDao(){
        connection = new JDBConnection();
    }

    public UserBean getLoginer(String name, String pswd) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_userinfo where account = ? and password = ?");
        String[] params={name,pswd};
        connection = new JDBConnection();
        UserBean user = null;
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        if(rs!=null&&rs.next()){
            user = new UserBean();
            user.setId(rs.getInt(1));
            user.setAccount(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setAble(rs.getString(7));
            rs.close();
        }
        return user;
    }


}
