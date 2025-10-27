-- ohxzown.tbhxzm114 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm114;

CREATE TABLE ohxzown.tbhxzm114 (
    bltn_no varchar(11) NOT NULL,
    faq_ttl_nm varchar(200) NULL,
    faq_ctt text NULL,
    answ_ctt varchar(4000) NULL,
    rgt_dt varchar(8) NULL,
    inqr_ncnt numeric(6) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm114 PRIMARY KEY (bltn_no)
);