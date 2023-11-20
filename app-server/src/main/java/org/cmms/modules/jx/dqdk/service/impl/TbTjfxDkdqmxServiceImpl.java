package org.cmms.modules.jx.dqdk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmx;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmxVO;
import org.cmms.modules.jx.dqdk.mapper.TbTjfxDkdqmxMapper;
import org.cmms.modules.jx.dqdk.service.ITbTjfxDkdqmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Service
public class TbTjfxDkdqmxServiceImpl extends ServiceImpl<TbTjfxDkdqmxMapper, TbTjfxDkdqmx> implements ITbTjfxDkdqmxService {

    @Autowired
    TbTjfxDkdqmxMapper tbTjfxDkdqmxMapper;
    @Override
    public List<TbTjfxDkdqmxVO> getListByYggh(String yggh) {
        return tbTjfxDkdqmxMapper.getListByYggh(yggh);
    }

    @Override
    public IPage<TbTjfxDkdqmxVO> getListByYggh(Page page, String yggh) {
        return tbTjfxDkdqmxMapper.getPageListByYggh(page,yggh);
    }
}
