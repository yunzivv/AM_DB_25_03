package org.example.service;

import org.example.container.Container;
import org.example.dao.ArticleDao;
import org.example.dto.Article;

import java.util.List;

public class ArticleService {

    ArticleDao articleDao;

    public ArticleService() {
        articleDao = Container.articleDao;
    }

    // article 존재 여부 확인
    public boolean articleIsExist() {

        return articleDao.articleIsExist();
    }

    public boolean idIsExist(int id) {

        return articleDao.idIsExist(id);
    }

    public int doWrite(String title, String body, int memberId) {

        return articleDao.doWrite(title, body, memberId);
    }

    public int doModify(int modifyId, String title, String body) {

        return articleDao.doModify(modifyId, title, body);
    }

    public int doDelete(int deleteId) {

        return articleDao.doDelete(deleteId);
    }

    public List<Article> getArticles() {

        return articleDao.getArticles();
    }

    public List<Article> getArticles(String keyword) {
        return articleDao.getArticles(keyword);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }
}
