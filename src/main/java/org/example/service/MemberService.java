package org.example.service;

import org.example.dao.MemberDao;
import org.example.dto.Member;

public class MemberService {

    MemberDao memberDao;
    Member loginedMember = null;

    public MemberService() {

        memberDao = new MemberDao();
    }

    // 입력받은 loginId 존재 여부 확인
    public boolean loginIdIsExist(String loginId) {

        return memberDao.loginIdIsExist(loginId);
    }

    // 회원가입
    public int doJoin(String loginId, String loginPw, String name) {

        return memberDao.doJoin(loginId, loginPw, name);
    }

    // 로그인
    public Member login(String loginId, String loginPw) {

        return memberDao.getMember(loginId, loginPw);
    }

    // 로그아웃
    public int logout() {

        return memberDao.logout();
    }


}
