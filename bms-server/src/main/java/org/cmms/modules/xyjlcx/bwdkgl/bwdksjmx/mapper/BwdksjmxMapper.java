package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
public interface BwdksjmxMapper extends BaseMapper<Bwdksjmx> {
    void pBwdksjmx();

    /*List<Bwdksjmx> queryContainsBwtmdk(@Param("name") String name,
                                       @Param("csnf") String csnf,
                                       @Param("age") String age);*/
    List<Bwdksjmx> queryBwtmdkHive(@Param("name") String name,
                                   @Param("csnf") String csnf,
                                   @Param("age") String age);

    List<Bwdksjmx> queryBwtmdkOracle(@Param("name") String name,
                                   @Param("csnf") String csnf,
                                   @Param("age") String age);
}
