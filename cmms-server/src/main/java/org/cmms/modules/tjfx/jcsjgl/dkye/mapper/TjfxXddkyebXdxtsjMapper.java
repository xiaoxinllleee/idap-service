package org.cmms.modules.tjfx.jcsjgl.dkye.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
public interface TjfxXddkyebXdxtsjMapper extends BaseMapper<TjfxXddkyebXdxtsj> {

    public TjfxXddkyebXdxtsj queryMsg(@Param("zjhm")String zjhm,@Param("jgdm")String jgdm,@Param("tjyf")String tjyf);

    public int deleteMsg(@Param("tjyf")String tjyf);

    public void deletemain(Date tjyf);


}
