package org.cmms.modules.dklldj.lldjgl.tslldjNy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.entity.RateLsdjcx;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.mapper.RateLsdjcxMapper;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.service.IRateLsdjcxService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateLsdjNycxService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Service
public class RateLsdNyjcxServiceImpl extends ServiceImpl<RateLsdjcxMapper, RateLsdjcx> implements IRateLsdjNycxService {



    @Override
    public void insertHistoryNy(RateTszxlldjb rateTszxlldjb1) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        RateLsdjcx lsdjcx = new RateLsdjcx();
        lsdjcx.setDjid(rateTszxlldjb1.getDjid());
        lsdjcx.setDjrq(new Timestamp(System.currentTimeMillis()));
        lsdjcx.setDjnf(rateTszxlldjb1.getDjnf());
        lsdjcx.setZzbz(rateTszxlldjb1.getZzbz());
        lsdjcx.setZjhm(rateTszxlldjb1.getZjhm());
        lsdjcx.setKhmc(rateTszxlldjb1.getKhmc());
        //lsdjcx.setDfhj(check.getDfhj());
        lsdjcx.setJjll(rateTszxlldjb1.getJzll());
        lsdjcx.setSffd(rateTszxlldjb1.getFdfd());
        lsdjcx.setZxll(rateTszxlldjb1.getZxll());
        lsdjcx.setLprll(rateTszxlldjb1.getLprll());
        lsdjcx.setJdbp(rateTszxlldjb1.getJdbp());
        lsdjcx.setLrsj(new Timestamp(System.currentTimeMillis()));
        lsdjcx.setLrczy(sysUser.getUsername());
        lsdjcx.setDjbs(Integer.valueOf(2));
        this.baseMapper.insert(lsdjcx);

    }
}
