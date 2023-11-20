package org.cmms.modules.khgl.nh.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.NhbkbpyYsVO;
import org.cmms.modules.khgl.nh.entity.XtBmd;
import org.cmms.modules.khgl.nh.mapper.NhbkbpyMapper;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.vo.KpyhsVO;
import org.cmms.modules.khgl.nh.vo.NhbkbpyNyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 农户背靠背评议
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
@Service
public class NhbkbpyServiceImpl extends ServiceImpl<NhbkbpyMapper, Nhbkbpy> implements INhbkbpyService {

    @Override
    public List<Nhbkbpy> viewDetail(String zjhm) {
        return baseMapper.viewDetail(zjhm);
    }

    @Override
    public Nhbkbpy PymxData(String zjhm, String pyyzjhm) {
        return baseMapper.PymxData(zjhm, pyyzjhm);
    }

    @Override
    public List<Nhbkbpy> selectByMainId(String zjhm){
        return baseMapper.selectByMainId(zjhm);
    }

    @Override
    public List<Nhbkbpy> randomList(NhbkbpyYsVO nhbkbpyYsVO) {
        return baseMapper.randomList(nhbkbpyYsVO);
    }

    @Override
    public Map<String, Integer> getYpyrs() {
        List<KpyhsVO> ypyrs = baseMapper.getYpyrs();
        Map<String, Integer> map = new HashMap<>();
        if (CollUtil.isNotEmpty(ypyrs)){
            for (int i = 0; i < ypyrs.size(); i++) {
                KpyhsVO kpyhsVO = ypyrs.get(i);
                map.put(kpyhsVO.getWgbh(),kpyhsVO.getHs() != null?kpyhsVO.getHs():0);
            }
        }
        return map;
    }

    @Override
    public BigDecimal minJcmxcs(String hhbm) {
        return baseMapper.minJcmxcs(hhbm);
    }

    @Override
    public String getAllBkbpybz(String hhbm) {
        return baseMapper.getAllBkbpybz(hhbm);
    }

    @Override
    public List<XtBmd> getBmdList(String wgbh, String yggh, int pyls,String code) {
        if (pyls == 1 && "425".equals(code)){
            return baseMapper.getBmdListOne(wgbh,yggh);
        }
        return baseMapper.getBmdListOneUp(wgbh, yggh, pyls);
    }

    @Override
    public int getMaxPyls(String wgbh, String yggh) {
        return baseMapper.getMaxPyls(wgbh, yggh);
    }

    @Override
    public String getpyyxm(String wgbh, int pyls) {
        return baseMapper.getpyyxm(wgbh, pyls);
    }

    @Override
    public String getYsxdx(String zjhm) {
        return baseMapper.getYsxdx(zjhm);
    }

    @Override
    public BigDecimal getMaxJysxed(String zjhm) {
        return baseMapper.getMaxJysxed(zjhm);
    }

    @Override
    public List<NhbkbpyNyVo> queryPyxxNy(String wgbh,Integer pyls) {
        return baseMapper.queryPyxxNy(wgbh,pyls);
    }

    @Override
    public Integer getPylsByWgbh(String wgbh) {
        return baseMapper.getPylsByWgbh(wgbh);
    }
}
