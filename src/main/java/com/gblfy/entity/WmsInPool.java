package com.gblfy.entity;

import java.math.BigDecimal;
import java.util.Date;


public class WmsInPool implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id*/
    private BigDecimal poolPkNo;

    /** clob类型数据字段*/
    private String bigData;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

    public BigDecimal getPoolPkNo() {
        return poolPkNo;
    }

    public void setPoolPkNo(BigDecimal poolPkNo) {
        this.poolPkNo = poolPkNo;
    }

    public String getBigData() {
        return bigData;
    }

    public void setBigData(String bigData) {
        this.bigData = bigData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WmsInPool{" +
                "poolPkNo=" + poolPkNo +
                ", bigData='" + bigData + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
