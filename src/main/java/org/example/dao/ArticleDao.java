package org.example.dao;

import org.example.container.Container;

import java.sql.Connection;

public class ArticleDao {

    Connection conn;

    public ArticleDao() {
        conn = Container.conn;
    }
}
