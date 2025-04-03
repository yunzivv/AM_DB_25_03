package org.example.test;

import org.example.dto.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectTest {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 드라이버 연결
            Class.forName("org.mariadb.jdbc.Driver");
            // 계정 연결
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");

            String sql = "SELECT id, title, `body` FROM article ORDER BY id DESC;";

            // sql 명령어를 전달한다.
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);

            // sql 명령어 전달 결과를 저장
            ResultSet rs = pstmt.executeQuery();

            // rs에 결과를 저장해도 자바에서는 결과값을 해석할 수 없다.
            // 자바가 읽을 수 있는 자료로 바꿔준다.
            List<Article> articles = new ArrayList<>();

            // rs.next() : 테이블의 튜플을 순회하며 다음 튜플이 있는 지 결과를 반환
            // article 필드는 rs의 결과값을 rs.getType()으로 가져온다.
            while (rs.next()) {

                int id = rs.getInt("id");
                String regDate = rs.getString("reg_date");
                String content = rs.getString("content");
                String title = rs.getString("title");
                String body = rs.getString("body");

                Article article = new Article(id, regDate, content, title, body);

                articles.add(article);
            }

            // article 객체를 저장한 articles 출력
            System.out.println(" No |  title | body ");
            System.out.println("--------------------");
            for(Article article : articles) {
                System.out.println(article.getId() + " | " + article.getTitle() + " | " + article.getBody());
            }


            // 몇개의 행에 적용 되었는 지 반환
            // select 실행은 적용 행이 없음
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
