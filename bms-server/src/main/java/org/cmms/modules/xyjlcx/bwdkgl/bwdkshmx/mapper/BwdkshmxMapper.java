package org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.entity.Bwdkshmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 表外贷款收回明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
public interface BwdkshmxMapper extends BaseMapper<Bwdkshmx> {
    void pBwdkshmx(@Param("rzsjB") String rzsjB, @Param("rzsjE")String rzsjE);

    List<Bwdkshmx> queryBwdkshmxOracle(@Param("rzwd") String rzwd,
                                       @Param("shrq") String shrq);
    List<Bwdkshmx> queryBwdkshmxImpala(@Param("rzwd") String rzwd,
                                       @Param("shrq") String shrq);
}
