-- ohxzown.tbhxzm113 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm113;

CREATE TABLE ohxzown.tbhxzm113 (
    org_cd varchar(7) NOT NULL,
    bltn_no varchar(11) NOT NULL,
    bltn_dvs_cd varchar(1) NOT NULL,
    tpw_bltn_rgt_dtm varchar(14) NOT NULL,
    ntc_mtrr_ttl_nm varchar(100) NULL,
    ntc_mtrr_ctt text NULL,
    main_exps_yn varchar(1) NULL,
    main_exps_stt_dt varchar(8) NULL,
    main_exps_end_dt varchar(8) NULL,
    inqr_ncnt numeric(6) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm113 PRIMARY KEY (org_cd, bltn_no, bltn_dvs_cd, tpw_bltn_rgt_dtm)
);