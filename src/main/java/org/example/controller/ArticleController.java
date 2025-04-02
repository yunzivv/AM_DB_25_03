package org.example.controller;

import org.example.service.ArticleService;

import java.sql.Connection;
import java.util.Scanner;

public class ArticleController {

    Scanner sc;
    ArticleService articleService;

    public ArticleController(Scanner sc, Connection conn) {
        this.sc = sc;
        articleService = new ArticleService();
    }

    public void doWrite(Connection conn) {

        System.out.println("[write]");

        System.out.print("title : ");
        String title = sc.nextLine();

        System.out.print("body : ");
        String body = sc.nextLine();

        int id =articleService.doWrite(conn, title, body);
        System.out.printf("article %d is wrote\n", id);
    }

    public void modify(Connection conn, String cmd) {
        int modifyId;

        try {
            modifyId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            return;
        }

        if (!articleService.idIsExist(conn, modifyId)) {
            System.out.printf("article %d is not exist\n", modifyId);
            return;
        }

        System.out.print("new Title : ");
        String title = sc.nextLine().trim();

        System.out.print("new Body : ");
        String body = sc.nextLine().trim();

        int id = articleService.doModify(conn, modifyId, title, body);
        System.out.printf("article %d is modified", id);
    }

    public void doDelete(Connection conn, String cmd) {

        int deleteId;

        try {
            deleteId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            deleteId = sc.nextInt();
            sc.nextLine();
        }

        if (!articleService.idIsExist(conn, deleteId)) {
            System.out.printf("article %d is not exist\n", deleteId);
            return;
        }

        int id = articleService.doDelete(conn, deleteId);
        System.out.printf("article %d is deleted\n", id);
    }

    public void showList(Connection conn) {

        // article 존재 여부 확인
        if (!articleService.articleIsExist(conn)) {
            System.out.println("article is not exist");
            return;
        }

        System.out.println(" No |  title  |  regDate  ");
        System.out.println("--------------------------");

        articleService.showList(conn);
    }

    public void showDetail(Connection conn, String cmd) {

        int detailId;

        try {
            detailId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            detailId = sc.nextInt();
            sc.nextLine();
        }

        if (!articleService.idIsExist(conn, detailId)) {
            System.out.printf("article %d is not exist\n", detailId);
            return;
        }

        articleService.showDetail(conn, detailId);
    }
}
