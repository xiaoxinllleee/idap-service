package org.cmms.modules.ckjkpt.jcyj.ckyebd.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.ckyebd.entity.CkjkptCkyebd;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptCkyebdService extends IService<CkjkptCkyebd> {
    public void extract(Map<String,String> sql);
}
