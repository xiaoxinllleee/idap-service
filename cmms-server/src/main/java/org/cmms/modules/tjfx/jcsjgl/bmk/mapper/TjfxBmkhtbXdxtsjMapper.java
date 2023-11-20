package org.cmms.modules.tjfx.jcsjgl.bmk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.bmk.entity.TjfxBmkhtbXdxtsj;
import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import org.springframework.security.core.parameters.P;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
public interface TjfxBmkhtbXdxtsjMapper extends BaseMapper<TjfxBmkhtbXdxtsj> {

    public TjfxBmkhtbXdxtsj queryMsg(@Param("zjhm")String zjhm, @Param("jgdm")String jgdm, @Param("tjyf")String tjyf);

//    public int deleteMsg(@Param("zjhm")String zjhm,@Param("jgdm")String jgdm,@Param("tjyf")String tjyf);
public int deleteMsg(@Param("tjyf")String tjyf);

public  void deletemian(@Param("tjyf") Date tjyf);
}
