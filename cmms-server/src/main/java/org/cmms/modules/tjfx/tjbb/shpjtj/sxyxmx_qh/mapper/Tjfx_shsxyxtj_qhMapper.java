package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.entity.Tjfx_shsxyxtj_qh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
public interface Tjfx_shsxyxtj_qhMapper extends BaseMapper<Tjfx_shsxyxtj_qh> {
    void InitDataToQh(Map<String, String> sql);

}
