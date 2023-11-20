package org.cmms.modules.jx.ckgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxx;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxxVo;
import org.cmms.modules.jx.ckgl.mapper.TbTjfxCkzrzzmxxxMapper;
import org.cmms.modules.jx.ckgl.service.ITbTjfxCkzrzzmxxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款拓展明细信息
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Service
public class TbTjfxCkzrzzmxxxServiceImpl extends ServiceImpl<TbTjfxCkzrzzmxxxMapper, TbTjfxCkzrzzmxxx> implements ITbTjfxCkzrzzmxxxService {

    @Override
    public IPage<TbTjfxCkzrzzmxxxVo> getListByYggh(Page<TbTjfxCkzrzzmxxxVo> page, String yggh) {
        return baseMapper.getListByYggh(page,yggh);
    }
}
