package com.demo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHandler {
    public static Integer strToint(String str){
        if(str!=null&&!str.equals("")){
            try{
                return Integer.parseInt(str);
            }catch(NumberFormatException e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String timeTostr(Date date){
        String strDate="";
        if(date!=null){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            strDate=format.format(date);
        }
        return strDate;
    }
    public static String changehtml(String str){
        String change="";
        if(str!=null&&!str.equals("")){
            change=str.replace("&","&amp;")
                    .replace(" ","&nbsp;")
                    .replace("<","&lt;")
                    .replace(">","&gt;")
                    .replace("\"","&quot;")
                    .replace("\r\n","<br>");
        }
        return change;
    }
}
