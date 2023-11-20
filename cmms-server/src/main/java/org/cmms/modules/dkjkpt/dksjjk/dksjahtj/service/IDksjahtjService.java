package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dksjahtj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款数据按户统计
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDksjahtjService extends IService<Dksjahtj> {

    void InitData(String tjyf);

}
