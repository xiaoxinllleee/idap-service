package org.cmms.modules.ywgl.dkyw.dkzhzy.service.impl;

import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DzyzhsVO;
import org.cmms.modules.ywgl.dkyw.dkzhzy.mapper.DkzhzyMapper;
import org.cmms.modules.ywgl.dkyw.dkzhzy.service.IDkzhzyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款账号转移
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@Service
public class DkzhzyServiceImpl extends ServiceImpl<DkzhzyMapper, Dkzhzy> implements IDkzhzyService {

    @Override
    public void pDkzhzy1(String org, String custManagerId) {
        baseMapper.pDkzhzy1(org, custManagerId);
    }
    @Override
    public void pDkzhzy2(String org, String acctNo) {
        baseMapper.pDkzhzy2(org, acctNo);
    }
    @Override
    public void pDkzhzy3(String org,String custManagerId, String acctNo) {
        baseMapper.pDkzhzy3(org, custManagerId,acctNo);
    }

    @Override
    public void dkzhzy1(String oldjgdm,String oldcustid,String newjgdm,String newcustid,String newyggh,String newgwbz,String newgyh,String  dkzh,String czy ) {
        baseMapper.dkzhzy1(oldjgdm,oldcustid,newjgdm,newcustid,newyggh,newgwbz,newgyh,dkzh,czy);
    }
    @Override
    public void dkzhzy2(String oldjgdm,String oldcustid,String newjgdm,String newcustid,String newyggh,String newgwbz,String newgyh,String czy ) {
        baseMapper.dkzhzy2(oldjgdm,oldcustid,newjgdm,newcustid,newyggh,newgwbz,newgyh,czy);
    }
    @Override
    public DzyzhsVO getDzysByKhjlOrDkzh(String jgdm, String khjlbz, String dkzh) {
        return baseMapper.getDzysByKhjlOrDkzh(jgdm,khjlbz,dkzh);
    }

    @Override
    public String getGlid() {
        return baseMapper.getGlid();
    }
}
