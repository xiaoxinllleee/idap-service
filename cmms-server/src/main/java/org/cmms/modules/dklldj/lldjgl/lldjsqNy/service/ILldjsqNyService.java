package org.cmms.modules.dklldj.lldjgl.lldjsqNy.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
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
@DS("eweb")
public interface ILldjsqNyService extends IService<RateDjsqxxNy> {

//    BigDecimal queryColumnValue(@Param("tablename") String tablename, @Param("columnname") String columnname, @Param("zjhm") String zjhm);

    BigDecimal querySnzxll(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    BigDecimal querySndkrp(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    /**
     * 获取个人客户存款日平余额 / 包含关联人
     * 洪江贷款利率定价
     *
     * @param tablename
     * @param zjhm
     * @return
     */
    BigDecimal queryCkrpyeSumGR(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    /**
     * 获取企业客户存款日平余额
     * 洪江贷款利率定价
     *
     * @param tablename
     * @param zjhm
     * @return
     */
    BigDecimal queryCkrpyeSumQY(@Param("tablename") String tablename, @Param("zjhm") String zjhm);

    void ExtractionGlzhxx(@Param("zjhm") String zjhm);

    void ExtractionKhzbsj(@Param("djnf") String djnf, @Param("zjhm") String zjhm);

    void ExtractionJynrp(@Param("djnf") String djnf, @Param("zjhm") String zjhm);

    List<RateZhckrp> QueryKhZhCkrp(@Param("tjlx") String tjlx, @Param("djnf") String djnf, @Param("zjhm") String zjhm);

    RateKhzbsjmx QueryKhZbsjmx(@Param("djnf") String djnf, @Param("zjhm") String zjhm);
}
