package org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.entity.Tjfx_shjrphsj_qh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行金融普惠数据
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
public interface Tjfx_shjrphsj_qhMapper extends BaseMapper<Tjfx_shjrphsj_qh> {
    void InitDataToQh(Map<String, String> sql);

}
