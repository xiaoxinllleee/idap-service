package org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.service;

import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.entity.Qhxdgtzyb2;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行行动挂图作业表2
 * @Author: jeecg-boot
 * @Date:   2020-03-13
 * @Version: V1.0
 */
public interface IQhxdgtzyb2Service extends IService<Qhxdgtzyb2> {
    public void extract(String tjyf);
}
