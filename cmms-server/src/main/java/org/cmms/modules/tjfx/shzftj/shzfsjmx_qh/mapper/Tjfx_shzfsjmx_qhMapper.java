package org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.entity.Tjfx_shzfsjmx_qh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商户走访数据明细统计_全行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
public interface Tjfx_shzfsjmx_qhMapper extends BaseMapper<Tjfx_shzfsjmx_qh> {
    void InitDataToQh(Map<String, String> sql);

}
