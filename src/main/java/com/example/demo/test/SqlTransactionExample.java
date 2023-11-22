package com.example.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlTransactionExample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 1. 获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "user", "password");

            // 2. 开启事务
            conn.setAutoCommit(false);

            // 3. 执行操作
            String sql = "INSERT INTO mytable (column1, column2) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "value1");
            pstmt.setString(2, "value2");
            pstmt.executeUpdate();

            // 4. 判断条件（这里模拟一个异常条件）
            int i = Integer.parseInt("not a number"); // 这将引发一个异常
        } catch (SQLException e) {
            // 5. 发生异常，进行回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
