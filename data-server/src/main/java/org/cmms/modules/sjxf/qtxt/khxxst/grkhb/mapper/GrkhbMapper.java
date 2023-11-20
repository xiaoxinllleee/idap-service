package org.cmms.modules.sjxf.qtxt.khxxst.grkhb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;

/**
 * @Description: 个人客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
public interface GrkhbMapper extends BaseMapper<Grkhb> {

    Grkhb getGrkhbHive(@Param("custid") String custid);

    Grkhb getGrkhbOracle (@Param("custid") String custid);
}
