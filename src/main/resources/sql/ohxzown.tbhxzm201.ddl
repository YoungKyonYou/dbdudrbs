-- HXZ_교통복지서비스관리
-- ohxzown.tbhxzm201 definition

-- DROP TABLE ohxzown.tbhxzm201;

CREATE TABLE ohxzown.tbhxzm201 (
                                   org_cd        varchar(7)   NOT NULL,
                                   tpw_svc_id    varchar(7)   NOT NULL,
                                   tpw_svc_nm    varchar(500) NOT NULL,
                                   tpw_svc_stt_dt varchar(8)  NOT NULL,
                                   tpw_svc_end_dt varchar(8)  NOT NULL,
                                   tpw_svc_ctt   varchar(2000) NOT NULL,
                                   krn_chec_yn   varchar(1)   NOT NULL,
                                   use_yn        varchar(1)   NOT NULL,
                                   acng_trdp_no  varchar(8)   NULL,
                                   bnk_trn_ctt   varchar(20)  NULL,
                                   rgsr_id       varchar(20)  NOT NULL,
                                   rgt_dtm       varchar(14)  NOT NULL,
                                   updr_id       varchar(20)  NOT NULL,
                                   upd_dtm       varchar(14)  NOT NULL,
                                   CONSTRAINT pk_tbhxzm201 PRIMARY KEY (org_cd, tpw_svc_id)
);
