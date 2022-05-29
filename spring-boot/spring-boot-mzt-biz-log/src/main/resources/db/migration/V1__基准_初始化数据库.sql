CREATE TABLE IF NOT EXISTS log_record
(
    id          bigserial    NOT NULL,
    type        varchar(50)  NOT NULL,
    sub_type    varchar(50),
    biz_no      varchar(255) NOT NULL,
    operator    varchar(255) NOT NULL,
    action      varchar(255) NOT NULL,
    fail        boolean      NOT NULL DEFAULT FALSE,
    create_time timestamptz  NOT NULL,
    extra       varchar(900),
    PRIMARY KEY (id)
);

COMMENT ON TABLE log_record IS '操作日志记录';
COMMENT ON COLUMN log_record.id IS '主键';
COMMENT ON COLUMN log_record.type IS '保存的操作日志的类型，比如：订单类型、商品类型';
COMMENT ON COLUMN log_record.sub_type IS '日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样';
COMMENT ON COLUMN log_record.biz_no IS '日志绑定的业务标识';
COMMENT ON COLUMN log_record.operator IS '操作人';
COMMENT ON COLUMN log_record.action IS '日志内容';
COMMENT ON COLUMN log_record.fail IS '是否是操作失败的日志';
COMMENT ON COLUMN log_record.create_time IS '创建时间';
COMMENT ON COLUMN log_record.extra IS '日志的额外信息';
