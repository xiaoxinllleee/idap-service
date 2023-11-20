package org.cmms.modules.dklldj.tjfxgl.qylldb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Dhdkmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 单户贷款明细
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IDhdkmxService extends IService<Dhdkmx> {

    List<Dhdkmx> queryDhdkmx(@Param("tjyf") Date tjyf,
                             @Param("jgdm") String jgdm,
                             @Param("zjhm") String zjhm,
                             @Param("ywbh") String ywbh);

}
