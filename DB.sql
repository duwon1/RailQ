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

-- 트리거
CREATE OR REPLACE TRIGGER trg_member_seq
    BEFORE INSERT ON member
    FOR EACH ROW
BEGIN
    :NEW.member_seq := member_seq.NEXTVAL;
END;
/


-- 뷰