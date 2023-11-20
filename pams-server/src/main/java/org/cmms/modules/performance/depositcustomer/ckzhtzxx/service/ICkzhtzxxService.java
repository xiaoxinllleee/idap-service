package org.cmms.modules.performance.depositcustomer.ckzhtzxx.service;

import org.cmms.modules.performance.depositcustomer.ckzhtzxx.entity.Ckzhtzxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款账户拓展信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
public interface ICkzhtzxxService extends IService<Ckzhtzxx> {
    public void extract();
}
