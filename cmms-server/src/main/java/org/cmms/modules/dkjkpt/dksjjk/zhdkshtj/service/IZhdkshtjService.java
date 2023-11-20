package org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.zhdkshtj.entity.Zhdkshtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行贷款收回统计
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IZhdkshtjService extends IService<Zhdkshtj> {
    public void init(String tjyf);

}
