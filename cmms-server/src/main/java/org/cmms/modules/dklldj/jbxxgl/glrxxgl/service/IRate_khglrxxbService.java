package org.cmms.modules.dklldj.jbxxgl.glrxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.entity.Rate_khglrxxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRate_khglrxxbService extends IService<Rate_khglrxxb> {
    @DS("eweb")
    public void extract();
}
