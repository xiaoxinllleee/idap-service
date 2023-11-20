package org.cmms.modules.khgl.ckkh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.ckkh.entity.CkkhCardVO;
import org.cmms.modules.khgl.ckkh.mapper.AppCkkhGzListMapper;
import org.cmms.modules.khgl.ckkh.service.IAppCkkhGzListService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: app存款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Service
public class AppCkkhGzListServiceImpl extends ServiceImpl<AppCkkhGzListMapper, AppCkkhGzList> implements IAppCkkhGzListService {

    @Override
    public Integer getGhzsByGrp(String yggh, String grp,String custName) {
        return baseMapper.getGhzsByGrp(yggh,grp, custName);
    }

    @Override
    public List<String> getZjhms(int rownumStart, int rownumEnd, String yggh, String grp) {
        return baseMapper.getZjhms(rownumStart, rownumEnd, yggh, grp);
    }

    @Override
    public boolean isGz(String zjhm, String yggh) {
        LambdaQueryWrapper<AppCkkhGzList> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(AppCkkhGzList::getZjhm,zjhm);
        lambdaQueryWrapper.eq(AppCkkhGzList::getYggh,yggh);
        Long aLong = baseMapper.selectCount(lambdaQueryWrapper);
        if (aLong > 0)
            return true;
        return false;
    }

    @Override
    public IPage<CkkhCardVO> getClckkh(Page page, String tzr, String grp, String custName) {
        return baseMapper.getClckkh(page, tzr, grp, custName);
    }
}
