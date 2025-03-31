package org.example;

public class Main {
    public static void main(String[] args) {

        App app = new App();
        app.run();

//        int lastId = 0;
//
//        while (true) {
//            System.out.print("\ncmd : ");
//            String cmd = sc.nextLine();
//
//            if (cmd.equals("exit")) {
//                break;
//            }
//
//            if (cmd.equals("article write")) {
//
//                System.out.println("[write]");
//
//                System.out.print("title : ");
//                String title = sc.nextLine();
//
//                System.out.print("body : ");
//                String body = sc.nextLine();
//
//
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//
//                try {
//                    Class.forName("org.mariadb.jdbc.Driver");
//                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
//
//                    conn = DriverManager.getConnection(url, "root", "");
//                    System.out.println("연결 성공!");
//
//                    String sql = "INSERT INTO article ";
//                    sql += "SET regDate = NOW(), ";
//                    sql += "updateDate = NOW(), ";
//                    sql += "title = '" + title + "', ";
//                    sql += "`body` = '" + body + "';";
//
//                    pstmt = conn.prepareStatement(sql);
//                    System.out.println(sql);
//
//                    int affectedrows = pstmt.executeUpdate();
//                    System.out.println("sql affecte rows : " + affectedrows);
//
//
//                    System.out.printf("[article %d wrote]\n", ++lastId);
//
//
//                } catch (ClassNotFoundException e) {
//                    System.out.println("드라이버 로딩 실패" + e);
//                } catch (SQLException e) {
//                    System.out.println("에러 : " + e);
//                } finally {
//                    try {
//                        if (conn != null && !conn.isClosed()) {
//                            conn.close();
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            } else if (cmd.equals("article list")) {
//
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//
//                try {
//                    Class.forName("org.mariadb.jdbc.Driver");
//                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
//
//                    conn = DriverManager.getConnection(url, "root", "");
//                    System.out.println("연결 성공!");
//
//                    String sql = "SELECT id, title, regDate FROM article ORDER BY id DESC;";
//
//                    pstmt = conn.prepareStatement(sql);
//                    System.out.println(sql);
//
//                    ResultSet rs = pstmt.executeQuery();
//
//                    System.out.println("[list]");
//
//                    List<Article> articles = new ArrayList<>();
//
//                    while (rs.next()) {
//
//                        int id = rs.getInt("id");
//                        String title = rs.getString("title");
//                        String regDate = rs.getString("regDate");
//
//                        Article article = new Article(id, title, regDate);
//
//                        articles.add(article);
//                    }
//
//                    System.out.println(" No |  title  |  regDate  ");
//                    System.out.println("--------------------");
//                    for (Article article : articles) {
//                        int id = article.getId();
//                        String title = article.getTitle();
//                        String regDate = article.getRegDate();
//                        String nowDate = LocalDate.now().toString();
//
//                        if (regDate.split(" ")[0].equals(LocalDate.now().toString())) {
//                            regDate = regDate.split(" ")[1].substring(0, 8);
//                        } else {
//                            regDate = regDate.split(" ")[0];
//                        }
//                        System.out.println(" " + id + " | " + title + " | " + regDate);
//                    }
//
//                    int affectedrows = pstmt.executeUpdate();
//                    System.out.println("rows : " + affectedrows);
//
//                } catch (ClassNotFoundException e) {
//                    System.out.println("드라이버 로딩 실패" + e);
//
//                } catch (SQLException e) {
//                    System.out.println("에러 : " + e);
//
//                } finally {
//                    try {
//                        if (conn != null && !conn.isClosed()) {
//                            conn.close();
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            } else if (cmd.startsWith("article modify")) {
//
//                int modifyId = -1;
//
//                try {
//                    modifyId = Integer.parseInt(cmd.split(" ")[2]);
//                } catch (Exception e) {
//                    System.out.println("Enter aritlce Number");
//                }
//
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//
//                try {
//                    Class.forName("org.mariadb.jdbc.Driver");
//                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
//
//                    conn = DriverManager.getConnection(url, "root", "");
//                    System.out.println("연결 성공!");
//
//                    String sql = "select count(*) from article where id = " + modifyId + ";";
//                    System.out.println(sql);
//                    pstmt = conn.prepareStatement(sql);
//                    ResultSet rs = pstmt.executeQuery();
//
//                    int modifyIdCount = -1;
//
//                    while(rs.next()) {
//                        modifyIdCount = rs.getInt("count(*)");
//                    }
//
//                    if (modifyIdCount == 0) {
//                        System.out.printf("article %d is not exist\n", modifyId);
//                        continue;
//                    }
//
//
//                    System.out.print("new Title : ");
//                    String title = sc.nextLine().trim();
//
//                    System.out.print("new Body : ");
//                    String body = sc.nextLine().trim();
//
//                    sql = "update article";
//                    sql += " set updateDate = now()";
//                    if (title.length() > 0) {
//                        sql += ", title = '" + title + "'";
//                    }
//                    if (body.length() > 0) {
//                        sql += ", `body` = '" + body + "'";
//                    }
//                    sql += " where id = " + modifyId;
//
//                    pstmt = conn.prepareStatement(sql);
//
//                    rs = pstmt.executeQuery();
//
//                    int affectedrows = pstmt.executeUpdate();
//                    System.out.println("rows : " + affectedrows);
//
//                } catch (ClassNotFoundException e) {
//                    System.out.println("드라이버 로딩 실패" + e);
//
//                } catch (SQLException e) {
//                    System.out.println("에러 : " + e);
//
//                } finally {
//                    try {
//                        if (conn != null && !conn.isClosed()) {
//                            conn.close();
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (cmd.startsWith("article delete")) {
//
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//
//                try {
//                    Class.forName("org.mariadb.jdbc.Driver");
//                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
//                    conn = DriverManager.getConnection(url, "root", "");
//
//                    int deleteId = -1;
//
//                    try {
//                        deleteId = Integer.parseInt(cmd.split(" ")[2]);
//                    } catch (Exception e) {
//                        System.out.println("Enter aritlce Number");
//                        continue;
//                    }
//
//                    String sql = "select count(*) from article where id = " + deleteId + ";";
//
//                    pstmt = conn.prepareStatement(sql);
//
//                    ResultSet rs = pstmt.executeQuery();
//
//                    int deleteIdCount = -1;
//
//                    while(rs.next()) {
//                        deleteIdCount = rs.getInt("count(*)");
//                    }
//
//                    if (deleteIdCount == 0) {
//                        System.out.printf("article %d is not exist\n", deleteId);
//                        continue;
//                    }
//
//                    sql = "delete FROM article";
//                    sql += " WHERE id = " + deleteId + ";";
//
//                    // sql 명령어를 전달한다.
//                    pstmt = conn.prepareStatement(sql);
//                    System.out.println(sql);
//
//                    // 몇개의 행에 적용 되었는 지 반환
//                    int affectedrows = pstmt.executeUpdate();
//                    System.out.println("rows : " + affectedrows);
//
//                    System.out.printf("[article %d deleted]\n", deleteId);
//
//                } catch (ClassNotFoundException e) {
//                    System.out.println("드라이버 로딩 실패" + e);
//                } catch (SQLException e) {
//                    System.out.println("에러 : " + e);
//                } finally {
//                    try {
//                        if (conn != null && !conn.isClosed()) {
//                            conn.close();
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
    }
}
