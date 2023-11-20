package org.cmms.modules.dklldj.lldjgl.glzhgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;

import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IrateKhzhglxxbService extends IService<rateKhzhglxxb> {

    @DS("eweb")
    void extract(String zjhm);

    /*@DS("sjxf")
    double bormBaseList(String zjhm);*/

}
