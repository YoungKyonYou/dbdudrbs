-- ohxzown.tbhxzm115 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm115;

CREATE TABLE ohxzown.tbhxzm115 (
    org_cd varchar(7) NOT NULL,
    atfl_mng_no numeric(10) NOT NULL,
    atfl_nm varchar(100) NULL,
    orgl_atfl_nm varchar(200) NULL,
    atfl_path_nm varchar(500) NULL,
    rgsr_id varchar(20) NOT NULL,
    rgt_dtm varchar(14) NOT NULL,
    updr_id varchar(20) NOT NULL,
    upd_dtm varchar(14) NOT NULL,
    CONSTRAINT pk_tbhxzm115 PRIMARY KEY (org_cd, atfl_mng_no)
);