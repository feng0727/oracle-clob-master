package com.gblfy.service;

import com.gblfy.entity.WmsInPool;

import java.math.BigDecimal;
import java.util.List;
import com.gblfy.entity.Policy;
public interface WmsInPoolService {

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    public String batchInsertWmsInPool(List<WmsInPool> list);
    public String selectOracleClobDataToJsonData(BigDecimal poolPkNo);

    public String batchInsertWmsInPool2(List<WmsInPool> wmsInPoolList);

    String  batchUpdateData(List<WmsInPool> wmsInPoolList);

    List<WmsInPool> batchQuery();

    int batchInserSupport(List<WmsInPool> wmsInPoolList);

    int updateBatchFolder(List<WmsInPool> wmsInPoolList);

    List<Policy> matchDgree(List<String> collect);

    List<Policy> selectClobById(List<Integer> asList);

    int matchDgreeBySel(List<Policy> policyListBids);
}
