package com.demo.dao;

import com.demo.bean.WordBean;
import com.demo.tools.JDBConnection;
import com.demo.tools.StringHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDao extends SuperDao{
    private JDBConnection connection = null;

    public WordDao(){
        connection = new JDBConnection();
    }

    public WordBean getSingle(String wordId) throws SQLException {
        WordBean single=null;
        String sql="select * from t_word where id=?";
        String[] params={wordId};
        List list= getList(sql,params);
        if(list!=null&&list.size()!=0){
            single=(WordBean)list.get(0);
        }
        return single;
    }

    private List getList(String sql,String[] params) throws SQLException{
        List list=null;
        connection = new JDBConnection();
        ResultSet rs=connection.queryByPsStatement(sql,params);
        if(rs!=null){
            list=new ArrayList();
            while(rs.next()){
                WordBean single=new WordBean();
                single.setId(rs.getInt(1));
                single.setContent(rs.getString(2));
                single.setAuthor(rs.getString(3));
                single.setPostTime(StringHandler.timeTostr(rs.getTimestamp(4)));
                list.add(single);
            }
            rs.close();
        }
        connection.closeAll();
        return list;
    }

    public List listnew() throws SQLException{
        String sql="select * from t_word order by post_time desc limit 0,10";
        List newlist=getList(sql,null);
        return newlist;
    }

    public List listmore(String field,String way,String strcurrentP,String strcurrentG,String goWhich) throws SQLException{
        String sqlall="select count(id) from tb_word";

        setDaoPerR(3);
        setDaoPerP(5);
        setDaoPage(sqlall,null,strcurrentP, strcurrentG, goWhich);

        int currentP=getDaoPage().getCurrentP();
        int top1=getDaoPage().getPerR();
        int top2=(currentP-1)*top1;
        System.out.println("top1:" + top1);
        System.out.println("top2:" + top2);
        String sqlsub="";
        if("post_time".equals(field)){
            if("desc".equals(way)){
                if(currentP==1){
                    sqlsub="select * from t_word order by post_time desc";
                }
                else{
                    sqlsub="select top "+top1+" * from t_word where (post_time < (select min(post_time) from (select top "+top2+" * from t_word order by post_time desc) as minv)) order by post_time desc";
                }

            }
            else{
                if(currentP==1){
                    sqlsub="select top "+top1+" * from t_word order by post_time";
                }
                else{
                    sqlsub="select top "+top1+" * from t_word where (post_time>(select max(post_time) from (select top "+top2+" * from tb_word order by post_time) as maxv)) order by post_time";
                }
            }
        }
        else if("author".equals(field)){
            if("desc".equals(way)){
                if(currentP==1){
                    sqlsub="select top "+top1+" * from t_word order by author desc,post_time desc";
                }
                else{
                    sqlsub="select * from (select top "+(currentP)*top1+" * from t_word order by author desc,post_time desc) as a where id not in(select top "+top2+" id from t_word order by author desc,post_time desc)";
                }
            }
            else{
                if(currentP==1){
                    sqlsub="select top "+top1+" * from t_word order by author,post_time";
                }

                else{
                    sqlsub="select * from (select top "+(currentP)*top1+" * from t_word order by author,post_time) temp where temp.id not in(select top "+top2+" id from t_word order by author,post_time)";
                }

            }
        }

        List wordlist=getList(sqlsub,null);
        return wordlist;
    }

    public int add(String[] params){
        String sql="insert into tb_word values(?,?,?)";
        return update(sql, params);
    }
    public int modify(String[] params){
        String sql="update tb_word set word_content=? where id=?";
        return update(sql,params);
    }
    public int delete(String wordId){
        String sql="delete from tb_word where id=?";
        String[] params={wordId};
        return update(sql,params);
    }
    private int update(String sql,String[] params){
        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql,params);
        int i=0;
        if(flag){
            i = 1;
        }
        connection.closeAll();
        return i;
    }
}
