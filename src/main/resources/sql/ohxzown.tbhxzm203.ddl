-- HXZ_지원금신청
-- DROP TABLE ohxzown.tbhxzm203;

CREATE TABLE ohxzown.tbhxzm203 (
                                   tpw_svc_id       varchar(7)   NOT NULL,
                                   tpw_svc_typ_sno  numeric(10)  NOT NULL,
                                   stlm_dt          varchar(8)   NOT NULL,
                                   mbrs_id          varchar(20)  NOT NULL,
                                   tpw_svc_typ_id   varchar(10)  NOT NULL,
                                   apl_dt           varchar(8)   NULL,
                                   bnk_cd           varchar(3)   NULL,
                                   acnt_no          varchar(49)  NULL,
                                   ooa_nm           varchar(100) NULL,
                                   apro_id          varchar(20)  NULL,
                                   aprv_dtm         varchar(14)  NULL,
                                   aprv_sta_cd      varchar(2)   NULL,
                                   atfl_mng_no      numeric(10)  NULL,
                                   tpw_apl_prgs_sta_cd varchar(2) NULL,
                                   rgsr_id          varchar(20)  NULL,
                                   rgt_dtm          varchar(14)  NULL,
                                   updr_id          varchar(20)  NULL,
                                   upd_dtm          varchar(14)  NULL,
                                   CONSTRAINT pk_tbhxzm203 PRIMARY KEY (tpw_svc_id, tpw_svc_typ_sno, stlm_dt, mbrs_id, tpw_svc_typ_id)
);
