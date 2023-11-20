package org.cmms.modules.khgl.ezf.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.ezf.entity.Ezf;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: E支付
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@DS("eweb")
public interface IEzfService extends IService<Ezf> {

    String getDate();
}
