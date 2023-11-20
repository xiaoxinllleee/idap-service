package org.cmms.modules.jx.srtx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxx;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxxVo;
import org.cmms.modules.jx.srtx.mapper.TbTjfxSrtxxxMapper;
import org.cmms.modules.jx.srtx.service.ITbTjfxSrtxxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 生日提醒信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Service
public class TbTjfxSrtxxxServiceImpl extends ServiceImpl<TbTjfxSrtxxxMapper, TbTjfxSrtxxx> implements ITbTjfxSrtxxxService {

    @Override
    public IPage<TbTjfxSrtxxxVo> getPageByYggh(Page<TbTjfxSrtxxxVo> page, String yggh) {
        return baseMapper.getPageByYggh(page,yggh);
    }
}
