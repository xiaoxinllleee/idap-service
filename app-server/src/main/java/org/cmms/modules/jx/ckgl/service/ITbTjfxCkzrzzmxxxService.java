package org.cmms.modules.jx.ckgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxxVo;

/**
 * @Description: 存款拓展明细信息
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxCkzrzzmxxxService extends IService<TbTjfxCkzrzzmxxx> {

    IPage<TbTjfxCkzrzzmxxxVo> getListByYggh(Page<TbTjfxCkzrzzmxxxVo> page, String yggh);
}
