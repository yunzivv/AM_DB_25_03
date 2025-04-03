package org.example.service;

import org.example.dao.MemberDao;

import java.sql.Connection;

public class MemberService {

    MemberDao memberDao;

    public MemberService() {

        memberDao = new MemberDao();
    }

    // 입력받은 loginId 존재 여부 확인
    public boolean loginIdIsExist(Connection conn, String loginId) {

        return memberDao.loginIdIsExist(conn, loginId);
    }

    // 회원가입
    public int doJoin(Connection conn, String loginId, String loginPw, String name) {

        return memberDao.doJoin(conn, loginId, loginPw, name);
    }

    public String login(Connection conn, String loginId, String loginPw) {

        return memberDao.login(conn, loginId, loginPw);
    }

    public int logout(Connection conn) {

        return memberDao.logout(conn);
    }

    public void join() {
    }
}
