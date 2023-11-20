package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行不良贷款反弹监控（比上月）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptZhbldkftjkBsyService extends IService<DkjkptZhbldkftjkBsy> {

    public void initData(String tjyf);

}
