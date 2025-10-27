-- ohxzzown.tbhxzh106 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzh106;

CREATE TABLE ohxzown.tbhxzh106 (
    mngr_id varchar(20) NOT NULL,
    mngr_mod_dt varchar(8) NOT NULL,
    mngr_mod_sno numeric(10) NOT NULL,
    mngr_name varchar(40) NULL,
    mngr_atht_id varchar(50) NULL,
    mngr_atht_upd_dtm varchar(14) NULL,
    otp_scrt_key_val varchar(100) NULL,
    pwd varchar(64) NULL,
    pwd_int_yn varchar(1) NULL,
    pwd_err_ncnt numeric(2) NULL,
    mngr_mbph_no varchar(44) NULL,
    mngr_tel_no varchar(44) NULL,
    mail_addr varchar(64) NULL,
    mngr_sta_cd varchar(1) NULL,
    org_cd varchar(7) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzh106 PRIMARY KEY (mngr_id, mngr_mod_dt, mngr_mod_sno)
);