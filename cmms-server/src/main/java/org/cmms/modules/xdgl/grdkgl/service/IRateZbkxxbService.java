package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbkxxb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 指标库信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateZbkxxbService extends IService<RateZbkxxb> {

    public List<RateZbkxxb> queryzbkxxb(String qydm);

}
