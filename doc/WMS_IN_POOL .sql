--�жϱ��Ƿ���ڣ����������ɾ��
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
comment on table WMS_IN_POOL is 'CLOB���Ա�';

-- Add comments to the columns
COMMENT ON COLUMN WMS_IN_POOL.POOL_PK_NO IS '����ID(����)';
COMMENT ON COLUMN WMS_IN_POOL.BIG_DATA  IS '�洢json�ַ���,������ֵ';
COMMENT ON COLUMN WMS_IN_POOL.CREATE_TIME  IS '����ʱ��';
COMMENT ON COLUMN WMS_IN_POOL.UPDATE_TIME  IS '�޸�ʱ��';

-- Create/Recreate primary, unique and foreign key constraints
alter table WMS_IN_POOL
    add constraint WMS_IN_POOL primary key (POOL_PK_NO);

-- Create sequence
create sequence SEQ_POOL_PK_NO
    minvalue 1    -- ��Сֵ=1
    maxvalue 999999999999999999999999999  -- ָ�����ֵ

    start with 1   -- ��1��ʼ
    increment by 1  -- ÿ�ε���1
    cache 20;

-- Create Index  --> clob can not create index
-- create index index_big_data on WMS_IN_POOL(BIG_DATA);

commit;
