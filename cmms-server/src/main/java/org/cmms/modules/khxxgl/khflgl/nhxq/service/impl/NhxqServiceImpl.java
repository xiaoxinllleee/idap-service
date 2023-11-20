package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.fxd.entity.KhglIndexVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhHmdDataVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.NhxqMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Service
public class NhxqServiceImpl extends ServiceImpl<NhxqMapper, Nhxq> implements INhxqService {
    @Override
    public void init(){
        baseMapper.init();
    }
    @Override
    public List<Nhxq> getHByZjhm(String zjhm) {
        return baseMapper.getHByZjhm(zjhm);
    }

    @Override
    public List<Nhxq> getByHnkd(KhxxglHnkd khxxglHnkd) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        return baseMapper.getByHnkd(khxxglHnkd,loginUser.getWorkNo());
    }

    @Override
    public Nhxq getByZjhm(String zjhm) {
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getZjhm,zjhm);
        List<Nhxq> nhxqs = baseMapper.selectList(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(nhxqs))
            return nhxqs.get(0);
        return null;
    }

    @Override
    public int syncYesNhxx() {
        return baseMapper.syncYesNhxx();
    }

    @Override
    public KhglIndexVO getFxdIndex() {
        return baseMapper.getFxdIndex();
    }

    @Override
    public KhglIndexVO getFxdIndex(String wgbh, String yggh) {
        return baseMapper.getFxdIndex2(wgbh, yggh);
    }

    @Override
    public List<Nhxq> selectUser(double minlng, double maxlng, double minlat, double maxlat) {
        return baseMapper.selectUser(minlng, maxlng, minlat, maxlat);
    }

    @Override
    public void removeHhbmById(String id) {
        baseMapper.removeHhbmById(id);
    }

    @Override
    public void khzyok(Nhxq nhxq, String sszh, String khjl) {
        baseMapper.khzyok(nhxq,sszh,khjl);
    }

    @Override
    public void khzyokList(List<String> list, String khjl) {
        baseMapper.khzyokList(list, khjl);
    }

    @Override
    public void khzyoid(String id, String khjl) {
        baseMapper.khzyoid(id, khjl);
    }

    @Override
    public List<NhHmdDataVo> getHmdData() {
        return baseMapper.getHmdData();
    }

    @Override
    public void drhnkdbmd() {
        baseMapper.drhnkdbmd();
    }

    @Override
    public void drhnkdbmdsszh(String sszh) {
        baseMapper.drhnkdbmdsszh(sszh);
    }

    @Override
    public List<Nhxq> sj10000() {
        return baseMapper.sj10000();
    }

    @Override
    public void update10000(Nhxq nhxq) {
        baseMapper.update10000(nhxq);
    }
}
