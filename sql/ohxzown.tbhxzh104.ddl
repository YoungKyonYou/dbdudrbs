CREATE TABLE ohxzown.tbhxzh104 (
    tpw_svc_id varchar(7) NOT NULL,
    tpw_svc_typ_id varchar(10) NOT NULL,
    mbrs_id varchar(20) NOT NULL,
    card_mod_dt varchar(8) NOT NULL,
    card_mod_sno numeric(10) NOT NULL,
    card_no varchar(100) NULL,
    card_stt_dt varchar(8) NULL,
    card_end_dt varchar(8) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzh104 PRIMARY KEY (tpw_svc_id, tpw_svc_typ_id, mbrs_id, card_mod_dt, card_mod_sno)
);
