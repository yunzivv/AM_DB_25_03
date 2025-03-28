package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Article> articles = new ArrayList<>();
        int lastArticleId = 0;

        while (true) {
            System.out.print("\ncmd : ");
            String cmd = sc.nextLine();

            if(cmd.equals("exit")){
                break;
            }

            if(cmd.equals("article write")) {

                System.out.println("[write]");
                int id = ++lastArticleId;

                System.out.print("title : ");
                String title = sc.nextLine();

                System.out.print("body : ");
                String body = sc.nextLine();

//                리스트에 article을 저장하지 않고 DB에 저장한다.
//                articles.add(new Article(id, title, content));

//                String sql = "INSERT INTO article SET ";
//                sql += "reDate = NOW()";
//                sql += "updateDate = NOW(), ";
//                sql += "title = '" + title + "', ";
//                sql += "`body` = '" + body + "';";

                System.out.printf("[%d article wrote]\n", lastArticleId);

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    // 드라이버 연결
                    Class.forName("org.mariadb.jdbc.Driver");
                    // 계정 연결
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "INSERT INTO article ";
                    sql += "SET regDate = NOW(), ";
                    sql += "updateDate = NOW(), ";
                    sql += "title = '" + title + "', ";
                    sql += "`body` = '" + body + "';";

                    // sql 명령어를 전달한다.
                    pstmt = conn.prepareStatement(sql);
                    System.out.println(sql);

                    // 몇개의 행에 적용 되었는 지 반환
                    int affectedrows = pstmt.executeUpdate();
                    System.out.println("sql affecte rows : " + affectedrows);


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

            } else if(cmd.equals("article list")) {

                System.out.println("[list]");

                if(articles.isEmpty()){
                    System.out.println("[list empty]");
                    continue;
                }

                System.out.println(" No |  title ");
                System.out.println("-------------");


                for(int i = articles.size() - 1; i >= 0; i--){
                    Article article = articles.get(i);
                    System.out.printf(" %d  |  %s \n", article.getId(), article.getTitle());
                }

            }
        }
    }
}