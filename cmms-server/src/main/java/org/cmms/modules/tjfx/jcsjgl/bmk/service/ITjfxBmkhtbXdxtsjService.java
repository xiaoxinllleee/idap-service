package org.cmms.modules.tjfx.jcsjgl.bmk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.bmk.entity.TjfxBmkhtbXdxtsj;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
public interface ITjfxBmkhtbXdxtsjService extends IService<TjfxBmkhtbXdxtsj> {
    public TjfxBmkhtbXdxtsj queryMsg(String zjhm, String jgdm, String tjyf);

//    public int deleteMsg(String zjhm,String jgdm,String tjyf);
public int deleteMsg(String tjyf);

public  void deletemian(@Param("tjyf") Date tjyf);

}
