package org.cmms.modules.ywgl.ywl.ywltqytj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ywl.ywltqytj.entity.Ywltqytj;

/**
 * @Description: 业务量提取与统计
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
public interface YwltqytjMapper extends BaseMapper<Ywltqytj> {

    void pYwltqytj(@Param("tjyf") String tjyf, @Param("zzbz") String zzbz);

    /**
     * 获取`员工业务量明细统计数据表`最大分配ID
     * @return
     */
    String getMaxFpid();

    /**
     * 获取绩效考核参数
     * @param paramcode 参数编码
     * @return
     */
    String getAssessParamValue(@Param("paramcode") String paramcode);

    /**
     * 读取折算配置信息表
     * @param beginOfMonth 月初日期：yyyyMMdd
     * @return
     */
    String conversionConfigInfo(@Param("beginOfMonth") Date beginOfMonth);
}
