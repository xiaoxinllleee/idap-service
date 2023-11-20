package org.cmms.modules.bigscreen.service;

import org.cmms.modules.bigscreen.entity.VDpYwhz;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjd;
import org.cmms.modules.bigscreen.entity.Xzzcsxgzjdcun;

import java.util.List;

/**
 * @Description: 大屏业务汇总视图
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
public interface IVDpYwhzService extends IService<VDpYwhz> {

    List<Xzzcsxgzjd> getMaxList();
    List<Xzzcsxgzjdcun> getCunList(String wgbh);
}
