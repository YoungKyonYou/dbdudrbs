-- ohxzown.tbhxzm116 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm116;

CREATE TABLE ohxzown.tbhxzm116 (
    org_cd varchar(7) NOT NULL,
    tpw_svc_id varchar(7) NOT NULL,
    bnr_no varchar(11) NOT NULL,
    bnr_ttl_nm varchar(130) NULL,
    atfl_mng_no numeric(10) NULL,
    bnr_url_val varchar(1000) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NOT NULL,
    rgt_dtm varchar(14) NOT NULL,
    updr_id varchar(20) NOT NULL,
    upd_dtm varchar(14) NOT NULL,
    CONSTRAINT pk_tbhxzm116 PRIMARY KEY (org_cd, tpw_svc_id, bnr_no)
);