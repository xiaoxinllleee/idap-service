package org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjgz.entity.DkjkptByxzgl;

/**
 * @Description: 本月新进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-30
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptByxzglService extends IService<DkjkptByxzgl> {
    public void extract(String tjyf);
}
