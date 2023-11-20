package org.cmms.modules.jx.ckyj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxxVo;

/**
 * @Description: 存款预警信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxCkyjxxService extends IService<TbTjfxCkyjxx> {

   IPage<TbTjfxCkyjxxVo>  queryListByYggh(Page<TbTjfxCkyjxxVo> page, String yggh);

    IPage<TbTjfxCkyjxxVo> getWatchList(Page<TbTjfxCkyjxxVo> page, String yggh, String khbh);
}
