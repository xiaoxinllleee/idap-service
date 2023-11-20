package org.cmms.modules.ywgl.dqck.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.dqck.entity.AppDqckVO;
import org.cmms.modules.ywgl.dqck.entity.AppTjfxCkdqkh;
import org.cmms.modules.ywgl.dqck.mapper.AppTjfxCkdqkhglMapper;
import org.cmms.modules.ywgl.dqck.service.IAppTjfxCkdqkhglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 到期存款
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class AppTjfxCkdqkhglServiceImpl extends ServiceImpl<AppTjfxCkdqkhglMapper, AppTjfxCkdqkh> implements IAppTjfxCkdqkhglService {

    @Override
    public IPage<AppDqckVO> getAppList(Page page, String type,String yggh) {
        return baseMapper.getAppList(page,type,yggh);
    }
}
