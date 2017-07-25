create schema quartz;

-- delete from quartz.qrtz_fired_triggers;
-- delete from quartz.qrtz_simple_triggers;
-- delete from quartz.qrtz_simprop_triggers;
-- delete from quartz.qrtz_cron_triggers;
-- delete from quartz.qrtz_blob_triggers;
-- delete from quartz.qrtz_triggers;
-- delete from quartz.qrtz_job_details;
-- delete from quartz.qrtz_calendars;
-- delete from quartz.qrtz_paused_trigger_grps;
-- delete from quartz.qrtz_locks;
-- delete from quartz.qrtz_scheduler_state;
--
-- drop table quartz.qrtz_calendars;
-- drop table quartz.qrtz_fired_triggers;
-- drop table quartz.qrtz_blob_triggers;
-- drop table quartz.qrtz_cron_triggers;
-- drop table quartz.qrtz_simple_triggers;
-- drop table quartz.qrtz_simprop_triggers;
-- drop table quartz.qrtz_triggers;
-- drop table quartz.qrtz_job_details;
-- drop table quartz.qrtz_paused_trigger_grps;
-- drop table quartz.qrtz_locks;
-- drop table quartz.qrtz_scheduler_state;


CREATE TABLE quartz.qrtz_job_details
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  JOB_NAME          VARCHAR(200) NOT NULL,
  JOB_GROUP         VARCHAR(200) NOT NULL,
  DESCRIPTION       VARCHAR(250) NULL,
  JOB_CLASS_NAME    VARCHAR(250) NOT NULL,
  IS_DURABLE        BOOL         NOT NULL,
  IS_NONCONCURRENT  BOOL         NOT NULL,
  IS_UPDATE_DATA    BOOL         NOT NULL,
  REQUESTS_RECOVERY BOOL         NOT NULL,
  JOB_DATA          BYTEA        NULL,
  PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

CREATE TABLE quartz.qrtz_triggers
(
  SCHED_NAME     VARCHAR(120) NOT NULL,
  TRIGGER_NAME   VARCHAR(200) NOT NULL,
  TRIGGER_GROUP  VARCHAR(200) NOT NULL,
  JOB_NAME       VARCHAR(200) NOT NULL,
  JOB_GROUP      VARCHAR(200) NOT NULL,
  DESCRIPTION    VARCHAR(250) NULL,
  NEXT_FIRE_TIME BIGINT       NULL,
  PREV_FIRE_TIME BIGINT       NULL,
  PRIORITY       INTEGER      NULL,
  TRIGGER_STATE  VARCHAR(16)  NOT NULL,
  TRIGGER_TYPE   VARCHAR(8)   NOT NULL,
  START_TIME     BIGINT       NOT NULL,
  END_TIME       BIGINT       NULL,
  CALENDAR_NAME  VARCHAR(200) NULL,
  MISFIRE_INSTR  SMALLINT     NULL,
  JOB_DATA       BYTEA        NULL,
  PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
  REFERENCES quartz.QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
);
CREATE TABLE quartz.qrtz_simple_triggers
(
  SCHED_NAME      VARCHAR(120) NOT NULL,
  TRIGGER_NAME    VARCHAR(200) NOT NULL,
  TRIGGER_GROUP   VARCHAR(200) NOT NULL,
  REPEAT_COUNT    BIGINT       NOT NULL,
  REPEAT_INTERVAL BIGINT       NOT NULL,
  TIMES_TRIGGERED BIGINT       NOT NULL,
  PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES quartz.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE quartz.qrtz_cron_triggers
(
  SCHED_NAME      VARCHAR(120) NOT NULL,
  TRIGGER_NAME    VARCHAR(200) NOT NULL,
  TRIGGER_GROUP   VARCHAR(200) NOT NULL,
  CRON_EXPRESSION VARCHAR(120) NOT NULL,
  TIME_ZONE_ID    VARCHAR(80),
  PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES quartz.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE quartz.qrtz_simprop_triggers
(
  SCHED_NAME    VARCHAR(120)   NOT NULL,
  TRIGGER_NAME  VARCHAR(200)   NOT NULL,
  TRIGGER_GROUP VARCHAR(200)   NOT NULL,
  STR_PROP_1    VARCHAR(512)   NULL,
  STR_PROP_2    VARCHAR(512)   NULL,
  STR_PROP_3    VARCHAR(512)   NULL,
  INT_PROP_1    INT            NULL,
  INT_PROP_2    INT            NULL,
  LONG_PROP_1   BIGINT         NULL,
  LONG_PROP_2   BIGINT         NULL,
  DEC_PROP_1    NUMERIC(13, 4) NULL,
  DEC_PROP_2    NUMERIC(13, 4) NULL,
  BOOL_PROP_1   BOOL           NULL,
  BOOL_PROP_2   BOOL           NULL,
  PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES quartz.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE quartz.qrtz_blob_triggers
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_NAME  VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  BLOB_DATA     BYTEA        NULL,
  PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES quartz.QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE quartz.qrtz_calendars
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  CALENDAR_NAME VARCHAR(200) NOT NULL,
  CALENDAR      BYTEA        NOT NULL,
  PRIMARY KEY (SCHED_NAME, CALENDAR_NAME)
);
CREATE TABLE quartz.qrtz_paused_trigger_grps
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  PRIMARY KEY (SCHED_NAME, TRIGGER_GROUP)
);
CREATE TABLE quartz.qrtz_fired_triggers
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  ENTRY_ID          VARCHAR(95)  NOT NULL,
  TRIGGER_NAME      VARCHAR(200) NOT NULL,
  TRIGGER_GROUP     VARCHAR(200) NOT NULL,
  INSTANCE_NAME     VARCHAR(200) NOT NULL,
  FIRED_TIME        BIGINT       NOT NULL,
  SCHED_TIME        BIGINT       NOT NULL,
  PRIORITY          INTEGER      NOT NULL,
  STATE             VARCHAR(16)  NOT NULL,
  JOB_NAME          VARCHAR(200) NULL,
  JOB_GROUP         VARCHAR(200) NULL,
  IS_NONCONCURRENT  BOOL         NULL,
  REQUESTS_RECOVERY BOOL         NULL,
  PRIMARY KEY (SCHED_NAME, ENTRY_ID)
);
CREATE TABLE quartz.qrtz_scheduler_state
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  INSTANCE_NAME     VARCHAR(200) NOT NULL,
  LAST_CHECKIN_TIME BIGINT       NOT NULL,
  CHECKIN_INTERVAL  BIGINT       NOT NULL,
  PRIMARY KEY (SCHED_NAME, INSTANCE_NAME)
);
CREATE TABLE quartz.qrtz_locks
(
  SCHED_NAME VARCHAR(120) NOT NULL,
  LOCK_NAME  VARCHAR(40)  NOT NULL,
  PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);
