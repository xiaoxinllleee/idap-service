package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateZbgzxxbService extends IService<RateZbgzxxb> {

    public RateZbgzxxb queryzbgzxxb(String qydm,String zbid,String zbgzid);

}
