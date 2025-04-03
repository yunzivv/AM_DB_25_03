package org.example.dao;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;
import org.example.container.Container;
import org.example.dto.Member;

import java.util.Map;

public class MemberDao {

    // id를 전달받아 해당 멤버 반환
    public Member getMemberByLonginId(String longinId) {

        Member member = null;
        return member;
    }

    public boolean loginIdIsExist(String loginId) {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int doJoin(String loginId, String loginPw, String name) {

        SecSql sql = new SecSql();
        sql.append("INSERT INTO`member`");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("loginId = ?,", loginId);
        sql.append("loginPw = ?,", loginPw);
        sql.append("`name` = ?;", name);

        // DBUtil.insert(conn, sql)에서 계속 오류 난다.
        // 쿼리문 예약어 대문자로 수정해서 해결
        return DBUtil.insert(Container.conn, sql);
    }

    public Member getMember(String loginId, String loginPw) {

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);
        sql.append("AND loginPw = ?;", loginPw);

        Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

        // 조건문 없이 그냥 memberMap을 반환하게 되면
        // NullPointException 발생
        if(memberMap.isEmpty()) {
            return null;
        }

        return new Member(memberMap);
    }

    public int logout() {
        return 0;
    }
}
