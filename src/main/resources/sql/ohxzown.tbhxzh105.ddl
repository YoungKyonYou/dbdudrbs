-- ohxzzown.tbhxzh106 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzh105;

CREATE TABLE ohxzown.tbhxzh105 (
    tpw_svc_id varchar(7) NOT NULL,
    tpw_svc_typ_id varchar(10) NOT NULL,
    mbrs_id varchar(20) NOT NULL,
    acnt_rgt_dt varchar(8) NOT NULL,
    acnt_sno numeric(10) NOT NULL,
    bnk_cd varchar(3) NULL,
    acnt_no varchar(49) NULL,
    ooa_nm varchar(100) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzh105 PRIMARY KEY (tpw_svc_id, tpw_svc_typ_id, mbrs_id, acnt_rgt_dt, acnt_sno)
);
