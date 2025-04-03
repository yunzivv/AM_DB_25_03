package org.example.dao;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;

import java.sql.Connection;

public class MemberDao {

    public boolean loginIdIsExist(Connection conn, String loginId) {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DBUtil.selectRowBooleanValue(conn, sql);
    }

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

    public String login(Connection conn, String loginId, String loginPw) {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?", loginId);
        sql.append("AND loginPw = ?;", loginPw);

        if(DBUtil.selectRowBooleanValue(conn, sql)) {

            sql = new SecSql();
            sql.append("SELECT `name`");
            sql.append("FROM `member`");
            sql.append("WHERE loginId = ?;", loginId);

            String name = DBUtil.selectRowStringValue(conn, sql);

            // member 객체를 만들어서 돌려주고 싶다.
            return name;
        }
        return null;
    }

    public int logout(Connection conn) {
        return 0;
    }
}
