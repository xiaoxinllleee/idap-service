package org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.service;

import org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.entity.TjfxXdxtkhsxyctjQh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
public interface ITjfxXdxtkhsxyctjQhService extends IService<TjfxXdxtkhsxyctjQh> {

    public  void extract(String tjyf);

}
