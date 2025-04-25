package org.example.dto;

import java.util.Map;

public class Article {

    private int id;
    private String regDate;
    private String updateDate;
    private int loginId;
    private String title;
    private String body;

    private String writer;

    public Article(Map<String, Object> articleMap) {
        this.id = (int) articleMap.get("id");
        this.regDate = (String) articleMap.get("regDate");
        this.updateDate = (String) articleMap.get("updateDate");
        this.loginId = (int) articleMap.get("loginId");
        this.title = (String) articleMap.get("title");
        this.body = (String) articleMap.get("body");
        this.writer = (String) articleMap.get("name");
    }

    public Article(int id, String regDate, String updateDate, int memberId, String title, String body) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.loginId = loginId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getRegDate() {return regDate;}

    public void setRegDate(String regDate) {this.regDate = regDate;}
    public String getUpdateDate() {return updateDate;}

    public void setUpdateDate(String updateDate) {this.updateDate = updateDate;}
    public int getMemberId() {
        return loginId;
    }

    public void setMemberId(int memberId) {
        this.loginId = memberId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
