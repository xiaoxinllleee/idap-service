package org.cmms.modules.tjfx.zfsjmx.qhsjmx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.zfsjmx.qhsjmx.entity.ZfsjmxQh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行走访数据明细统计
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
public interface ZfsjmxQhMapper extends BaseMapper<ZfsjmxQh> {

    void InitDataToQh(Map<String, String> sql);

}
