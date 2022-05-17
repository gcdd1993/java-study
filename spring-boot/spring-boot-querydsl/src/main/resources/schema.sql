CREATE TABLE pre_user
(
    id         varchar(64) NOT NULL
        PRIMARY KEY,
    username   varchar(255),
    realname   varchar(255),
    gender     varchar(255),
    age        int,
    qq         varchar(32),
    birthday   varchar(255),
    birthmonth varchar(255),
    birthyear  varchar(255)
);