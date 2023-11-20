package org.cmms.modules.gr.grjxsj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.gr.grjxsj.entity.*;
import org.cmms.modules.gr.grjxsj.mapper.TbTjfxYgjxgzMapper;
import org.cmms.modules.gr.grjxsj.service.ITbTjfxYgjxgzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 员工绩效工资表
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Service
public class TbTjfxYgjxgzServiceImpl extends ServiceImpl<TbTjfxYgjxgzMapper, TbTjfxYgjxgz> implements ITbTjfxYgjxgzService {

    @Override
    public ErpWageYgjbgzglYx getInfoByGzyf(Date gzyf, String yggh) {
        return baseMapper.getInfoByGzyf(gzyf,yggh);
    }

    @Override
    public ErpWageYgjx getErpWageYgjxInfoByGzrqAndYggh(Date gzrq, String yggh) {
        return baseMapper.getErpWageYgjxInfoByGzrqAndYggh(gzrq,yggh);
    }

    @Override
    public List<TbTjfxYgzblbgz> getYgjxPie(String yggh, Date tjrq) {
        return baseMapper.getYgjxPie(yggh,tjrq);
    }

    @Override
    public TbTjfxYgzblbgz getGrzdjxsj(Date tjrq, String yggh, Integer zblb) {
        return baseMapper.getGrzdjxsj(tjrq,yggh,zblb);
    }

    @Override
    public IPage<ZbmxDto> getJxMx(Page<ZbmxDto> page, String yggh, Integer zblb) {
        return baseMapper.getJxMx(page,yggh,zblb);
    }

    @Override
    public IPage<TbTjfxYgjxgz> getWQDateZb(Page<TbTjfxYgjxgz> page, String yggh, String zbid) {
        return baseMapper.getWQDateZb(page,yggh,zbid);
    }

    @Override
    public IPage<TbTjfxYgzblbgz> getWQDateJx(Page<TbTjfxYgzblbgz> page, String yggh, String zblb) {
        return baseMapper.getWQDateJx(page,yggh,zblb);
    }

    @Override
    public IPage<TbTjfxYgjxgz> getWqJxInfo(Page<TbTjfxYgjxgz> page, String yggh) {
        return baseMapper.getWqJxInfo(page,yggh);
    }

    @Override
    public TbTjfxYgjxgz getJXByDateYggh(String yggh, Date tjrq) {
        return baseMapper.getJXByDateYggh(yggh,tjrq);
    }


}
