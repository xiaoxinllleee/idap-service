package org.cmms.modules.pad.shxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.shxxgl.entity.Fxezh;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.Xyk;

import java.util.List;

/**
 * @Description: 福祥E支付
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
public interface IFxezhService extends IService<Fxezh> {
    IPage<Fxezh> getByWgbh(Page page, String wgbh);
    List<Fxezh> getByWgbhList( String wgbh);
    IPage<Fxezh> getByWgbhTy(Page page, List<String> wgbhList);
}
