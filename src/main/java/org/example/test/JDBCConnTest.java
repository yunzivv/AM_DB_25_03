package org.example.test;

import java.sql.*;

public class JDBCConnTest {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            // 드라이버 연결
            Class.forName("org.mariadb.jdbc.Driver");
            // 계정 연결
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {

        return null;
    }
}