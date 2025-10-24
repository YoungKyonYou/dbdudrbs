-- ohxzown.tbhxzm111 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm111;

CREATE TABLE ohxzown.tbhxzm111 (
    org_cd varchar(7) NOT NULL,
    user_id varchar(20) NOT NULL,
    user_dvs_cd varchar(1) NOT NULL,
    conc_dtm varchar(14) NOT NULL,
    menu_conc_sno numeric(10) NOT NULL,
    menu_nm varchar(100) NULL,
    menu_url_val varchar(100) NULL,
    user_ip_addr varchar(44) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm111 PRIMARY KEY (org_cd, user_id, user_dvs_cd, conc_dtm, menu_conc_sno)
);