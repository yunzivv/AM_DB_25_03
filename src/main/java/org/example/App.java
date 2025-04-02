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

            } catch (SQLException e) {
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

        if (cmd.equals("exit")) {
            return -1;
        }

        if (cmd.startsWith("member join")) {

            System.out.println("[member join]");
            String loginId;
            String loginPw;
            String name;

            while(true) {
                System.out.print("Id : ");
                loginId = sc.nextLine().trim();

                // 입력받은 loginId 형식 확인(입력되지 않았거나 공백을 포함하는 지)
                if (loginId.isEmpty() || loginId.contains(" ")) {
                    System.out.println("올바르지 않은 형식의 ID입니다.");
                    continue;
                }

                // 입력받은 loginId 존재 여부 확인
                SecSql sql = new SecSql();
                sql.append("select count(*) > 0");
                sql.append("from `member`");
                sql.append("where loginId = ?;", loginId);

                boolean isLoginId = DBUtil.selectRowBooleanValue(conn, sql);

                if (isLoginId) {
                    System.out.printf("%s는 이미 사용중인 ID입니다.\n", loginId);
                    continue;
                }

                break;
            }

            while (true) {
                System.out.print("Password : ");
                loginPw = sc.nextLine().trim();
                System.out.print("Password Check : ");
                String loginPwCheck = sc.nextLine().trim();

                if (loginPw.isEmpty() || loginPw.contains(" ")) {
                    System.out.println("올바르지 않은 형식의 Password입니다.");
                    continue;
                }
                if(!loginPw.equals(loginPwCheck)) {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Name : ");
                name = sc.nextLine();
                if (name.isEmpty() || name.contains(" ")) {
                    System.out.println("올바르지 않은 형식의 Name입니다.");
                    continue;
                }
                break;
            }

            SecSql sql = new SecSql();
            sql.append("insert into `member`");
            sql.append("set regDate = NOW(),");
            sql.append("updateDate = NOW(),");
            sql.append("loginId = ?,", loginId);
            sql.append("loginPw = ?,", loginPw);
            sql.append("`name` = ?;", name);

            // 여기 수정
            int id = DBUtil.insert(conn, sql);
            System.out.printf("%s님 회원가입을 축하합니다.", name);

        } else if (cmd.startsWith("member login")) {

        }


        if (cmd.equals("article write")) {

            System.out.println("[write]");

            System.out.print("title : ");
            String title = sc.nextLine();

            System.out.print("body : ");
            String body = sc.nextLine();

            // SecSql은 가변인자를 매개변수로 받음
            SecSql sql = new SecSql();
            sql.append("INSERT INTO article");
            sql.append("SET regDate = NOW(),");
            sql.append("updateDate = NOW(),");
            // 물음표로 작성하면 알아서 치환이 된다.
            sql.append("title = ?,", title);
            sql.append("`body` = ?;", body);

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

            if (articles.size() == 0) {
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

            int modifyId;

            try {
                modifyId = Integer.parseInt(cmd.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("Enter aritlce Number to decimal");
                return 0;
            }

            // 수정할 article의 존재 여부 확인
            SecSql sql = new SecSql();
            sql.append("SELECT *");
            sql.append("FROM article");
            sql.append("WHERE id = ?;", modifyId);

            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            if (articleMap.isEmpty()) {
                System.out.printf("article %d is not exist\n", modifyId);
                return 0;
            }

            System.out.println("[modify]");
            System.out.print("new Title : ");
            String title = sc.nextLine().trim();

            System.out.print("new Body : ");
            String body = sc.nextLine().trim();

            PreparedStatement pstmt = null;

            sql = new SecSql();
            sql.append("UPDATE article");
            sql.append("SET updateDate = NOW()");
            if (title.length() > 0) {
                sql.append(", title = ?", title);
            }
            if (body.length() > 0) {
                sql.append(", `body` = ?", body);
            }
            sql.append("WHERE id = ?;", modifyId);

            DBUtil.update(conn, sql);

            System.out.printf("article %d is modified", modifyId);

        } else if (cmd.startsWith("article delete")) {

            int deleteId;

            try {
                deleteId = Integer.parseInt(cmd.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("Enter aritlce Number to decimal");
                deleteId = sc.nextInt();
                sc.nextLine();
            }

            SecSql sql = new SecSql();
            sql.append("select count(*)");
            sql.append("from article");
            sql.append("where id = ?;", deleteId);

            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            if (articleMap.isEmpty()) {
                System.out.printf("article %d is not exist\n", deleteId);
                return 0;
            }

            sql = new SecSql();
            sql.append("delete FROM article");
            sql.append("WHERE id = ?;", deleteId);

            DBUtil.update(conn, sql);

            System.out.printf("[article %d deleted]\n", deleteId);


        } else if (cmd.startsWith("article detail")) {

            int detailId;

            try {
                detailId = Integer.parseInt(cmd.split(" ")[2]);
            } catch (Exception e) {
                System.out.println("Enter aritlce Number to decimal");
                detailId = sc.nextInt();
                sc.nextLine();
            }

            SecSql sql = new SecSql();
            sql.append("select * from article");
            sql.append("where id = ?;", detailId);

            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            if (articleMap.isEmpty()) {
                System.out.printf("article %d is not exist\n", detailId);
                return 0;
            }

            Article article = new Article(articleMap);
            System.out.println("[detail]");
            System.out.println("작성일자 : " + article.getRegDate());
            System.out.println("수정일자 : " + article.getUpdateDate());
            System.out.println("제목 : " + article.getTitle());
            System.out.println("내용 : " + article.getBody());

        }


        return 0;
    }
}