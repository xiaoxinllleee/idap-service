package org.cmms.modules.tjfx.qhywjd.qhdkqk.service;

import org.cmms.modules.tjfx.qhywjd.qhdkqk.entity.TjfxQhdkqk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 全行贷款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface ITjfxQhdkqkService extends IService<TjfxQhdkqk> {
    void initData(String sjrq,String yggh);
    Date getMaxDate();
    TjfxQhdkqk getHjDate(String sjrq, String sszh);
}
