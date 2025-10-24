-- ohxzzown.tbhxzh107 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzh107;

CREATE TABLE ohxzown.tbhxzh107 (
    org_cd varchar(7) NOT NULL,
    mngr_id varchar(20) NOT NULL,
    dn_dtm varchar(14) NOT NULL,
    dn_file_nm varchar(130) NULL,
    dn_rsn varchar(2000) NULL,
    rgsr_id varchar(20) NULL,
    rgt_dtm varchar(14) NULL,
    updr_id varchar(20) NULL,
    upd_dtm varchar(14) NULL,
    CONSTRAINT pk_tbhxzh107 PRIMARY KEY (org_cd, mngr_id, dn_dtm)
);