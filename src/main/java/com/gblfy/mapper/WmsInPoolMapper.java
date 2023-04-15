package com.gblfy.mapper;

import com.gblfy.entity.WmsInPool;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import com.gblfy.entity.Policy;
import java.util.List;

public interface WmsInPoolMapper {
    /**
     *
     * @param poolPkNo
     * @return
     */
    public int deleteByPrimaryKey(BigDecimal poolPkNo);

    /**
     *
     * @param record
     * @return
     */
    public int insert(WmsInPool record);

    /**
     *
     * @param record
     * @return
     */
    public int insertSelective(WmsInPool record);

    /**
     *
     * @param poolPkNo
     * @return
     */
    public WmsInPool selectByPrimaryKey(BigDecimal poolPkNo);

    /**
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(WmsInPool record);

    /**
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKey(WmsInPool record);

    /**
     * @param wmsInPools
     * @return
     */
    public int batchInsertWmsInPool(@Param("wmsInPools") List<WmsInPool> wmsInPools);

    int batchInsertWmsInPool2(@Param("wmsInPools")List<WmsInPool> wmsInPoolList);

    int batchUpdateData(@Param("sfpchList") List<WmsInPool> wmsInPoolList);

    List<WmsInPool> batchQuery(@Param("ids") List<Integer> ids);

    void insertFolder(@Param("wmsInPool") WmsInPool wmsInPool);

    void updateBatchFolder(@Param("wmsInPool")WmsInPool wmsInPool);

    List<Policy> matchDgree(@Param("content")List<String> content);

    List<Policy> selectClobById(@Param("idsData") List<Integer> idsData);

   int matchDgreeBySel(@Param("idList") List<Policy> policyListByIds);
}
