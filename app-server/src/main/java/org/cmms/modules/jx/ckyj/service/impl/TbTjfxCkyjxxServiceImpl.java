package org.cmms.modules.jx.ckyj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxx;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxxVo;
import org.cmms.modules.jx.ckyj.mapper.TbTjfxCkyjxxMapper;
import org.cmms.modules.jx.ckyj.service.ITbTjfxCkyjxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款预警信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Service
public class TbTjfxCkyjxxServiceImpl extends ServiceImpl<TbTjfxCkyjxxMapper, TbTjfxCkyjxx> implements ITbTjfxCkyjxxService {

    @Override
    public IPage<TbTjfxCkyjxxVo> queryListByYggh(Page<TbTjfxCkyjxxVo> page, String yggh) {
        return baseMapper.queryListByYggh(page,yggh);
    }

    @Override
    public IPage<TbTjfxCkyjxxVo> getWatchList(Page<TbTjfxCkyjxxVo> page, String yggh, String khbh) {
        return baseMapper.getWatchList(page,yggh,khbh);
    }
}