CREATE INDEX idx_qrtz_j_req_recovery ON quartz.qrtz_job_details (SCHED_NAME, REQUESTS_RECOVERY);
CREATE INDEX idx_qrtz_j_grp ON quartz.qrtz_job_details (SCHED_NAME, JOB_GROUP);
CREATE INDEX idx_qrtz_t_j ON quartz.qrtz_triggers (SCHED_NAME, JOB_NAME, JOB_GROUP);
CREATE INDEX idx_qrtz_t_jg ON quartz.qrtz_triggers (SCHED_NAME, JOB_GROUP);
CREATE INDEX idx_qrtz_t_c ON quartz.qrtz_triggers (SCHED_NAME, CALENDAR_NAME);
CREATE INDEX idx_qrtz_t_g ON quartz.qrtz_triggers (SCHED_NAME, TRIGGER_GROUP);
CREATE INDEX idx_qrtz_t_state ON quartz.qrtz_triggers (SCHED_NAME, TRIGGER_STATE);
CREATE INDEX idx_qrtz_t_n_state ON quartz.qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
CREATE INDEX idx_qrtz_t_n_g_state ON quartz.qrtz_triggers (SCHED_NAME, TRIGGER_GROUP, TRIGGER_STATE);
CREATE INDEX idx_qrtz_t_next_fire_time ON quartz.qrtz_triggers (SCHED_NAME, NEXT_FIRE_TIME);
CREATE INDEX idx_qrtz_t_nft_st ON quartz.qrtz_triggers (SCHED_NAME, TRIGGER_STATE, NEXT_FIRE_TIME);
CREATE INDEX idx_qrtz_t_nft_misfire ON quartz.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME);
CREATE INDEX idx_qrtz_t_nft_st_misfire ON quartz.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_STATE);
CREATE INDEX idx_qrtz_t_nft_st_misfire_grp ON quartz.qrtz_triggers (SCHED_NAME, MISFIRE_INSTR, NEXT_FIRE_TIME, TRIGGER_GROUP, TRIGGER_STATE);
CREATE INDEX idx_qrtz_ft_trig_inst_name ON quartz.qrtz_fired_triggers (SCHED_NAME, INSTANCE_NAME);
CREATE INDEX idx_qrtz_ft_inst_job_req_rcvry ON quartz.qrtz_fired_triggers (SCHED_NAME, INSTANCE_NAME, REQUESTS_RECOVERY);
CREATE INDEX idx_qrtz_ft_j_g ON quartz.qrtz_fired_triggers (SCHED_NAME, JOB_NAME, JOB_GROUP);
CREATE INDEX idx_qrtz_ft_jg ON quartz.qrtz_fired_triggers (SCHED_NAME, JOB_GROUP);
CREATE INDEX idx_qrtz_ft_t_g ON quartz.qrtz_fired_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);
CREATE INDEX idx_qrtz_ft_tg ON quartz.qrtz_fired_triggers (SCHED_NAME, TRIGGER_GROUP);
