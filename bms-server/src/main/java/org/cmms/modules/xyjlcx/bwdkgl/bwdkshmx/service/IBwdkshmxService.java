package org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.entity.Bwdkshmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 表外贷款收回明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@DS("zx")//zx
public interface IBwdkshmxService extends IService<Bwdkshmx> {
    @DS("eweb")
    void pBwdkshmx(@Param("rzsjB") String rzsjB, @Param("rzsjE")String rzsjE);

    List<Bwdkshmx> queryBwdkshmxOracle(@Param("rzwd") String rzwd,
                                       @Param("shrq") String shrq);
    List<Bwdkshmx> queryBwdkshmxImpala(@Param("rzwd") String rzwd,
                                       @Param("shrq") String shrq);
}
