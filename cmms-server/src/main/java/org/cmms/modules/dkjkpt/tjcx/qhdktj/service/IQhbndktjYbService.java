package org.cmms.modules.dkjkpt.tjcx.qhdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.qhdktj.entity.QhbndktjYb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行表内贷款统计(月报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IQhbndktjYbService extends IService<QhbndktjYb> {
    public void  extract(Map<String,String> sql);
}
