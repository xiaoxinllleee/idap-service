package org.cmms.modules.dklldj.lldjgl.tslldjNy.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0 客户执行利率
 */
@DS("eweb") // rate
public interface IRateZxLlNycxService extends IService<RateZxllcx> {

    Boolean selectKeHuZxLiByZjHGmAndDjnf(RateTszxlldjb rateTszxlldjb);

    void updateZxLi(RateTszxlldjb rateTszxlldjb1);
}
