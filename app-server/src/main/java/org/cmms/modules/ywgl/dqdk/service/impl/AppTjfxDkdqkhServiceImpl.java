package org.cmms.modules.ywgl.dqdk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.ywgl.dqdk.entity.AppDqdkVO;
import org.cmms.modules.ywgl.dqdk.entity.AppTjfxDkdqkh;
import org.cmms.modules.ywgl.dqdk.mapper.AppTjfxDkdqkhMapper;
import org.cmms.modules.ywgl.dqdk.service.IAppTjfxDkdqkhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class AppTjfxDkdqkhServiceImpl extends ServiceImpl<AppTjfxDkdqkhMapper, AppTjfxDkdqkh> implements IAppTjfxDkdqkhService {

    @Override
    public IPage<AppDqdkVO> getListByPage(Page page, String dqrq, String dkye, String ye, String khlx, String yggh) {
        return baseMapper.getListByPage(page, dqrq, dkye, ye, khlx, yggh);
    }
}
