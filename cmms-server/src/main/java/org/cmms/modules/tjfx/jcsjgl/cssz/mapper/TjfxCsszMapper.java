package org.cmms.modules.tjfx.jcsjgl.cssz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface TjfxCsszMapper extends BaseMapper<TjfxCssz> {

    String queryCszByCsbm(String csbm);

}
