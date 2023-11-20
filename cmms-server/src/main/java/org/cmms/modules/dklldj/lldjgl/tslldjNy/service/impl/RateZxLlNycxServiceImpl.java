package org.cmms.modules.dklldj.lldjgl.tslldjNy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.mapper.RateZxllcxMapper;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateZxLlNycxService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-03-30
 * @Version: V1.0
 */
@Service
public class RateZxLlNycxServiceImpl extends ServiceImpl<RateZxllcxMapper, RateZxllcx> implements IRateZxLlNycxService {

    @Override
    public Boolean selectKeHuZxLiByZjHGmAndDjnf(RateTszxlldjb rateTszxlldjb) {
        Boolean flag = false;
        LambdaQueryWrapper<RateZxllcx> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(RateZxllcx::getDjid, rateTszxlldjb.getDjid());
        List<RateZxllcx> rateZxllcxList = this.baseMapper.selectList(lambdaQueryWrapper);
        if (rateZxllcxList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateZxLi(RateTszxlldjb rateTszxlldjb1) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        LambdaQueryWrapper<RateZxllcx>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(RateZxllcx::getZjhm,rateTszxlldjb1.getZjhm());
        lambdaQueryWrapper.eq(RateZxllcx::getDjnf,rateTszxlldjb1.getDjnf());
        RateZxllcx rateZxllcx=new RateZxllcx();
        rateZxllcx.setDjid(rateTszxlldjb1.getDjid());
        rateZxllcx.setDjrq(new Timestamp(System.currentTimeMillis()));
        rateZxllcx.setDjnf(rateTszxlldjb1.getDjnf());
        rateZxllcx.setZzbz(rateTszxlldjb1.getZzbz());
        rateZxllcx.setZjhm(rateTszxlldjb1.getZjhm());
        rateZxllcx.setKhmc(rateTszxlldjb1.getKhmc());
        rateZxllcx.setFrdb(rateTszxlldjb1.getFrdb());
        rateZxllcx.setJjll(rateTszxlldjb1.getJzll());
        rateZxllcx.setSffd(rateTszxlldjb1.getFdfd());
        rateZxllcx.setZxll(rateTszxlldjb1.getZxll());
        rateZxllcx.setLprll(rateTszxlldjb1.getLprll());
        rateZxllcx.setJdbp(rateTszxlldjb1.getJdbp());
        rateZxllcx.setLrsj(new Timestamp(System.currentTimeMillis()));
        rateZxllcx.setLrczy(sysUser.getUsername());
        Long count=this.baseMapper.selectCount(lambdaQueryWrapper);
         if(count>0)
         {
             this.baseMapper.delete(lambdaQueryWrapper);

         }
         this.baseMapper.insert(rateZxllcx);

    }

}
