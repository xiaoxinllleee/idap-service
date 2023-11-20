package org.cmms.modules.tjfx.jcsjgl.dkye.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.dkye.entity.TjfxXddkyebXdxtsj;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
public interface ITjfxXddkyebXdxtsjService extends IService<TjfxXddkyebXdxtsj> {
    public TjfxXddkyebXdxtsj queryMsg(String zjhm,String jgdm,String tjyf);
    public int deleteMsg(String tjyf);
    public void deletemain(Date tjyf);

}
