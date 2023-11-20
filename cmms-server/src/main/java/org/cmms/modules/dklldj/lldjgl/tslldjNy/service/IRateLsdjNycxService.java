package org.cmms.modules.dklldj.lldjgl.tslldjNy.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.entity.RateLsdjcx;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@DS("eweb") // rate
public interface IRateLsdjNycxService extends IService<RateLsdjcx> {



    void insertHistoryNy(RateTszxlldjb rateTszxlldjb1);
}
