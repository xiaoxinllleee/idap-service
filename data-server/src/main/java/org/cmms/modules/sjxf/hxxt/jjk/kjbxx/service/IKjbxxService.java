package org.cmms.modules.sjxf.hxxt.jjk.kjbxx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 卡基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@DS("sjxf")
public interface IKjbxxService extends IService<Kjbxx> {

    /**
     * 获取便民卡信息-impala
     *
     * @param zjhm
     * @return
     */
    List<Kjbxx> getBmkxxHive(@Param("zjhm") String zjhm);

    /**
     * 获取便民卡信息-oracle
     *
     * @param zjhm
     * @return
     */
    List<Kjbxx> getBmkxxOracle(@Param("zjhm") String zjhm);
}
