CREATE TABLE IF NOT EXISTS job_run_log
(
    id              bigint AUTO_INCREMENT NOT NULL COMMENT '主键',
    job_name        VARCHAR(255)          NOT NULL COMMENT '任务名称',
    job_group       VARCHAR(255)          NOT NULL COMMENT '任务组',
    job_description VARCHAR(900) COMMENT '任务描述',
    job_class       VARCHAR(255)          NOT NULL COMMENT '任务类',
    job_data_map    TEXT COMMENT '任务参数',
    run_time        bigint                NOT NULL COMMENT '运行时间',
    run_result      VARCHAR(1)            NOT NULL COMMENT '任务运行结果',
    err_msg         TEXT COMMENT '错误信息',
    PRIMARY KEY (id)
) COMMENT = '任务执行日志';