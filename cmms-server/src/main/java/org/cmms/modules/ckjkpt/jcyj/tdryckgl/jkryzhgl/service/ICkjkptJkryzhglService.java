package org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.tdryckgl.jkryzhgl.entity.CkjkptJkryzhgl;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptJkryzhglService extends IService<CkjkptJkryzhgl> {
    public CkjkptJkryzhgl queryByZjhm(String zjhm);

    public void extract(Map<String,String> sql);
}
