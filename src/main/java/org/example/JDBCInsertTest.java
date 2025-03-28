package org.example;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCInsertTest {

    public static void main(String[] args) {

        // 클래스가 달라지면 다시 연결이 필요함
        Connection conn = null;

        // 데이터베이스에 sql문을 전달, 결과값을 반환한다.
        // con과 세트
        PreparedStatement pstmt = null;

        // pstmt의 결과를 받아 저장하는 클래스
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);

        try {
            // 드라이버 연결
            Class.forName("org.mariadb.jdbc.Driver");
            // 계정 연결
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");


            System.out.println("[write]");

                    System.out.print("tile : ");
                    String title = sc.nextLine();

                    System.out.print("body : ");
                    String body = sc.nextLine();

                    String sql = "INSERT INTO article SET ";
                    sql += "rgDate = NOW()";
                    sql += "updateDate = NOW(), ";
                    sql += "title = " + title;
                    sql += "`body` = " + body + ";";

                    try {
                        pstmt = conn.prepareStatement(sql);
                        int res = pstmt.executeUpdate(sql);

                        if (res > 0) {
                            System.out.println("[write success]");
                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    } finally {

                        try {
                            pstmt.close();
                            conn.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

            sql = "INSERT INTO article ";
            sql += "SET reDate = NOW(), ";
            sql += "updateDate = NOW(), ";
            sql += "title = CONCAT('제목', SUBSTRING(RAND() *1000 FROM 1 FOR 2)), ";
            sql += "`body` = CONCAT('내용', SUBSTRING(RAND() *1000 FROM 1 FOR 2));";


            // sql 명령어를 전달한다.
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);

            // 몇개의 행에 적용 되었는 지 반환
            int affectedrows = pstmt.executeUpdate();
            System.out.println("rows : " + affectedrows);

//            while (true) {
//                System.out.print("\ncmd : ");
//                String cmd = sc.nextLine();
//
//                if (cmd.equals("exit")) {
//                    break;
//                }
//
//                if (cmd.equals("article write")) {
//
//                    System.out.println("[write]");
//
//                    System.out.print("tile : ");
//                    String title = sc.nextLine();
//
//                    System.out.print("body : ");
//                    String body = sc.nextLine();
//
//                    String sql = "INSERT INTO article SET ";
//                    sql += "rgDate = NOW()";
//                    sql += "updateDate = NOW(), ";
//                    sql += "title = " + title;
//                    sql += "`body` = " + body + ";";
//
//                    try {
//                        pstmt = conn.prepareStatement(sql);
//                        int res = pstmt.executeUpdate(sql);
//
//                        if (res > 0) {
//                            System.out.println("[write success]");
//                            articles.add(new Article(id, title, content));
//                        }
//
//                    } catch (Exception e) {
//
//                        System.out.println(e.getMessage());
//
//                    } finally {
//
//                        try {
//                            stmt.close();
//                            conn.close();
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//
//                } else if (cmd.equals("article list")) {
//
//                    System.out.println("[list]");
//
//                    if (articles.isEmpty()) {
//                        System.out.println("[list is empty]");
//                        continue;
//                    }
//
//                    System.out.println(" No |  title ");
//                    System.out.println("-------------");
//
//                    for (int i = articles.size() - 1; i >= 0; i--) {
//                        Article article = articles.get(i);
//                        System.out.printf(" %d  |  %s \n", article.getId(), article.getTitle());
//                    }
//
//                }
//            }
//            System.out.println("[Program finished]");
//            sc.close();

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
