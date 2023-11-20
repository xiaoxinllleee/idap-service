package org.cmms.modules.dkjkpt.tjcx.zhajdktj.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjYb;

import java.util.Map;

/**
 * @Description: 支行按揭贷款统计_月报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IZhajdktjYbService extends IService<ZhajdktjYb> {
    public void extract (Map<String,String> sql);
}
