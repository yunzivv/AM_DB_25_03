package org.example.controller;

import org.example.service.MemberService;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController {

    private Scanner sc;
    private Connection conn;
    private MemberService memberService;

    public MemberController(Scanner sc, Connection conn) {
        this.sc = sc;
        this.conn = conn;
        this.memberService = new MemberService();
    }

    public void join() {

        System.out.println("[member join]");
        String loginId;
        String loginPw;
        String name;

        while (true) {
            System.out.print("Id : ");
            loginId = sc.nextLine().trim();

            // 입력받은 loginId 형식 확인(입력되지 않았거나 공백을 포함하는 지)
            if (loginId.isEmpty() || loginId.contains(" ")) {
                System.out.println("올바르지 않은 형식의 ID입니다.");
                continue;
            }

            // memberService에서 loginId 존재 여부 확인
            boolean isLoginId = memberService.loginIdIsExist(conn, loginId);

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
            if (!loginPw.equals(loginPwCheck)) {
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

        int id = memberService.doJoin(conn, loginId, loginPw, name);
        System.out.printf("%s님 회원가입을 축하합니다.", name);
    }

    public void login() {
    }

    public void logout() {

    }
}
