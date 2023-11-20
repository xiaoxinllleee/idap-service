package org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.service;

import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.entity.Qhxdgt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface IQhxdgtService extends IService<Qhxdgt> {
    public void extract(String ksrq,String jsrq,String zzbz);
}
