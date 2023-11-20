package org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.xjlghjcajfp.entity.DkjkptXjlghtjAjfp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 现金流归行监测（按揭、扶贫）
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptXjlghtjAjfpService extends IService<DkjkptXjlghtjAjfp> {
    public void extract(Map<String,String> sql);
}
