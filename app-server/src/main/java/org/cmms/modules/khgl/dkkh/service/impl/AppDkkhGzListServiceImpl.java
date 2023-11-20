package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.dkkh.entity.AppDkkhGzList;
import org.cmms.modules.khgl.dkkh.mapper.AppDkkhGzListMapper;
import org.cmms.modules.khgl.dkkh.service.IAppDkkhGzListService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Service
public class AppDkkhGzListServiceImpl extends ServiceImpl<AppDkkhGzListMapper, AppDkkhGzList> implements IAppDkkhGzListService {

    @Override
    public boolean isGz(String zjhm, String yggh) {
        LambdaQueryWrapper<AppDkkhGzList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AppDkkhGzList::getYggh,yggh);
        lambdaQueryWrapper.eq(AppDkkhGzList::getZjhm,zjhm);
        Long aLong = baseMapper.selectCount(lambdaQueryWrapper);
        if (aLong > 0)
            return true;
        return false;
    }

    @Override
    public IPage getZjhms(Page page, String yggh, String custType, String wjfl,String zjhm) {
        return baseMapper.getZjhms(page, yggh, custType, wjfl,zjhm);
    }
}
