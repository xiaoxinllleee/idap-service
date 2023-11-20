package org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.cscqzhbe.entity.TjfxCscqzhbe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 村社村前统计支行表二
 * @Author: cmms
 * @Date:   2019-12-14
 * @Version: V1.0
 */
public interface TjfxCscqzhbeMapper extends BaseMapper<TjfxCscqzhbe> {

    public void extract(String tjyf);

}
