DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;

DROP TABLE article;

# loginId 속성 추가해야한다.
CREATE TABLE article(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

SELECT * FROM article;
DESC article;

# 0부터 1까지 숫자 중 랜덤 출력
SELECT RAND();

# 랜드함수 *10 을 한자리만 출력
SELECT SUBSTRING(RAND() *10 FROM 1 FOR 1);

# INSERT INTO article
# SET regDate = NOW(),
#     updateDate = NOW(),
#     title = CONCAT('제목', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
#     `body` = CONCAT('내용', SUBSTRING(RAND() *10 FROM 1 FOR 1));

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목3',
    `body` = '내용3';

SELECT * FROM article;

USE AM_DB_25_03;
SHOW TABLES;

CREATE TABLE `member`(
id INT(100) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
     regDate DATETIME NOT NULL,
     updateDate DATETIME NOT NULL,
     loginId VARCHAR(100) NOT NULL UNIQUE,
     loginPw VARCHAR(100) NOT NULL UNIQUE,
    `name` VARCHAR(10) NOT NULL
);
DESC `member`;

INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'keroro',
    loginPw = 'kero',
    `name` = 'keroro';

# INSERT INTO `member`
# SET regDate = NOW(),
#     updateDate = NOW(),
#     loginId = CONCAT('아이디', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
#     loginPw = CONCAT('비번', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
#     `name` = CONCAT('이름', SUBSTRING(RAND() *10 FROM 1 FOR 1));

SELECT * FROM article;
SELECT * FROM `member`;


################################################ 세팅


DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;

DROP TABLE article;

# article 테이블 생성
CREATE TABLE article(
                        id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        title VARCHAR(100) NOT NULL,
                        `body` TEXT NOT NULL
);

SELECT * FROM article;
DESC article;

# 0부터 1까지 숫자 중 랜덤 출력
SELECT RAND();

# 랜드함수 *10 을 한자리만 출력
SELECT SUBSTRING(RAND() *10 FROM 1 FOR 1);

# 테스트 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *10 FROM 1 FOR 1));

# article 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목3',
    `body` = '내용3';

SELECT * FROM article;

USE AM_DB_25_03;
SHOW TABLES;

# member 테이블 생성
CREATE TABLE `member`(
                         id INT(100) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         loginId VARCHAR(100) NOT NULL UNIQUE,
                         loginPw VARCHAR(100) NOT NULL UNIQUE,
                         `name` VARCHAR(10) NOT NULL
);
DESC `member`;

# member 테이블 테스트 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'keroro',
    loginPw = 'kero',
    `name` = 'keroro';

# 테스트 데이터 대량 생성
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = CONCAT('아이디', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    loginPw = CONCAT('비번', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    `name` = CONCAT('이름', SUBSTRING(RAND() *10 FROM 1 FOR 1));

SELECT * FROM article;
SELECT * FROM `member`;


################################################ 세팅

DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;

DROP TABLE article;

# article 테이블 생성
CREATE TABLE article(
                        id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        title VARCHAR(100) NOT NULL,
                        `body` TEXT NOT NULL
);

SELECT * FROM article;
DESC article;

# 0부터 1까지 숫자 중 랜덤 출력
SELECT RAND();

# 랜드함수 *10 을 한자리만 출력
SELECT SUBSTRING(RAND() *10 FROM 1 FOR 1);

# 테스트 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *10 FROM 1 FOR 1));

# article 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목3',
    `body` = '내용3';

SELECT * FROM article;

USE AM_DB_25_03;
SHOW TABLES;

# member 테이블 생성
CREATE TABLE `member`(
                         id INT(100) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         loginId VARCHAR(100) NOT NULL UNIQUE,
                         loginPw VARCHAR(100) NOT NULL UNIQUE,
                         `name` VARCHAR(10) NOT NULL
);
DESC `member`;

# member 테이블 테스트 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'keroro',
    loginPw = 'kero',
    `name` = 'keroro';

# 테스트 데이터 대량 생성
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = CONCAT('아이디', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    loginPw = CONCAT('비번', SUBSTRING(RAND() *10 FROM 1 FOR 1)),
    `name` = CONCAT('이름', SUBSTRING(RAND() *10 FROM 1 FOR 1));

SELECT * FROM article;
SELECT * FROM `member`;


################################################ 세팅

# article 테이블에 memberId 추가
ALTER TABLE article
    ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# memberID 속성 값 추가
UPDATE article
SET memberId = 1
WHERE id = 4;

UPDATE article
SET memberId = 2
WHERE id = 5;

UPDATE article
SET memberId = 3
WHERE id = 7;

UPDATE article
SET memberId = 4
WHERE id = 8;

UPDATE article
SET memberId = 5
WHERE id = 9;

# member랑 article join
SELECT a.*, m.name
FROM article AS a
         INNER JOIN `member` AS m
                    ON a.memberId = m.id;

SELECT a.*, m.name
FROM article AS a
         INNER JOIN `member` AS m
                    ON a.memberId = m.id
WHERE a.id = 5;

DESC article;
