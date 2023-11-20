package org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.tdrqcklsjk.entity.CkjkptTdrqcklsjk;

/**
 * @Description: 特定人群存款流水监控
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptTdrqcklsjkService extends IService<CkjkptTdrqcklsjk> {
    public void extract(String tjyf);
}
