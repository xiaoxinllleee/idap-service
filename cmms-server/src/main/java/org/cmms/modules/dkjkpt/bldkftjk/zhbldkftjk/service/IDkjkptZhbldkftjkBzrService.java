package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBzr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行不良贷款反弹监控（比昨日）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptZhbldkftjkBzrService extends IService<DkjkptZhbldkftjkBzr> {

    public void initData(String tjyf);

}
