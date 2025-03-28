package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCUpdateTest {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        Scanner sc = new Scanner(System.in);

        try {
            // 드라이버 연결
            Class.forName("org.mariadb.jdbc.Driver");
            // 계정 연결
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");

            System.out.print("수정할 게시물 번호 : ");
            int modifyNum = sc.nextInt();
            sc.nextLine();

            System.out.print("\nnew Title : ");
            String title = sc.nextLine();

            System.out.print("\nnew Body : ");
            String body = sc.nextLine();

            String sql = "update article";
            sql += " set updateDate = now(),";
            sql += " title = '" + title + "',";
            sql += " `body` = '" + body + "'";
            sql += " where id = " + modifyNum;

            // sql 명령어를 전달한다.
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);

            // sql 명령어 전달 결과를 저장
            ResultSet rs = pstmt.executeQuery();

            // rs에 결과를 저장해도 자바에서는 결과값을 해석할 수 없다.
            // 자바가 읽을 수 있는 자료로 바꿔준다.
            List<Article> articles = new ArrayList<>();


            // 몇개의 행에 적용 되었는 지 반환
            int affectedrows = pstmt.executeUpdate();
            System.out.println("rows : " + affectedrows);

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
}
