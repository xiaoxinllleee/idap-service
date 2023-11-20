package org.cmms.modules.tjfx.wgywsjtj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.tjfx.wgywsjtj.entity.VKhxxglTjfxWgywsjtj;

import java.util.List;

/**
 * @Description: 网格业务数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
public interface IVKhxxglTjfxWgywsjtjService extends IService<VKhxxglTjfxWgywsjtj> {

    IPage<Kjbxx> getKjbxxListByWgbh(Page page,String wgbh);

    List<Kjbxx> getKjbxxListByWgbh(String wgbh);
}
