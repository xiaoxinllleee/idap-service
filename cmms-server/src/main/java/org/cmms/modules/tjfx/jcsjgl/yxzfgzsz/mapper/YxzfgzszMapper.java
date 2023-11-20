package org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.entity.Yxzfgzsz;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;

/**
 * @Description: 农户有效走访规则设置
 * @Author: jeecg-boot
 * @Date:   2022-01-17
 * @Version: V1.0
 */
public interface YxzfgzszMapper extends BaseMapper<Yxzfgzsz> {
    List<Yxzfmx> getZfzbxxByHhbm(String hhbm,String tjrq);
}
