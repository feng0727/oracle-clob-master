--判断表是否存在，如果存在则删除
drop table WMS_IN_POOL;

-- Create table
create table WMS_IN_POOL
(
    POOL_PK_NO  NUMBER NOT NULL,
    BIG_DATA    CLOB default NULL,
    CREATE_TIME DATE default SYSDATE,
    UPDATE_TIME DATE
);

-- Add comments to the table
comment on table WMS_IN_POOL is 'CLOB测试表';

-- Add comments to the columns
COMMENT ON COLUMN WMS_IN_POOL.POOL_PK_NO IS '主键ID(自增)';
COMMENT ON COLUMN WMS_IN_POOL.BIG_DATA  IS '存储json字符串,大数据值';
COMMENT ON COLUMN WMS_IN_POOL.CREATE_TIME  IS '创建时间';
COMMENT ON COLUMN WMS_IN_POOL.UPDATE_TIME  IS '修改时间';

-- Create/Recreate primary, unique and foreign key constraints
alter table WMS_IN_POOL
    add constraint WMS_IN_POOL primary key (POOL_PK_NO);

-- Create sequence
create sequence SEQ_POOL_PK_NO
    minvalue 1    -- 最小值=1
    maxvalue 999999999999999999999999999  -- 指定最大值

    start with 1   -- 从1开始
    increment by 1  -- 每次递增1
    cache 20;

-- Create Index  --> clob can not create index
-- create index index_big_data on WMS_IN_POOL(BIG_DATA);

commit;
