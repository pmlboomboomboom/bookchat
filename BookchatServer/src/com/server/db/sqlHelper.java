package com.server.db;

import java.sql.*;
import java.util.*;

import com.common.*;
/*
 * 1 public static boolean checkLogin(String name, String password)验证用户名信息 
 * 2 public static boolean register(String name, String password)验证是否注册成功 
 */
public class sqlHelper {
    //第一个是数据库的IP地址、端口和名称； 第二个参数是数据库的用户名； 第三个参数时数据库的密码
    private static String[] connectionParameters = { "jdbc:mysql://localhost:3306/test","root","dear_zz520"};
    
    //登陆检测
    public static boolean checkLogin(String name, String password)
    {
        Connection conn = null;//声明数据库连接对象
        Statement stmt = null; //声明数据库表达式
        ResultSet rs = null; //声明结果集对象
        try {
            //载入mysql的驱动字符串
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接
            conn = DriverManager.getConnection(connectionParameters[0], connectionParameters[1], connectionParameters[2]);
            //获取表达式对象实例
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE name='"+name+"' AND password = '"+password+"'");
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //注册
    public static boolean register(String name, String password)
    {
        Connection conn = null;//声明数据库连接对象
        Statement stmt = null; //声明数据库表达式
        ResultSet rs = null; //声明结果集对象
        try {
          //载入mysql的驱动字符串
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接
            conn = DriverManager.getConnection(connectionParameters[0], connectionParameters[1], connectionParameters[2]);
            //获取表达式对象实例
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE name='"+name+"'");
            //如果存在该用户，则返回false,不存在则注册
            if(rs.next()) {
                conn.close();
                return false;
            } else {
                //返回1就代表更新了记录
                int i = stmt.executeUpdate("INSERT INTO users (name, password) VAlUES ('"+name+"', '"+password+"')");
                
                if (i == 1) return true;
                else {
                    conn.close();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
