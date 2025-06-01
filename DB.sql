-- 시퀀스
CREATE SEQUENCE member_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE reservation_seq START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE train_info_seq START WITH 1 INCREMENT BY 1 NOCACHE;

-- 테이블
-- 멤버
CREATE TABLE member (
    num         INT PRIMARY KEY,
    id          VARCHAR2(200) NOT NULL,
    pw          VARCHAR2(200) NOT NULL,
    email       VARCHAR2(200) NOT NULL,
    name        VARCHAR2(200) NOT NULL,
    gender      VARCHAR2(1) NOT NULL,
    zipcode     INT NOT NULL,
    address     VARCHAR2(200) NOT NULL,
    address1    VARCHAR2(200) NOT NULL,
    create_time	DATE DEFAULT SYSDATE,
    last_login	DATE,
    mcheck      INT DEFAULT 1 NOT NULL
);

-- 도시정보
CREATE TABLE city (
    id			INT PRIMARY KEY,
    name		varchar2(200) NOT NULL
);

-- 정거장정보
CREATE TABLE region (
    id			INT PRIMARY KEY,
    name		varchar2(200) NOT NULL
);

-- 기차종류
CREATE TABLE train (
    id			INT PRIMARY KEY,
    name		VARCHAR2(200) NOT NULL
);

-- 기차시간표정보
CREATE TABLE train_information (
    num             INT PRIMARY KEY,
    tid             INT NOT NULL,
    dep_regionid    INT NOT NULL,
    arr_regionid    INT NOT NULL,
    dep_time        DATE NOT NULL,
    arr_time        DATE NOT NULL,

    CONSTRAINT fk_train_info_train
        FOREIGN KEY (tid) REFERENCES train(id),
    CONSTRAINT fk_dep_region
        FOREIGN KEY (dep_regionid) REFERENCES region(id),
    CONSTRAINT fk_arr_region
        FOREIGN KEY (arr_regionid) REFERENCES region(id)
);

-- 예약정보
CREATE TABLE reservation (
    num                 INT PRIMARY KEY,
    mnum                INT NOT NULL,
    tnum                INT NOT NULL,
    seat_no             VARCHAR2(10),
    reservation_time    DATE DEFAULT SYSDATE,

    CONSTRAINT fk_reservation_member
        FOREIGN KEY (mnum) REFERENCES member(num),
    CONSTRAINT fk_reservation_train_info
        FOREIGN KEY (tnum) REFERENCES train_information(num)
);


-- 뷰