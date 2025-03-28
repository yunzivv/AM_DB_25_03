DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;

DROP TABLE article;

CREATE TABLE article(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

SELECT * FROM article;
DESC article;

# 0부터 1까지 숫자 중 랜덤 출력
SELECT RAND();

# 랜드함수 *10 을 한자리만 출력
SELECT SUBSTRING(RAND() *10, FROM 1 FOR 1);

INSERT INTO article
SET reDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *10 FROM 1 FOR 1));