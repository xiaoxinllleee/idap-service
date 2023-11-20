package org.cmms.modules.khxxgl.clkhxx.service;

import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存量客户管理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IClkhglService extends IService<Clkhgl> {
    public void extract();

    public int syncYesClkhxx();
}
