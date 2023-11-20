package org.cmms.modules.ywgl.cdkfx.khjlcdktj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdktj.entity.ErpYljcKhjldkkhzbtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理指标统计
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
public interface ErpYljcKhjldkkhzbtjMapper extends BaseMapper<ErpYljcKhjldkkhzbtj> {

    void pAutoExec(@Param("jgdm")String jgdm,@Param("tjyf")String tjyf,@Param("username")String username);

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
}
