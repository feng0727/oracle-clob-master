package com.gblfy.service.impl;

import com.alibaba.fastjson.JSON;
import com.gblfy.entity.WmsInPool;
import com.gblfy.mapper.WmsInPoolMapper;
import com.gblfy.service.WmsInPoolService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

@Service
public class WmsInPoolServiceImpl implements WmsInPoolService {

    private static final Logger logger = LoggerFactory.getLogger(WmsInPoolServiceImpl.class);

    @Resource
    private WmsInPoolMapper wmsInPoolMapper;

    //（大数据量）推荐使用
    @Override
    @Transactional
    public String batchInsertWmsInPool(List<WmsInPool> list) {
        //第二种方案:
        int addFlag = 0;
        long start1 = System.currentTimeMillis();
        if (list != null && list.size() > 0) {
            //第二种方案:
            try {
                addFlag = wmsInPoolMapper.batchInsertWmsInPool(list);
            } catch (Exception e) {
                logger.error("批量新增wms_in_pool表失败,原因为:" + e.getMessage(), e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new RuntimeException("批量新增wms_in_pool表失败,原因为:" + e.getMessage());
            }
        }

        long start2 = System.currentTimeMillis();
        logger.info("批量新增wms_in_pool表【{}】条数据成功,一共耗时({})毫秒！", addFlag, (start2 - start1));

        //返回结果
        return "批量新增wms_in_pool表【" + addFlag + "】条数据成功,一共耗时=" + (start2 - start1) + "毫秒！";
    }

    @Override
    @Transactional
    public String selectOracleClobDataToJsonData(BigDecimal poolPkNo) {
        WmsInPool wms = wmsInPoolMapper.selectByPrimaryKey(poolPkNo);
        return JSON.toJSONString(wms);
    }

    @Override
    @Transactional
    public String batchInsertWmsInPool2(List<WmsInPool> wmsInPoolList) {

            //第二种方案:
            int addFlag = 0;
            long start1 = System.currentTimeMillis();
            if (wmsInPoolList != null && wmsInPoolList.size() > 0) {
                //第二种方案:
                try {
                    addFlag = wmsInPoolMapper.batchInsertWmsInPool2(wmsInPoolList);
                } catch (Exception e) {
                    logger.error("批量新增wms_in_pool表失败,原因为:" + e.getMessage(), e);
                    throw new RuntimeException("批量新增wms_in_pool表失败,原因为:" + e.getMessage());
                }
            }

            long start2 = System.currentTimeMillis();
            logger.info("批量新增wms_in_pool表【{}】条数据成功,一共耗时({})毫秒！", addFlag, (start2 - start1));

            //返回结果
            return "批量新增wms_in_pool表【" + addFlag + "】条数据成功,一共耗时=" + (start2 - start1) + "毫秒！";

    }



    @Override
    @Transactional
    public String batchUpdateData(List<WmsInPool> wmsInPoolList) {

        //第二种方案:
        int addFlag = 0;
        long start1 = System.currentTimeMillis();
        if (wmsInPoolList != null && wmsInPoolList.size() > 0) {
            //第二种方案:
            try {
                addFlag = wmsInPoolMapper.batchUpdateData(wmsInPoolList);
            } catch (Exception e) {
                logger.error("批量新增wms_in_pool表失败,原因为:" + e.getMessage(), e);
                throw new RuntimeException("批量新增wms_in_pool表失败,原因为:" + e.getMessage());
            }
        }

        long start2 = System.currentTimeMillis();
        logger.info("批量新增wms_in_pool表【{}】条数据成功,一共耗时({})毫秒！", addFlag, (start2 - start1));
        //返回结果
        return  "批量修改wms_in_pool表【" + addFlag + "】条数据成功,一共耗时=" + (start2 - start1) + "毫秒！";

    }

    @Override
    public List<WmsInPool> batchQuery() {
        List<Integer> ids=new ArrayList<>();
        ids.add(102);
        ids.add(103);
        return wmsInPoolMapper.batchQuery(ids);
    }


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public int batchInserSupport(List<WmsInPool> wmsInPoolList) {

        //获取sql会话
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        // 通过新的session获取mapper，而不是常规的spring管理注入
        WmsInPoolMapper wmsMapper = session.getMapper(WmsInPoolMapper.class);
        int size = wmsInPoolList.size();
        // 如果有父类子类两层都需要批量插入也可
        try {
            // 外层循环
            for (int i = 0; i < size; i++) {
                //   用上面在session中获取的mapper进行插入操作
                wmsMapper.insertFolder(wmsInPoolList.get(i));
                //    最后批量提交
                if (i % 200 == 0 || i == size - 1) {
                    session.commit(); //200个提交一次，手动提交，提交后无法回滚
                    session.clearCache(); // 清理缓存，防止溢出
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
            session.rollback();  //没有提交的数据可以回滚
        } finally {
            session.close();
        }
        return 0;
    }
    @Override
    public int updateBatchFolder(List<WmsInPool> wmsInPoolList) {
        //获取sql会话
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        // 通过新的session获取mapper，而不是常规的spring管理注入
        WmsInPoolMapper wmsMapper = session.getMapper(WmsInPoolMapper.class);
        int size = wmsInPoolList.size();
        // 如果有父类子类两层都需要批量插入也可
        try {
            // 外层循环
            for (int i = 0; i < size; i++) {
                //   用上面在session中获取的mapper进行插入操作
                wmsMapper.updateBatchFolder(wmsInPoolList.get(i));
                //    最后批量提交
                if (i % 200 == 0 || i == size - 1) {
                    session.commit(); //200个提交一次，手动提交，提交后无法回滚
                    session.clearCache(); // 清理缓存，防止溢出
                }
            }
        }catch (Exception e) {
            System.out.println(e.toString());
            session.rollback();  //没有提交的数据可以回滚
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public List<Policy> matchDgree(List<String> collect) {
        return wmsInPoolMapper.matchDgree(collect);
    }

    // @Transactional
    // public String batchInsertWmsInPool2(List<WmsInPool> list) {
    //     int addFlag = 0;
    //     long start1 = System.currentTimeMillis();
    //     if (list != null && list.size() > 0) {
    //         //第一种方案:
    //         for (WmsInPool addWmsInPool : list) {
    //             addFlag++;
    //             try {
    //                 logger.info("新增wms_in_pool表参数=[" + ReflectionToStringBuilder.toString(addWmsInPool) + "]");
    //                 addFlag = wmsInPoolMapper.insert(addWmsInPool);
    //             } catch (Exception e) {
    //                 logger.error("单个新增wms_in_pool表失败,原因为:" + e.getMessage(), e);
    //                 throw new RuntimeException("单个新增wms_in_pool表失败,原因为:" + e.getMessage());
    //             }
    //
    //             //判断新增结果
    //             if (addFlag != 1) {
    //                 logger.error("单个新增wms_in_pool表失败,原因为:数据库没有执行任何操作!");
    //                 throw new RuntimeException("单个新增wms_in_pool表失败,原因为:数据库没有执行任何操作!");
    //             }
    //         }//END for
    //     }
    //
    //     long start2 = System.currentTimeMillis();
    //     logger.info("批量新增wms_in_pool表【{}】条数据成功,一共耗时({})毫秒！", addFlag, (start2 - start1));
    //
    //     //返回结果
    //     return "批量新增wms_in_pool表【" + addFlag + "】条数据成功,一共耗时=" + (start2 - start1) + "毫秒！";
    // }
}
