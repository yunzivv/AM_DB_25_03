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

        // 클래스가 달라지면 다시 연결이 필요함
//        JDBCConnTest.main(String[] args);

        // 데이터베이스에 sql문을 전달, 결과값을 반환한다.
        // con과 세트
        PreparedStatement pstmt = null;

        // pstmt의 결과를 받아 저장하는 클래스
        ResultSet rs = null;

        while (true) {
            System.out.print("\ncmd :");
            String cmd = sc.nextLine();

            if(cmd.equals("exit")){
                break;
            }

            if(cmd.equals("article write")) {

                System.out.println("[write]");
                int id = ++lastArticleId;

                System.out.print("tile : ");
                String title = sc.nextLine();

                System.out.print("body : ");
                String content = sc.nextLine();

//                리스트에 article을 저장하지 않고 DB에 저장한다.
//                articles.add(new Article(id, title, content));


                System.out.printf("[%d article wrote]\n", lastArticleId);

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