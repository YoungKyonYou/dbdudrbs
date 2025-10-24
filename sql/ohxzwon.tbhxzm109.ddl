-- ohxzwon.tbhxzm109 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm109;

CREATE TABLE ohxzown.tbhxzm109 (
    menu_id varchar(20) NOT NULL,
    tpw_menu_dvs_cd varchar(2) NOT NULL,
    menu_lvl_no varchar(3) NOT NULL,
    menu_seq numeric(5) NOT NULL,
    menu_nm varchar(100) NULL,
    menu_desc varchar(500) NULL,
    menu_url_val varchar(100) NULL,
    hgrn_menu_id varchar(20) NULL,
    use_yn varchar(1) NULL,
    rgsr_id varchar(20) NOT NULL,
    rgt_dtm varchar(14) NOT NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm109 PRIMARY KEY (menu_id, tpw_menu_dvs_cd, menu_lvl_no, menu_seq)
);