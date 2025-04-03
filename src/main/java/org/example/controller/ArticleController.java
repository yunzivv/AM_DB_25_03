package org.example.controller;

import org.example.container.Container;
import org.example.service.ArticleService;

import java.util.Scanner;

public class ArticleController {

    Scanner sc;
    ArticleService articleService;

    public ArticleController() {
        this.sc = Container.sc;
        articleService = Container.articleService;
    }

    public void doWrite() {

        System.out.println("[write]");

        System.out.print("title : ");
        String title = sc.nextLine();

        System.out.print("body : ");
        String body = sc.nextLine();

        int id =articleService.doWrite(title, body);
        System.out.printf("article %d is wrote\n", id);
    }

    public void modify(String cmd) {
        int modifyId;

        try {
            modifyId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            return;
        }

        if (!articleService.idIsExist(modifyId)) {
            System.out.printf("article %d is not exist\n", modifyId);
            return;
        }

        System.out.print("new Title : ");
        String title = sc.nextLine().trim();

        System.out.print("new Body : ");
        String body = sc.nextLine().trim();

        int id = articleService.doModify(modifyId, title, body);
        System.out.printf("article %d is modified", id);
    }

    public void doDelete(String cmd) {

        int deleteId;

        try {
            deleteId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            deleteId = sc.nextInt();
            sc.nextLine();
        }

        if (!articleService.idIsExist(deleteId)) {
            System.out.printf("article %d is not exist\n", deleteId);
            return;
        }

        int id = articleService.doDelete(deleteId);
        System.out.printf("article %d is deleted\n", id);
    }

    public void showList() {

        // article 존재 여부 확인
        if (!articleService.articleIsExist()) {
            System.out.println("article is not exist");
            return;
        }

        System.out.println(" No |  title  |  regDate  ");
        System.out.println("--------------------------");

        articleService.showList();
    }

    public void showDetail(String cmd) {

        int detailId;

        try {
            detailId = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("Enter article Number to decimal");
            detailId = sc.nextInt();
            sc.nextLine();
        }

        if (!articleService.idIsExist(detailId)) {
            System.out.printf("article %d is not exist\n", detailId);
            return;
        }

        articleService.showDetail(detailId);
    }
}
