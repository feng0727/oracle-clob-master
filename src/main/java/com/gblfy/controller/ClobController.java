package com.gblfy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gblfy.entity.Policy;
import com.gblfy.entity.WmsInPool;
import com.gblfy.service.WmsInPoolService;
import com.google.common.collect.Lists;
import com.hankcs.hanlp.HanLP;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 批量json数据保存到oracle的clob字段中
 *
 * @author gblfy
 * @date 2021-11-05
 */
@RestController
public class ClobController {

    @Resource
    private WmsInPoolService wms;

    /**
     * 批量json数据保存到oracle的clob字段中
     * http://localhost:8080/batchSaveJsonDataToOracleClob
     */
    @GetMapping("/batchSaveJsonDataToOracleClob")
    public String batchSaveJsonDataToOracleClob() {
        List<WmsInPool> wmsInPoolList = generateData();
        return wms.batchInsertWmsInPool(wmsInPoolList);
    }

    /**
     * 批量保存不使用自增主键
     * @param POOL_PK_NO
     * @return
     */
    @GetMapping("/batchInsertWmsInPoolUnUseKeys")
    public String batchInsertWmsInPoolUnUseKeys() {
        List<WmsInPool> wmsInPoolList = generateData2();
        return wms.batchInsertWmsInPool2(wmsInPoolList);
    }



    @GetMapping("/selectOracleClobDataToJsonData")
    public String selectOracleClobDataToJsonData(@RequestParam("POOL_PK_NO") int POOL_PK_NO) {
        BigDecimal bigDecimalValue = new BigDecimal(POOL_PK_NO);
        return wms.selectOracleClobDataToJsonData(bigDecimalValue);
    }

    @GetMapping("/batchUpdateData")
    public String batchUpdateData() {
        List<WmsInPool> wmsInPoolList = generateData3();
        return  wms.batchUpdateData(wmsInPoolList);
    }

    private List<WmsInPool> generateData3() {
        List<WmsInPool> wmsInPoolList = new ArrayList<WmsInPool>();
        for (int i = 41; i < 45; i++) {
            WmsInPool wmsInPool = new WmsInPool();
            Map<String, Object> map = new HashMap<>();
            map.put("webSite", "gblfy.com.cn");
            wmsInPool.setBigData(JSON.toJSONString(map));
            wmsInPool.setPoolPkNo(new BigDecimal(i));
            wmsInPool.setCreateTime(new Date());
            wmsInPool.setUpdateTime(new Date());
            wmsInPoolList.add(wmsInPool);
        }
        return wmsInPoolList;
    }

    /**
     *  批量查询
     */
    @GetMapping("/batchSelect")
    public String batchSelect(){
       List<WmsInPool> wmsInPoolList=wms.batchQuery();
       return wmsInPoolList.toString();
    }


    /**
     * 模拟生成批量json数据
     *
     * @return
     */
    private List<WmsInPool> generateData() {
        List<WmsInPool> wmsInPoolList = new ArrayList<WmsInPool>();
        for (int i = 0; i < 1000; i++) {
            WmsInPool wmsInPool = new WmsInPool();
            Map<String, Object> map = new HashMap<>();
            map.put("webSite", "gblfy.com");
            wmsInPool.setBigData(JSON.toJSONString(map));
            wmsInPool.setCreateTime(new Date());
            wmsInPool.setUpdateTime(new Date());
            wmsInPoolList.add(wmsInPool);
        }

        return wmsInPoolList;
    }

    private List<WmsInPool> generateData2() {
        List<WmsInPool> wmsInPoolList = new ArrayList<WmsInPool>();
        for (int i = 0; i < 10; i++) {
            WmsInPool wmsInPool = new WmsInPool();
            Map<String, Object> map = new HashMap<>();
            map.put("webSite", "gblfy.com");
            wmsInPool.setBigData(JSON.toJSONString(map));
            wmsInPool.setCreateTime(new Date());
            wmsInPool.setUpdateTime(new Date());
            wmsInPool.setPoolPkNo(testDeci());
            wmsInPoolList.add(wmsInPool);
        }

        return wmsInPoolList;
    }



    public BigDecimal testDeci(){
        BigDecimal randomRedPacketBetweenMinAndMax = getRandomRedPacketBetweenMinAndMax(new BigDecimal(Integer.MIN_VALUE), new BigDecimal(Integer.MAX_VALUE));
        return   randomRedPacketBetweenMinAndMax.abs().multiply(new BigDecimal(100));
    }

    /**
     * 获取金额
     * @param min
     * @param max
     * @return
     */
    public static BigDecimal getRandomRedPacketBetweenMinAndMax(BigDecimal min, BigDecimal max){
        float minF = min.floatValue();
        float maxF = max.floatValue();

        //生成随机数
        BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);

        //返回保留两位小数的随机数。不进行四舍五入
        return db.setScale(2,BigDecimal.ROUND_DOWN);
    }



    // 二、用Mybatis 的 Batch Insert Support 批量插入

    @RequestMapping("insertBatchFolder")
    public int insertFolder() {
        List<WmsInPool> wmsInPoolList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            WmsInPool wmsInPool = new WmsInPool();
            Map<String, Object> map = new HashMap<>();
            map.put("webSite", "guava.com");
            wmsInPool.setBigData(JSON.toJSONString(map));
            wmsInPool.setCreateTime(new Date());
            wmsInPool.setUpdateTime(new Date());
            wmsInPool.setPoolPkNo(testDeci());
            wmsInPoolList.add(wmsInPool);
        }
       return   wms.batchInserSupport(wmsInPoolList);
  }


    @RequestMapping("updateBatchFolder")
    public int updateBatchFolder() {
        List<WmsInPool> wmsInPoolList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            WmsInPool wmsInPool = new WmsInPool();
            Map<String, Object> map = new HashMap<>();
            map.put("webSite", "guava.update.com");
            wmsInPool.setBigData(JSON.toJSONString(map));
            wmsInPool.setCreateTime(new Date());
            wmsInPool.setUpdateTime(new Date());
            wmsInPool.setPoolPkNo(new BigDecimal(i+1));
            wmsInPoolList.add(wmsInPool);
        }
        return   wms.updateBatchFolder(wmsInPoolList);
    }

    @RequestMapping("matchDgree")
    public Object matchDgree(){
        String text="河南省新能源公司";
        List<String> collect = HanLP.segment(text)
                .stream()
                .map(term -> term.word)
                .collect(Collectors.toList());
       List<Policy>  policyList= wms.matchDgree(collect);
       return policyList;
    }

    //在原值上添加数据
    @RequestMapping("updateScore")
    public Object updateScore(){
        Integer[] ints2 = new Integer[]{9,10,11};
        List<Policy>  policyListBids=wms.selectClobById(Arrays.asList(ints2));
        int  updateCount=wms.matchDgreeBySel(policyListBids);
        return updateCount;
    }


}
