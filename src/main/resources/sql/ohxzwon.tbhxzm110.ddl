-- ohxzown.tbhxzm110 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm110;

CREATE TABLE ohxzown.tbhxzm110 (
    role_id varchar(20) NOT NULL,
    menu_id varchar(20) NOT NULL,
    menu_atht_mng_sno numeric(10) NOT NULL,
    inqr_atht_yn varchar(1) NULL,
    rgsr_id varchar(20) NOT NULL,
    rgt_dtm varchar(14) NOT NULL,
    rgt_atht_yn varchar(1) NULL,
    upd_atht_yn varchar(1) NULL,
    dlt_atht_yn varchar(1) NULL,
    use_yn varchar(1) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm110 PRIMARY KEY (role_id, menu_id, menu_atht_mng_sno)
);