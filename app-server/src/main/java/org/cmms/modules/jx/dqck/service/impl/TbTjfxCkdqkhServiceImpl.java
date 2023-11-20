package org.cmms.modules.jx.dqck.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkh;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkhVO;
import org.cmms.modules.jx.dqck.mapper.TbTjfxCkdqkhMapper;
import org.cmms.modules.jx.dqck.service.ITbTjfxCkdqkhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款到期
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Service
public class TbTjfxCkdqkhServiceImpl extends ServiceImpl<TbTjfxCkdqkhMapper, TbTjfxCkdqkh> implements ITbTjfxCkdqkhService {

    @Override
    public IPage<TbTjfxCkdqkhVO> getListByPage(Page page, String yggh) {
        return baseMapper.getListByPage(page,yggh);
    }
}
