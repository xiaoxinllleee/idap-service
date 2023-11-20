package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.enums.QydmEnums;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMx;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMxVO;
import org.cmms.modules.ygjx.mapper.ErpWageYgjxMxMapper;
import org.cmms.modules.ygjx.service.IErpWageYgjxMxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工绩效明细
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class ErpWageYgjxMxServiceImpl extends ServiceImpl<ErpWageYgjxMxMapper, ErpWageYgjxMx> implements IErpWageYgjxMxService {


    public IPage<ErpWageYgjxMxVO> getList(Page page, String yggh, String zblb,String date) {
        return baseMapper.getList(page, yggh, zblb,date);
    }

    @Override
    @DS("idap")
    public IPage<ErpWageYgjxMxVO> getListV3(Page page, String yggh, String zblb,String date) {
        return baseMapper.getListV3(page, yggh, zblb,date);
    }
    @Override
    public IPage<ErpWageYgjxMxVO> getListTy(Page page, String yggh, String zblb, String date) {
        return baseMapper.getListTy(page, yggh, zblb, date);
    }

    @Override
    public IPage<ErpWageYgjxMxVO> getJhList(Page page, String yggh, String zblb, String date) {
        return baseMapper.getJhList(page, yggh, zblb, date);
    }
}
