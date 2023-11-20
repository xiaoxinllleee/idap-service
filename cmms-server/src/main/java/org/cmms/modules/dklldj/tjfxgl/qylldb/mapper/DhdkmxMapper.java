package org.cmms.modules.dklldj.tjfxgl.qylldb.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Dhdkmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 单户贷款明细
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
public interface DhdkmxMapper extends BaseMapper<Dhdkmx> {

    List<Dhdkmx> queryDhdkmx(@Param("tjyf") Date tjyf,
                             @Param("jgdm") String jgdm,
                             @Param("zjhm") String zjhm,
                             @Param("ywbh") String ywbh);

}
