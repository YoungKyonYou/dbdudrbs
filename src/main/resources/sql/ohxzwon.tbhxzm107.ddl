CREATE TABLE ohxzown.tbhxzm107 (
    org_cd varchar(7) NOT NULL,
    tpw_svc_mngr_id varchar(20) NOT NULL,
    tpw_svc_mngr_sno numeric(10) NOT NULL,
    tpw_svc_id varchar(7) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm107 PRIMARY KEY (org_cd, tpw_svc_mngr_id, tpw_svc_mngr_sno)
);