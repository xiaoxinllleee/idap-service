package org.cmms.modules.dkjkpt.tjcx.zhajdktj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjNb;

import java.util.Map;

/**
 * @Description: 支行按揭贷款统计_年报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IZhajdktjNbService extends IService<ZhajdktjNb> {

    public void  extract(Map<String,String> sql);
}
