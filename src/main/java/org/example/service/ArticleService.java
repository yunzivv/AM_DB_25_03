package org.example.service;

import org.example.Util.DBUtil;
import org.example.Util.SecSql;
import org.example.container.Container;
import org.example.dao.ArticleDao;
import org.example.dto.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleService {

    List<Article> articles;
    ArticleDao articleDao;

    public ArticleService() {
        articles = new ArrayList<Article>();
        articleDao = Container.articleDao;
    }

    // article 존재 여부 확인
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

    public void showList() {

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("ORDER BY id DESC;");

        List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }

        for (Article article : articles) {
            int id = article.getId();
            String title = article.getTitle();
            String regDate = article.getRegDate();
            String nowDate = LocalDate.now().toString();

            // 작성일자가 오늘이라면 시간, 오늘 이전이라면 날짜로 출력
            if (regDate.split(" ")[0].equals(nowDate)) {
                regDate = regDate.split(" ")[1].substring(0, 8);
            } else {
                regDate = regDate.split(" ")[0];
            }
            System.out.println(" " + id + "  |  " + title + " |  " + regDate);
        }
    }

    public void showDetail(int detailId) {

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("WHERE id = ?;", detailId);

        Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

        Article article = new Article(articleMap);
        System.out.println("No. : " + article.getId());
        System.out.println("작성일자 : " + article.getRegDate());
        System.out.println("수정일자 : " + article.getUpdateDate());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());

    }
}
