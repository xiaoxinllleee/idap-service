package org.cmms.modules.dklldj.jbxxgl.zhrpcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.entity.Rate_zhckrp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRate_zhckrpService extends IService<Rate_zhckrp> {
    @DS("eweb")
    public void extract(Map<String,String> tj);
}
