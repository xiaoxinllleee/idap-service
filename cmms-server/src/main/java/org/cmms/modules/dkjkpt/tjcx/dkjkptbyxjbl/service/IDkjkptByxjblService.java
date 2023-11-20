package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.entity.DkjkptByxjbl;

/**
 * @Description: 本月新进不良
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptByxjblService extends IService<DkjkptByxjbl> {
    public void extract(String tjyf);
}
