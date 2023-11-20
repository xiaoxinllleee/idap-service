package org.cmms.modules.dklldj.lldjgl.lldjsqNy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.entity.RateKhzbsjmx;
import org.cmms.modules.dklldj.lldjgl.entity.RateZhckrp;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDjsqxxNy;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
public interface LldjsqNyMapper extends BaseMapper<RateDjsqxxNy> {

//    BigDecimal queryColumnValue(@Param("tablename") String tablename, @Param("columnname") String columnname, @Param("zjhm") String zjhm);

    BigDecimal querySnzxll(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    BigDecimal querySndkrp(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    BigDecimal queryCkrpyeSumGR(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    BigDecimal queryCkrpyeSumQY(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    void ExtractionGlzhxx(@Param("zjhm") String zjhm);

    void ExtractionKhzbsj(@Param("djnf") String djnf, @Param("zjhm") String zjhm);

    void ExtractionJynrp(@Param("djnf") String djnf, @Param("zjhm") String zjhm);

    List<RateZhckrp> QueryKhZhCkrp(@Param("tjlx") String tjlx, @Param("djnf") String djnf, @Param("zjhm") String zjhm);

    RateKhzbsjmx QueryKhZbsjmx(@Param("djnf") String djnf, @Param("zjhm") String zjhm);

}
