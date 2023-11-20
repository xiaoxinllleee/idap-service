package org.cmms.modules.dklldj.lldjgl.lldjsqHj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqxxHj;

import java.math.BigDecimal;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@DS("eweb")
public interface ILldjsqHjService extends IService<RateDjsqxxHj> {
    BigDecimal querySnzxll(@Param("tablename") String tablename,
                           @Param("zjhm") String zjhm);

    /**
     * 获取个人客户存款日平余额 / 包含关联人
     * 洪江贷款利率定价
     *
     * @param tablename
     * @param zjhm
     * @return
     */
    BigDecimal queryCkrpyeSumGR(@Param("tablename") String tablename,
                                @Param("zjhm") String zjhm);

    /**
     * 获取企业客户存款日平余额
     * 洪江贷款利率定价
     *
     * @param tablename
     * @param zjhm
     * @return
     */
    BigDecimal queryCkrpyeSumQY(@Param("tablename") String tablename,
                                @Param("zjhm") String zjhm);
}
