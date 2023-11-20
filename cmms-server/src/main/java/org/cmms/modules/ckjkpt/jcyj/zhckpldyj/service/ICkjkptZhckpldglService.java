package org.cmms.modules.ckjkpt.jcyj.zhckpldyj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.entity.CkjkptZhckpldgl;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptZhckpldglService extends IService<CkjkptZhckpldgl> {
    public void extract(String tjyf);
}
