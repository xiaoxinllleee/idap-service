package org.cmms.modules.sjxf.qtxt.khxxst.khxxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;

/**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
public interface KhxxbMapper extends BaseMapper<Khxxb> {

    Khxxb getKhxxbHive(@Param("zjhm") String zjhm);

    Khxxb getKhxxbOracle(@Param("zjhm") String zjhm);
}
