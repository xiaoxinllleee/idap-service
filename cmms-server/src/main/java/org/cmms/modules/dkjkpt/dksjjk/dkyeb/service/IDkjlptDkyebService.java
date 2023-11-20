package org.cmms.modules.dkjkpt.dksjjk.dkyeb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.entity.DkjlptDkyeb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjlptDkyebService extends IService<DkjlptDkyeb> {

    void InitData(String tjrq);

}
