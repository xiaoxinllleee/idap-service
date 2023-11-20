package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkjkptCkzhglxx;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.mapper.CkjkptCkzhglxxMapper;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.ICkjkptCkzhglxxService;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.HrBasStaffPostVO;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@Service
public class CkjkptCkzhglxxServiceImpl extends ServiceImpl<CkjkptCkzhglxxMapper, CkjkptCkzhglxx> implements ICkjkptCkzhglxxService {


    @Override
    public String getMaxId() {
        return baseMapper.getMaxId();
    }

    @Override
    public Integer getCkzhglxxByckzh(String ckzh) {
        return baseMapper.getCkzhglxxByckzh(ckzh);
    }

   /* @Override
    public List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz,String yggh) {
        return baseMapper.getListClaim(ywjgdm,rglx,gwbz,khjlbz,yggh);
    }*/

    @Override
    public void extract() {
        baseMapper.extract();
    }

    @Override
    @DS("master")
    public Integer judgeExtract() {
        return baseMapper.judgeExtract();
    }

    @Override
    public String getMaxDataDate() {
        return baseMapper.getMaxDataDate();
    }

    @Override
    public List<CbsInvmBase> viewCkzhExit(String b_date, String e_date, String ckzh) {
        return baseMapper.viewCkzhExit(b_date,e_date,ckzh);
    }


    @Override
    public String getDkzh(String ckzh) {
        return baseMapper.getDkzh(ckzh);
    }

    @Override
    @DS("master")
    public List<HrBasStaffPostVO> empCheckFunc(String zzbz, String gwbz, String yggh) {
        return baseMapper.empCheckFunc(zzbz, gwbz, yggh);
    }
}
