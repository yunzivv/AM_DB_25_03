package org.example;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    // 반복문 내부에서 드라이버 연결
    // 연결 성공 시 doAction 메서드 호출
    public void run() {
        int lastId = 0;

        System.out.println("프로그램 시작");
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("\ncmd : ");
            String cmd = sc.nextLine().trim();

            Connection conn = null;

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                System.out.println("연결 성공!");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            try {
                conn = DriverManager.getConnection(url, "root", "");
                int actionResult = doAction(conn, sc, cmd);

                if (actionResult == -1) {
                    System.out.println("프로그램 종료");
                    sc.close();
                    break;
                }

            }catch (SQLException e) {
                System.out.println("에러 1 : " + e);
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

    // 동작 메서드
    private int doAction(Connection conn, Scanner sc, String cmd) {

        if(cmd.equals("exit")) {
            return -1;
        }

        if (cmd.equals("article write")) {

            System.out.println("[write]");

            System.out.print("title : ");
            String title = sc.nextLine();

            System.out.print("body : ");
            String body = sc.nextLine();

            // SecSql은 가변인자를 매개변수로 받기 때문에
            // 오버라이딩 또는
            SecSql sql = new SecSql();
            sql.append("INSERT INTO article");
            sql.append("sql += SET regDate = NOW(),");
            sql.append("updateDate = NOW(),");
            // 물음표로 작성하면 알아서 치환이 된다.
            sql.append("title = ?," + title);
            sql.append("`body` = ?;" + body);

            int id = DBUtil.insert(conn, sql);

            System.out.printf("[article %d wrote]\n", id);


        } else if (cmd.equals("article list")) {

            System.out.println("[list]");

            List<Article> articles = new ArrayList<>();

            SecSql sql = new SecSql();
            // sql.append("SELECT *");
            // sql.append("FROM article");
            // sql.append("ORDER BY id DESC;");
            sql.append("SELECT id, title, regDate FROM article ORDER BY id DESC;");

            List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);

            for (Map<String, Object> articleMap : articleListMap) {
                articles.add(new Article(articleMap));
            }

            if(articles.size() == 0) {
                System.out.println("[No article]");
                return 0;
            }


            System.out.println(" No |  title  |  regDate  ");
            System.out.println("--------------------");

            for (Article article : articles) {
                int id = article.getId();
                String title = article.getTitle();
                String regDate = article.getRegDate();
                String nowDate = LocalDate.now().toString();

                // 작성일자가 오늘이라면 시간, 오늘 이전이라면 날짜로 출력
                if (regDate.split(" ")[0].equals(LocalDate.now().toString())) {
                    regDate = regDate.split(" ")[1].substring(0, 8);
                } else {
                    regDate = regDate.split(" ")[0];
                }
                System.out.println(" " + id + " | " + title + " | " + regDate);
            }


        } else if (cmd.startsWith("article modify")) {

            int modifyId = 0;

            try {
                modifyId = Integer.parseInt(cmd.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("Enter aritlce Number to decimal");
                return 0;
            }

            // 수정할 article이 존재하는 지 여부 확인
            int modifyIdCount = -1;

            SecSql sql = new SecSql();
            sql.append("SELECT COUNT(*) FROM article WHERE id = " + modifyId);


            if (modifyIdCount == 0) {
                System.out.printf("article %d is not exist\n", modifyId);
                return 0;
            }

            System.out.print("new Title : ");
            String title = sc.nextLine().trim();

            System.out.print("new Body : ");
            String body = sc.nextLine().trim();

            PreparedStatement pstmt = null;

            try {
                sql.append("UPDATE article");
                if (title.length() > 0) {
                    sql.append("SET title = ?," + title);
                }
                if (body.length() > 0) {
                    sql.append("`body` = ?;" + body);
                }
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();

            }catch (SQLException e) {
                System.out.println("에러 4" + e);
            } finally {
                try {
                    if(pstmt != null && !pstmt.isClosed()) {
                        pstmt.close();
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("article %d is modified", modifyId);

        } else if (cmd.startsWith("article delete")) {

            // 수정
            int deleteId = -1;

            try {
                deleteId = Integer.parseInt(cmd.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("Enter aritlce Number");
                return 0;
            }

            String sql = "select count(*) from article where id = " + deleteId + ";";

            int deleteIdCount = -1;


            if (deleteIdCount == 0) {
                System.out.printf("article %d is not exist\n", deleteId);
                return 0;
            }

            sql = "delete FROM article";
            sql += " WHERE id = " + deleteId + ";";

            System.out.printf("[article %d deleted]\n", deleteId);

            return 0;
        }

    }
}