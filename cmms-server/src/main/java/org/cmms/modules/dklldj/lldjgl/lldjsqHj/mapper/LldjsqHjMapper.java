package org.cmms.modules.dklldj.lldjgl.lldjsqHj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqxxHj;

import java.math.BigDecimal;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
public interface LldjsqHjMapper extends BaseMapper<RateDjsqxxHj> {
    BigDecimal querySnzxll(@Param("tablename") String tablename,
                           @Param("zjhm") String zjhm);

    BigDecimal queryCkrpyeSumGR(@Param("tablename") String tablename,
                                @Param("zjhm") String zjhm);

    BigDecimal queryCkrpyeSumQY(@Param("tablename") String tablename,
                                @Param("zjhm") String zjhm);
}
