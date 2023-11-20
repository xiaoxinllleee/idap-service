package org.cmms.modules.dkjkpt.tjcx.zhdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjYb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行表内贷款统计(月报)
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IZhbndktjYbService extends IService<ZhbndktjYb> {

    public void extract (Map<String,String> sql);


   /* public void extract (Map<Object,Object> sql);*/

}
