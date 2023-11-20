package org.cmms.modules.dkjkpt.bldkftjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsnm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptBldkftjkBsnmService extends IService<DkjkptBldkftjkBsnm> {

    public void extract(String tjyf);
}
