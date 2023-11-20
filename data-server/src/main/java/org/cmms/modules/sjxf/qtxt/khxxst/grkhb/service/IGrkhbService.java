package org.cmms.modules.sjxf.qtxt.khxxst.grkhb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;

/**
 * @Description: 个人客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@DS("sjxf")
public interface IGrkhbService extends IService<Grkhb> {

    /**
     * 获取被调查人基本信息
     *
     * @param custid
     * @return
     */
    Grkhb getGrkhbHive(@Param("custid") String custid);

    Grkhb getGrkhbOracle (@Param("custid") String custid);
}
