package org.cmms.modules.gr.grjxsj.mapper;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gr.grjxsj.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.gr.grywsj.entity.TbTjfxYgywsj;

/**
 * @Description: 员工绩效工资表
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
public interface TbTjfxYgjxgzMapper extends BaseMapper<TbTjfxYgjxgz> {
    ErpWageYgjbgzglYx getInfoByGzyf(@Param("gzyf") Date gzyf, @Param("yggh") String yggh);


    ErpWageYgjx getErpWageYgjxInfoByGzrqAndYggh(@Param("gzrq")Date gzrq,
                                                @Param("yggh")String yggh);

    List<TbTjfxYgzblbgz> getYgjxPie(@Param("yggh")String yggh, @Param("tjrq")Date tjrq);

    TbTjfxYgzblbgz getGrzdjxsj(@Param("tjrq")Date tjrq, @Param("yggh") String yggh,@Param("zblb") Integer zblb);

    IPage<ZbmxDto> getJxMx(Page<ZbmxDto> page, @Param("yggh") String yggh, @Param("zblb")Integer zblb);

    IPage<TbTjfxYgjxgz> getWQDateZb(Page<TbTjfxYgjxgz> page, @Param("yggh")String yggh, @Param("zbid")String zbid);

    IPage<TbTjfxYgzblbgz> getWQDateJx(Page<TbTjfxYgzblbgz> page, @Param("yggh")String yggh, @Param("zblb")String zblb);

    IPage<TbTjfxYgjxgz> getWqJxInfo(Page<TbTjfxYgjxgz> page, @Param("yggh")String yggh);

    TbTjfxYgjxgz getJXByDateYggh(@Param("yggh") String yggh, @Param("tjrq") Date tjrq) ;


}
