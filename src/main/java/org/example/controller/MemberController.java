package org.example.controller;

import org.example.container.Container;
import org.example.dto.Member;
import org.example.service.MemberService;

import java.sql.Connection;
import java.util.Scanner;

public class MemberController {

    private Scanner sc;
    private Connection conn;
    private MemberService memberService;
    private Member loginedMember = null;

    public MemberController() {
        this.sc = Container.sc;
        this.conn = Container.conn;
        this.memberService = new MemberService();
    }

    boolean isLogined() {
        return loginedMember != null;
    }

    public Member getLoginedMember() {
        return loginedMember;
    }

    public void join() {

        if (Container.session.isLogined()) {
            System.out.println("로그아웃 후 사용하세요");
            return;
        }

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
            boolean isLoginId = memberService.loginIdIsExist(loginId);

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

        int id = memberService.doJoin(loginId, loginPw, name);
        System.out.printf("%s님 회원가입을 축하합니다.", name);
    }

    public void login() {

        if (Container.session.isLogined()) {
            System.out.println("로그아웃 후 사용하세요");
            return;
        }

        System.out.println("[member Login]");
        int count = 1;
        for (int i = 0; i < 3; i++) {
            System.out.print("login ID : ");
            String loginId = sc.nextLine().trim();
            System.out.print("password : ");
            String loginPw = sc.nextLine().trim();

            loginedMember = memberService.login(loginId, loginPw);
            String name = loginedMember.getName();

            if (name == null || name.isEmpty()) {
                System.out.printf("로그인 실패(%d/3)\n", count);
                ++count;
                continue;
            }

            Container.session.loginedMember = loginedMember;
            Container.session.loginedMemberId = loginedMember.getId();
            System.out.print(name + "님 로그인 성공\n");

            return;
        }
    }

    public void logout() {

        if (!Container.session.isLogined()) {
            System.out.println("로그인 후 사용하세요");
            return;
        }

        Container.session.loginedMember = null;
        Container.session.loginedMemberId = -1;

        System.out.println("로그아웃 되었습니다.");
    }

    public void showProfile() {
        if (Container.session.isLogined()) {
            System.out.println("로그인 후 사용하세요");
            return;
        } else {
            System.out.println(Container.session.loginedMember);
        }
    }

}
