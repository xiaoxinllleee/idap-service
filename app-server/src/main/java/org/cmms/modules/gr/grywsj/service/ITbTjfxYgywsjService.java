package org.cmms.modules.gr.grywsj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gr.grywsj.entity.TbTjfxYgywsj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 个人业务数据
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxYgywsjService extends IService<TbTjfxYgywsj> {
    IPage<TbTjfxYgywsj> getWqDate(Page<TbTjfxYgywsj> page, @Param("yggh")String yggh, @Param("zbid")String zbid);


}
