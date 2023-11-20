package org.cmms.modules.dklldj.jbxxgl.khxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRate_khjbxxbService extends IService<Rate_khjbxxb> {

    @DS("eweb")
    public void extract();

    Rate_khjbxxb getNameByZjhm(String zjhm);

}
