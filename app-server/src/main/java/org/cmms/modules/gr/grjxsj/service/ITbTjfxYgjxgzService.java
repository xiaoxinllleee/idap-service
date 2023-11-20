package org.cmms.modules.gr.grjxsj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gr.grjxsj.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 员工绩效工资表
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxYgjxgzService extends IService<TbTjfxYgjxgz> {
    ErpWageYgjbgzglYx getInfoByGzyf(@Param("gzyf") Date gzyf, @Param("yggh") String yggh);


    ErpWageYgjx getErpWageYgjxInfoByGzrqAndYggh(@Param("gzrq")Date gzrq,
                                                 @Param("yggh")String yggh);

    /**
     * 个人绩效饼图数据查询
     * @param yggh
     * @param tjrq
     * @return
     */
    List<TbTjfxYgzblbgz> getYgjxPie(@Param("yggh")String yggh, @Param("tjrq")Date tjrq);


    TbTjfxYgzblbgz getGrzdjxsj(@Param("tjrq")Date tjrq, @Param("yggh") String yggh,@Param("zblb") Integer zblb);

    IPage<ZbmxDto> getJxMx(Page<ZbmxDto> page, @Param("yggh") String yggh, @Param("zblb")Integer zblb);

    IPage<TbTjfxYgjxgz> getWQDateZb(Page<TbTjfxYgjxgz> page, @Param("yggh")String yggh, @Param("zbid")String zbid);

    IPage<TbTjfxYgzblbgz> getWQDateJx(Page<TbTjfxYgzblbgz> page, @Param("yggh")String yggh, @Param("zblb")String zblb);

    IPage<TbTjfxYgjxgz> getWqJxInfo(Page<TbTjfxYgjxgz> page, @Param("yggh")String yggh);

    TbTjfxYgjxgz getJXByDateYggh(@Param("yggh") String yggh, @Param("tjrq") Date tjrq) ;

}
