package org.cmms.modules.tjfx.tjbb.khywbb.service;

import org.cmms.modules.tjfx.tjbb.khywbb.entity.Qhkhywbb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户业务报表(全行)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
public interface IQhkhywbbService extends IService<Qhkhywbb> {
    public void extract();
    public void extractRC(String zxrkrq);
}
