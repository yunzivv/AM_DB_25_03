package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int lastId = 0;

        while (true) {
            System.out.print("\ncmd : ");
            String cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("article write")) {

                System.out.println("[write]");

                System.out.print("title : ");
                String title = sc.nextLine();

                System.out.print("body : ");
                String body = sc.nextLine();


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


                    System.out.printf("[article %d wrote]\n", ++lastId);


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

            } else if (cmd.equals("article list")) {

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    // 드라이버 연결
                    Class.forName("org.mariadb.jdbc.Driver");
                    // 계정 연결
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    String sql = "SELECT id, title, regDate FROM article ORDER BY id DESC;";

                    pstmt = conn.prepareStatement(sql);
                    System.out.println(sql);

                    ResultSet rs = pstmt.executeQuery();

                    System.out.println("[list]");

                    List<Article> articles = new ArrayList<>();

                    while (rs.next()) {

                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String regDate = rs.getString("regDate");

                        Article article = new Article(id, title, regDate);

                        articles.add(article);
                    }

                    // article 객체를 저장한 articles 출력
                    System.out.println(" No |  title  |  regDate  ");
                    System.out.println("--------------------");
                    for (Article article : articles) {
                        int id = article.getId();
                        String title = article.getTitle();
                        String regDate = article.getRegDate();

                        if (regDate.split(" ")[0].equals(LocalDate.now())) {
                            regDate = regDate.split(" ")[1];
                        } else {
                            regDate = regDate.split(" ")[0];
                        }
                        System.out.println(" " + id + " | " + title + " | " + regDate);
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

            } else if (cmd.equals("article modify")) {

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    // 드라이버 연결
                    Class.forName("org.mariadb.jdbc.Driver");
                    // 계정 연결
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    System.out.print("\n수정할 게시물 번호 : ");
                    int modifyNum = sc.nextInt();
                    sc.nextLine();

                    System.out.print("new Title : ");
                    String title = sc.nextLine();

                    System.out.print("new Body : ");
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
            } else if (cmd.equals("article delete")) {

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    // 드라이버 연결
                    Class.forName("org.mariadb.jdbc.Driver");
                    // 계정 연결
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");

                    System.out.print("\n삭제할 게시물 번호 : ");
                    int deleteNum = sc.nextInt();
                    sc.nextLine();

                    String sql = "delete FROM article";
                    sql += " WHERE id = " + deleteNum + ";";

                    // sql 명령어를 전달한다.
                    pstmt = conn.prepareStatement(sql);
                    System.out.println(sql);

                    // 몇개의 행에 적용 되었는 지 반환
                    int affectedrows = pstmt.executeUpdate();
                    System.out.println("rows : " + affectedrows);

                    System.out.printf("[article %d deleted]\n", deleteNum);

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
    }
}
