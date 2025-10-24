CREATE TABLE ohxzown.tbhxzm103 (
    tpw_mbrs_typ_cd varchar(3) NOT NULL,
    mbrs_typ_ctg_sno numeric(10) NOT NULL,
    tpw_mbrs_typ_cd_nm varchar(200) NULL,
    tpw_mbrs_ctg_cd varchar(2) NULL,
    use_yn varchar(1) NULL,
    vld_stt_dt varchar(8) NULL,
    vld_end_dt varchar(8) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm103 PRIMARY KEY (tpw_mbrs_typ_cd, mbrs_typ_ctg_sno)
);