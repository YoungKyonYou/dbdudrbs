-- ohxzown.tbhxzm112 definition
-- Drop table
-- DROP TABLE ohxzown.tbhxzm112;

CREATE TABLE ohxzown.tbhxzm112 (
    user_id varchar(20) NOT NULL,
    user_dvs_cd varchar(1) NOT NULL,
    conc_dtm varchar(14) NOT NULL,
    conc_ip_addr varchar(20) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzm112 PRIMARY KEY (user_id, user_dvs_cd, conc_dtm)
);