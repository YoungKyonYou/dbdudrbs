-- HXZ_회원정보변경이력
-- ohxzown.tbhxzh101 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzh101;

CREATE TABLE ohxzown.tbhxzh101 (
    mbrs_id              VARCHAR(20)   NOT NULL,
    mbrs_inf_mod_dt      VARCHAR(8)    NOT NULL,
    mbrs_inf_mod_sno     NUMERIC(10)   NOT NULL,
    mbrs_nm              VARCHAR(100)  NULL,
    mail_addr            VARCHAR(64)   NULL,
    mbrs_mbph_no        VARCHAR(44)   NULL,
    mbrs_tel_no          VARCHAR(20)   NULL,
    pwd                  VARCHAR(64)   NULL,
    mbrs_sta_cd          VARCHAR(2)    NULL,
    pwd_err_ncnt         NUMERIC(2)    NULL,
    dspr_rgt_inhr_no     VARCHAR(20)   NULL,
    vtrn_no              VARCHAR(50)   NULL,
    tpw_join_typ_cd      VARCHAR(2)    NULL,
    tpw_join_rctd_val    VARCHAR(20)   NULL,
    scsn_dtm             VARCHAR(14)   NULL,
    prsn_auth_ci_enc_val VARCHAR(200)  NULL,
    gndr_cd              VARCHAR(1)    NULL,
    mbrs_brdt            VARCHAR(8)    NULL,
    mbrs_scsn_dvs_cd     VARCHAR(2)    NULL,
    ntfc_yn              VARCHAR(1)    NULL,
    mrkg_ultz_agrm_yn    VARCHAR(1)    NULL,
    sms_rcv_agrm_yn      VARCHAR(1)    NULL,
    mail_rcv_agrm_yn     VARCHAR(1)    NULL,
    bztl_rcv_agrm_yn     VARCHAR(1)    NULL,
    mbrs_join_dt         VARCHAR(8)    NULL,
    mod_rsn_ctt          VARCHAR(2000) NULL,
    rgsr_id              VARCHAR(20)   NULL,
    rgt_dtm              VARCHAR(14)   NULL,
    updr_id              VARCHAR(20)   NULL,
    upd_dtm              VARCHAR(14)   NULL,
    CONSTRAINT pk_tbhxzh101 PRIMARY KEY (mbrs_inf_mod_sno, mbrs_inf_mod_dt, mbrs_id)
);
