-- HXZ_회원서비스정보변경이력
-- ohxzown.tbhxzh102 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzh102;

CREATE TABLE ohxzown.tbhxzh102 (
    mbrs_svc_mod_sno   NUMERIC(10)   NOT NULL,
    tpw_svc_id         VARCHAR(7)    NOT NULL,
    mbrs_svc_mod_dt    VARCHAR(8)    NOT NULL,
    mbrs_id            VARCHAR(20)   NOT NULL,
    mbrs_svc_join_dt   VARCHAR(8)    NOT NULL,
    tpw_svc_typ_id     VARCHAR(10)   NOT NULL,
    tpw_mbrs_svc_sta_cd VARCHAR(2)   NULL,
    atfl_mng_no        NUMERIC(10)   NULL,
    mbrs_svc_cncn_dt   VARCHAR(8)    NULL,
    rgsr_id            VARCHAR(20)   NULL,
    rgt_dtm            VARCHAR(14)   NULL,
    updr_id            VARCHAR(20)   NULL,
    upd_dtm            VARCHAR(14)   NULL,
    CONSTRAINT pk_tbhxzh102 PRIMARY KEY (
        mbrs_svc_mod_sno, tpw_svc_id, mbrs_svc_mod_dt, mbrs_id, mbrs_svc_join_dt, tpw_svc_typ_id
    )
);
