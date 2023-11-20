package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsnm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptZhbldkftjkBsnmService extends IService<DkjkptZhbldkftjkBsnm> {

    public void initData(String tjyf);

}
