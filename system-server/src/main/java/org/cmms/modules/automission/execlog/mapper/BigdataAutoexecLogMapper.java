package org.cmms.modules.automission.execlog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.automission.execlog.vo.ErpBasWyxcsszVO;

/**
 * @Description: 大数据应用平台每日调度日志信息
 * @Author: jeecg-boot
 * @Date:   2021-12-31
 * @Version: V1.0
 */
public interface BigdataAutoexecLogMapper extends BaseMapper<BigdataAutoexecLog> {

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

    /**
     * 获取系统配置参数值
     * @param cfgcode 系统配置参数编码
     * @return cfgvalue
     */
    String getSystemConfigParamValue(@Param("cfgcode") String cfgcode);

    /**
     * 获取系统配置参数值
     * @param cfgcode 系统配置参数编码
     * @return cfgvaluenumber
     */
    String getSystemConfigParamValueNumber(@Param("cfgcode") String cfgcode);

    /**
     * 获取参数值
     * @param csbm 参数编码
     * @return csz
     */
    String getDailyLoanLimit(@Param("csbm") String csbm);

    /**
     * 从`sys_bas_cfg`获取业务科目号
     * @param cfgcode 业务参数代码
     * @return
     */
    String querySubjectNo(@Param("cfgcode") String cfgcode);

    /**
     * 获取贷款当月日平参数设置信息
     * @param qybz 启用标识
     * @return
     */
    ErpBasWyxcsszVO getOneErpBasWyxcssz(@Param("qybz") String qybz);

    /**
     * 获取存款账号关联信息最大关联id
     * 拿到该列最大值id sql 里面已加1
     * @return
     */
    String getMaxId();

    /**
     * 判断这个存储是走哪一个
     * @return
     */
    Integer judgeExtract();
}
