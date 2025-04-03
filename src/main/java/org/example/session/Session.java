package org.example.session;

import org.example.container.Container;
import org.example.dto.Member;

// 로그인 상태를 기억함
// 웹 사이트의 여러 페이지에 결쳐 사용되는 사용자 정보를 저장
public class Session {

    public Member loginedMember;
    public int loginedMemberId;

    public Session() {
        loginedMember = null;
        loginedMemberId = -1;
    }

    public void login(Member member) {
        Container.session.loginedMember = member;
    }

    public void logout() {
        loginedMember = null;
        loginedMemberId = -1;
    }

    public boolean isLogined() {
        return loginedMember != null;
    }
}
