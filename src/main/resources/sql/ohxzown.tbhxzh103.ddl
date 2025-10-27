CREATE TABLE ohxzown.tbhxzh103 (
    tpw_svc_id             VARCHAR(7)    NOT NULL,
    tpw_svc_typ_id         VARCHAR(10)   NOT NULL,
    mbrs_id                VARCHAR(20)   NOT NULL,
    tpw_rsdc_auth_ct_dt    VARCHAR(8)    NOT NULL,
    tpw_rsdc_auth_dt       VARCHAR(8)    NOT NULL,
    tpw_rsdc_auth_hst_sqno NUMERIC(10)   NOT NULL,
    addo_cd                VARCHAR(10)   NULL,
    stdo_cd                VARCHAR(10)   NULL,
    mvin_dt                VARCHAR(8)    NULL,
    mvot_dt                VARCHAR(8)    NULL,
    rgsr_id                VARCHAR(20)   NULL,
    rgt_dtm                VARCHAR(14)   NULL,
    updr_id                VARCHAR(20)   NULL,
    upd_dtm                VARCHAR(14)   NULL,
    CONSTRAINT pk_tbhxzh103 PRIMARY KEY (
        tpw_svc_id, tpw_svc_typ_id, mbrs_id, tpw_rsdc_auth_ct_dt, tpw_rsdc_auth_dt, tpw_rsdc_auth_hst_sqno
    )
);