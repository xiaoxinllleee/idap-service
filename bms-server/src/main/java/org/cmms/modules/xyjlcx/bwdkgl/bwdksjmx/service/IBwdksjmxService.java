package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@DS("zx") // zx
public interface IBwdksjmxService extends IService<Bwdksjmx> {
    @DS("eweb")
    void pBwdksjmx();

    /*List<Bwdksjmx> queryContainsBwtmdk(@Param("name") String name,
                                       @Param("csnf") String csnf,
                                       @Param("age") String age);*/
    /**
     * 查询被调查人本人是否含有表外同名贷款--impala
     *
     * @param name 姓名
     * @param csnf 出生年份
     * @param age  同名表外贷款核实：疑似查询功能中，表外借款之时，被查询人不足N岁，明显不是同一人，不需核实，排除干扰。
     * @return
     */
    List<Bwdksjmx> queryBwtmdkHive(@Param("name") String name,
                                   @Param("csnf") String csnf,
                                   @Param("age") String age);

    /**
     * 查询被调查人本人是否含有表外同名贷款--oracle
     *
     * @param name 姓名
     * @param csnf 出生年份
     * @param age  同名表外贷款核实：疑似查询功能中，表外借款之时，被查询人不足N岁，明显不是同一人，不需核实，排除干扰。
     * @return
     */
    List<Bwdksjmx> queryBwtmdkOracle(@Param("name") String name,
                                     @Param("csnf") String csnf,
                                     @Param("age") String age);
}
