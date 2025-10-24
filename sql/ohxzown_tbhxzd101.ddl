-- HXZ_회원분류내역
-- ohxzown.tbhxzd101 definition

-- Drop table
-- DROP TABLE ohxzown.tbhxzd101;

CREATE TABLE ohxzown.tbhxzd101 (
    mbrs_id          VARCHAR(20)   NOT NULL,
    mbrs_ctg_sno     NUMERIC(10)   NOT NULL,
    tpw_mbrs_ctg_cd  VARCHAR(2)    NOT NULL,
    vld_stt_dt       VARCHAR(8)    NOT NULL,
    vld_end_dt       VARCHAR(8)    NOT NULL,
    use_yn           VARCHAR(1)    NOT NULL,
    rgsr_id          VARCHAR(20)   NOT NULL,
    rgt_dtm          VARCHAR(14)   NOT NULL,
    updr_id          VARCHAR(20)   NOT NULL,
    upd_dtm          VARCHAR(14)   NOT NULL,
    CONSTRAINT pk_tbhxzd101 PRIMARY KEY (mbrs_id, mbrs_ctg_sno, tpw_mbrs_ctg_cd)
);
