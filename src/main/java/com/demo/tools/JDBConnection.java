package com.demo.tools;

import java.sql.*;

public class JDBConnection {
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/testdb?useUnicode&characterEncoding=utf-8&useSSL=true&rewriteBatchedStatements=true";
    private final String userName = "root";
    private final String password = "root";
    private ResultSet rs = null;
    private Statement stmt = null;
    private Connection con = null;
    private PreparedStatement preparedStatement  = null;

    public JDBConnection() {
        try {
            Class.forName(dbDriver).newInstance();
        }
        catch (Exception ex) {
            System.out.println("数据库加载失败");
        }
    }


    private boolean creatConnection() {
        try {
            con = DriverManager.getConnection(url, userName, password);
            con.setAutoCommit(true);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private void createPsStatement(String sql) {
        creatConnection();
        try {
            System.out.println("创建PrepareStatement通道对象。。。");
            preparedStatement  = con.prepareStatement(sql);
        }
        catch (SQLException e) {
            System.out.println("创建PrepareStatement通道对象失败。。。");
            e.printStackTrace();
        }
    }

    private  void bundle(String[] parm) {
        //判断数组Parm是否为空
        if (parm != null) {
            //通过for循环将参数绑定起来
            for (int i = 0; i < parm.length; i++) {
                try {
                    System.out.println( "进行参数的绑定。。。");
                    preparedStatement.setString(i + 1, parm[i]);
                }
                catch (SQLException e) {
                    System.out.println("绑定参数失败。。。");
                    e.printStackTrace();
                }
            }
        }
    }

    public ResultSet queryByPsStatement(String sql,String[] pram) {
        createPsStatement(sql);
        bundle(pram);
        try {
            System.out.println("采用PrepareStatement方法执行sql查询语句。。。");
            rs = preparedStatement .executeQuery();
        }
        catch (SQLException e) {
            System.out.println("采用PrepareStatement方法执行sql查询语句失败");
            e.printStackTrace();
        }
        return rs;
    }

    public Boolean updateData(String sql,String[] parm) {
        //创建通道
        createPsStatement(sql);
        //绑定参数
        bundle(parm);
        int row = 0;
        try {
            System.out.println("修改数据中。。。");
            row = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("修改数据失败。。。");
            e.printStackTrace();
        }
        boolean res = false;
        if (row > 0) {
            res = true;
        }
        return res;
    }

    public void closeAll() {
        if (rs != null) {
            try {
                System.out.println("关闭resultSet。。。");
                rs.close();
            } catch (SQLException e) {
                System.out.println("关闭resultSet异常。。。");
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                System.out.println("关闭statement。。。");
                stmt.close();
            } catch (SQLException e) {
                System.out.println("关闭statement异常。。。");
                e.printStackTrace();
            }
        }
        if (preparedStatement  != null) {
            try {
                System.out.println("关闭preparestatement。。。");
                preparedStatement .close();
            } catch (SQLException e) {
                System.out.println("关闭preparestatement异常。。。");
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                System.out.println("关闭connection。。。");
                con.close();
            } catch (SQLException e) {
                System.out.println("关闭connection异常。。。");
                e.printStackTrace();
            }
        }
    }
}
