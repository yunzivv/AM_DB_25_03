package org.example.service;

import org.example.MemberDao;
import org.example.Util.DBUtil;
import org.example.Util.SecSql;
import java.sql.Connection;

public class MemberService {

    MemberDao memberDao;
    int lastId;

    public MemberService() {
        memberDao = new MemberDao();
        lastId = 1;
    }

    // 입력받은 loginId 존재 여부 확인
    public boolean loginIdIsExist(Connection conn, String loginId) {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DBUtil.selectRowBooleanValue(conn, sql);
    }

    // 회원가입
    public int doJoin(Connection conn, String loginId, String loginPw, String name) {

        SecSql sql = new SecSql();
        sql.append("INSERT INTO`member`");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("loginId = ?,", loginId);
        sql.append("loginPw = ?,", loginPw);
        sql.append("`name` = ?;", name);

        // DBUtil.insert(conn, sql)에서 계속 오류 난다.
        // 쿼리문 예약어 대문자로 수정해서 해결
        return DBUtil.insert(conn, sql);
    }

    public void login() {

    }

    public void logout() {

    }

    public void join() {
    }
}
