package org.example.controller;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;
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
            // 여기까지 service 할 일

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

        // 여기서 계속 오류 난다.
        int id = DBUtil.insert(conn, sql);
        System.out.printf("%s님 회원가입을 축하합니다.", name);
    }
}
