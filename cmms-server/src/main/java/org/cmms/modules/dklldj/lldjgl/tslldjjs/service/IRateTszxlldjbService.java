package org.cmms.modules.dklldj.lldjgl.tslldjjs.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.entity.RateTszxlldjb;

import java.util.Date;

/**
 * @Description: 特殊利率定价
 * @Author: jeecg-boot
 * @Date:   2021-04-02
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateTszxlldjbService extends IService<RateTszxlldjb> {
    boolean isNotSureByZjhm(String zjhm, Date djnf,String spzt);
}
