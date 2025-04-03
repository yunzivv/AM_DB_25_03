package org.example.dao;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;
import org.example.container.Container;
import org.example.dto.Article;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {

    Connection conn;

    public ArticleDao() {
        conn = Container.conn;
    }

    public boolean articleIsExist() {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM article;");

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public boolean idIsExist(int id) {

        SecSql sql = new SecSql();
        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM article");
        sql.append("WHERE id = ?;", id);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public int doWrite(String title, String body) {

        SecSql sql = new SecSql();
        sql.append("INSERT INTO article");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("title = ?,", title);
        sql.append("`body` = ?;", body);

        int id = DBUtil.insert(Container.conn, sql);

        return id;
    }

    public int doModify(int modifyId, String title, String body) {

        SecSql sql = new SecSql();
        sql.append("UPDATE article");
        sql.append("SET updateDate = NOW()");
        if (!title.isEmpty()) {
            sql.append(", title = ?", title);
        }
        if (!body.isEmpty()) {
            sql.append(", `body` = ?", body);
        }
        sql.append("WHERE id = ?;", modifyId);

        return DBUtil.update(Container.conn, sql);
    }

    public int doDelete(int deleteId) {

        SecSql sql = new SecSql();
        sql.append("DELETE");
        sql.append("FROM article");
        sql.append("WHERE id = ?;", deleteId);

        DBUtil.delete(Container.conn, sql);

        return deleteId;
    }

    public List<Article> getArticles() {
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("ORDER BY id DESC");

        List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

        List<Article> articles = new ArrayList<>();

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }
        return articles;
    }

    public Article getArticle(int detailId) {

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("WHERE id = ?;", detailId);

        Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);
        return new Article(articleMap);
    }
}
