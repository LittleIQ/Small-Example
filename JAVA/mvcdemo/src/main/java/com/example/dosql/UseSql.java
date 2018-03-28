package com.example.dosql;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.Vector;

import com.mysql.jdbc.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class UseSql {
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "";
    private String dbUser = "root";
    private String dbPass = "980528nyx";

    public UseSql(String u) {
        this.dbUrl = "jdbc:mysql://localhost:3306/" + u + "?useUnicode=true&characterEncoding=UTF-8";
    }

    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);//注意是三个参数
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Vector read(String sql, int num) {
        Vector data = new Vector();
        Connection conn = getConn();//此处为通过自己写的方法getConn()获得连接
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int sign = 0;
            while(rs.next())
            {
                sign = 1;
                String all="";
                Vector the = new Vector();
                for(int i = 1;i<=num;i++)
                    the.add(rs.getString(i));
                data.add(the);
            }
            if(sign==0){
                data=null;
            }
            //可以将查找到的值写入类，然后返回相应的对象
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public int insert(String sql, String[] data) {
        int i = 0;
        Connection cnn = getConn();
        try {
            PreparedStatement preStmt = cnn.prepareStatement(sql);
            for (int j = 1; j <= data.length; j++) {
                preStmt.setString(j, data[j-1]);
            }
            i = preStmt.executeUpdate();
            cnn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

//    public int update(String sql, String[] data) {
//        int i = 0;
//        Connection cnn = getConn();
//        try {
//            PreparedStatement preStmt = cnn.prepareStatement(sql);
//            for (int j = 1; j <= data.length; j++) {
//                preStmt.setString(j, data[j-1]);
//            }
//            i = preStmt.executeUpdate();
//            cnn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return i;
//    }

    public int number(String name) {
        int num = 0;
        Connection conn = getConn();//此处为通过自己写的方法getConn()获得连接
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(1) from "+name);
            if(rs.next())
            {
                num = Integer.parseInt(rs.getString(1));
            }
            //可以将查找到的值写入类，然后返回相应的对象
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return num;
    }
}
