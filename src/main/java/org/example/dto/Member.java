package org.example.dto;

import java.util.Map;

public class Member {

    private int id;
    private String regDate;
    private String updateDate;
    private String loginId;
    private String lginPw;
    private String name;

    public Member(Map<String, Object> articleMap) {
        this.id = (int) articleMap.get("id");
        this.regDate = (String) articleMap.get("regDate");
        this.updateDate = (String) articleMap.get("updateDate");
        this.loginId = (String) articleMap.get("loginId");
        this.lginPw = (String) articleMap.get("lginPw");
        this.name = (String) articleMap.get("name");
    }

    public Member(int id, String regDate, String updateDate, String loginId, String lginPw, String name) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.loginId = loginId;
        this.lginPw = lginPw;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLginPw() {
        return lginPw;
    }

    public void setLginPw(String lginPw) {
        this.lginPw = lginPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
