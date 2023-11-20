package org.cmms.modules.gr.grywsj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.gr.grywsj.entity.TbTjfxYgywsj;
import org.cmms.modules.gr.grywsj.mapper.TbTjfxYgywsjMapper;

import org.cmms.modules.gr.grywsj.service.ITbTjfxYgywsjService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 个人业务数据
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Service
public class TbTjfxYgywsjServiceImpl extends ServiceImpl<TbTjfxYgywsjMapper, TbTjfxYgywsj> implements ITbTjfxYgywsjService {

    @Override
    public IPage<TbTjfxYgywsj> getWqDate(Page<TbTjfxYgywsj> page, String yggh, String zbid) {
        return baseMapper.getWqDate(page,yggh,zbid);
    }

}
