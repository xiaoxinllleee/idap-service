package org.cmms.modules.dkjkpt.tjcx.qhdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjNb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行表内贷款统计(年报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IQhbndktjNbService extends IService<QhbndktjNb> {
    public void  extract(Map<String,String> sql);
}
