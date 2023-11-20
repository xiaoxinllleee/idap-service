package org.cmms.modules.jx.srtx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxxVo;

/**
 * @Description: 生日提醒信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxSrtxxxService extends IService<TbTjfxSrtxxx> {

    IPage<TbTjfxSrtxxxVo> getPageByYggh(Page<TbTjfxSrtxxxVo> page, String yggh);
}
