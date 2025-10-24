-- ohxzwon.tbhxzm104 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm104;

CREATE TABLE ohxzown.tbhxzm104 (
    org_cd varchar(7) NOT NULL,
    tpw_mbrs_typ_cd varchar(3) NOT NULL,
    mbrs_typ_mng_sno numeric(10) NOT NULL,
    typ_adpt_min_val numeric(10) NULL,
    typ_adpt_max_val numeric(10) NULL,
    use_yn varchar(1) NULL,
    vld_stt_dt varchar(8) NULL,
    vld_end_dt varchar(8) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm104 PRIMARY KEY (org_cd, tpw_mbrs_typ_cd, mbrs_typ_mng_sno)
);