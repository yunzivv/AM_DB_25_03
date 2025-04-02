package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    // 반복문 내부에서 드라이버 연결
    // 연결 성공 시 doAction 메서드 호출
    public void run() {
        int lastId = 0;

        System.out.println("[프로그램 시작]");
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
                    System.out.println("[프로그램 종료]");
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

        MemberController memberController = new MemberController(sc, conn);
        ArticleController articleController = new ArticleController(sc, conn);

        if (cmd.equals("member join")) {

            memberController.join();

        } else if (cmd.equals("member login")) {

            memberController.login();

        } else if (cmd.equals("member logout")) {

            memberController.logout();

        } else if (cmd.equals("article write")) {

            articleController.doWrite(conn);

        } else if (cmd.equals("article list")) {

            articleController.showList(conn);

        } else if (cmd.startsWith("article modify")) {

            articleController.modify(conn, cmd);

        } else if (cmd.startsWith("article delete")) {

            articleController.doDelete(conn, cmd);

        } else if (cmd.startsWith("article detail")) {

            articleController.showDetail(conn, cmd);

        } else {

            System.out.println("지원하지 않는 기능입니다.");
        }

        return 0;
    }
}