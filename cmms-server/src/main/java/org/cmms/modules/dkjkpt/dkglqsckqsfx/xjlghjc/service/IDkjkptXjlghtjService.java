package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjc.entity.DkjkptXjlghtj;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 现金流归行监测
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptXjlghtjService extends IService<DkjkptXjlghtj> {
    public void extract(Map<String,String> sql);
}
