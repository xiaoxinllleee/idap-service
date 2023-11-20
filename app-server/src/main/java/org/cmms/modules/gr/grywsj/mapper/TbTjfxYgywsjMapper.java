package org.cmms.modules.gr.grywsj.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.gr.grywsj.entity.TbTjfxYgywsj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人业务数据
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
public interface TbTjfxYgywsjMapper extends BaseMapper<TbTjfxYgywsj> {
  IPage<TbTjfxYgywsj> getWqDate(Page<TbTjfxYgywsj> page, @Param("yggh")String yggh, @Param("zbid")String zbid);

}
