package org.cmms.modules.tjfx.qhywjd.qhckqk.service;

import org.cmms.modules.tjfx.qhywjd.qhckqk.entity.TjfxQhckqk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 全行存款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface ITjfxQhckqkService extends IService<TjfxQhckqk> {
    void initData(String sjrq,String yggh);
    Date getMaxDate();
    TjfxQhckqk getHjDate(String sjrq,String sszh);
}
