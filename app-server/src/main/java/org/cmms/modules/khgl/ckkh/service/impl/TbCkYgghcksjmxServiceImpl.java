package org.cmms.modules.khgl.ckkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.khgl.ckkh.entity.*;
import org.cmms.modules.khgl.ckkh.mapper.TbCkYgghcksjmxMapper;
import org.cmms.modules.khgl.ckkh.service.ITbCkYgghcksjmxService;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 员工管户数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class TbCkYgghcksjmxServiceImpl extends ServiceImpl<TbCkYgghcksjmxMapper, TbCkYgghcksjmx> implements ITbCkYgghcksjmxService {


    @Autowired
    RedisUtil redisUtil;

    @Override
    public CkkhjbVO getByYggh(String yggh) {
        return baseMapper.getByYggh(yggh);
    }

    @Override
    public List<String> getZjhms(int rownumStart,int rownumEnd,String yggh,String grp,String custName) {
        return baseMapper.getZjhms( rownumStart, rownumEnd,yggh,grp,custName);
    }

    @Override
    public Integer getGhzs(String yggh) {
        return baseMapper.getGhzs(yggh);
    }

    @Override
    public TbCkYgghcksjmx minEndDate(String zjhm) {
        return baseMapper.minEndDate(zjhm);
    }

    @Override
    public CkkhGyVO getByZjhm(String zjhm) {
        return baseMapper.getByZjhm(zjhm);
    }

    @Override
    public Integer getGhzs(String yggh, String grp,String custName) {
        return baseMapper.getGhzsByGrp(yggh,grp,custName);
    }

    @Override
    public List<String> getAcctGrpByZjhm(String zjhm, String yggh) {
        return baseMapper.getAcctGrpByZjhm(zjhm, yggh);
    }

    @Override
    public List<CkkhRankVO> getRankList(int rownumStart, int rownumEnd, String yggh, String pxType) {
        return baseMapper.getRankList(rownumStart, rownumEnd, yggh, pxType);
    }



    @Override
    public IPage<TbCkYgghcksjmxVo> getJsr(Page page, String ghr, String qmyeS, String qmyeE, String qmTable, String zrTable) {
        return baseMapper.getJsr(page, ghr, qmyeS, qmyeE, qmTable, zrTable);
    }

    @Override
    public IPage<TbCkYgghcksjmxVo> getJyc(Page page, String ghr, String qmyeS, String qmyeE, String qmTable) {
        return baseMapper.getJyc(page, ghr, qmyeS, qmyeE, qmTable);
    }

    @Override
    public IPage<TbCkYgghcksjmxVo> getJjc(Page page, String ghr, String qmyeS, String qmyeE, String qmTable, String jcTable) {
        return baseMapper.getJjc(page, ghr, qmyeS, qmyeE, qmTable, jcTable);
    }

    @Override
    public IPage<TbCkYgghcksjmxVo> getJnc(Page page, String ghr, String qmyeS, String qmyeE, String qmTable) {
        return baseMapper.getJnc(page, ghr, qmyeS, qmyeE, qmTable);
    }

    @Override
    public String getCkCpxx(String zjhm) {
        return baseMapper.getCkCpxx(zjhm);
    }


    @Override
    public List<QhckphVO> getCkRank() {
        return baseMapper.getCkRank();
    }

    @Override
    public IPage<CkkhCardVO> getClckkh(Page page, String tzr, String grp, String custName) {
        return baseMapper.getClckkh(page, tzr, grp, custName);
    }
}
