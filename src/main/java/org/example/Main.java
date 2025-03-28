package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("[Program Started]");

        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;
        List<Article> articles = new ArrayList<>();

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

                articles.add(new Article(id, title, content));
                System.out.println("[write success]");

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

        System.out.println("[Program finished]");
        sc.close();
    }
}