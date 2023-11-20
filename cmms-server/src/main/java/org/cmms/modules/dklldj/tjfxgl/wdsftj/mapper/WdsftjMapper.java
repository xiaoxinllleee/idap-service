package org.cmms.modules.dklldj.tjfxgl.wdsftj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.wdsftj.entity.Wdsftj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 网点上浮统计
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
public interface WdsftjMapper extends BaseMapper<Wdsftj> {

    void init();

}
