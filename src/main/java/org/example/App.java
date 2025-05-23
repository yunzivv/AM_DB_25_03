package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.MemberController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    private Scanner sc;

    public App() {
        Container.init();
        this.sc = Container.sc;
    }

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

                Container.conn = conn;

                int actionResult = doAction(cmd);

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
    private int doAction(String cmd) {

        if (cmd.equals("exit")) {
            return -1;
        }

        MemberController memberController = Container.memberController;
        ArticleController articleController = Container.articleController;

        if (cmd.equals("member join")) {

            memberController.join();

        } else if (cmd.equals("member login")) {

            memberController.login();

        } else if (cmd.equals("member logout")) {

            memberController.logout();

        } else if (cmd.equals("article write")) {

            articleController.doWrite();

        } else if (cmd.startsWith("article list")) {

            articleController.showList(cmd);

        } else if (cmd.startsWith("article modify")) {

            articleController.modify(cmd);

        } else if (cmd.startsWith("article delete")) {

            articleController.doDelete(cmd);

        } else if (cmd.startsWith("article detail")) {

            articleController.showDetail(cmd);

        } else {

            System.out.println("지원하지 않는 기능입니다.");
        }

        return 0;
    }
}