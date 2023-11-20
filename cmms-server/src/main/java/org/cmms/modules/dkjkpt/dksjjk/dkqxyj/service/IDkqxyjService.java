package org.cmms.modules.dkjkpt.dksjjk.dkqxyj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.entity.Dkqxyj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款欠息预警
 * @Author: jeecg-boot
 * @Date:   2022-09-15
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkqxyjService extends IService<Dkqxyj> {

    public void initData( String userName);
}
