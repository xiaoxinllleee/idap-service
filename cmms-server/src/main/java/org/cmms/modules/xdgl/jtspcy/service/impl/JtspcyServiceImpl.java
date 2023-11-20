package org.cmms.modules.xdgl.jtspcy.service.impl;

import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import org.cmms.modules.xdgl.jtspcy.mapper.JtspcyMapper;
import org.cmms.modules.xdgl.jtspcy.service.IJtspcyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 集体审批成员
 * @Author: jeecg-boot
 * @Date:   2020-09-14
 * @Version: V1.0
 */
@Service
public class JtspcyServiceImpl extends ServiceImpl<JtspcyMapper, Jtspcy> implements IJtspcyService {

    @Autowired
    private JtspcyMapper jtspcyMapper;


    @Override
    public void deleteJtspcy(String id,String yggh) {
        baseMapper.deleteJtspcy(id,yggh);
    }

    @Override
    public Integer updatespjl(Jtspcy jtspcy) {
        return jtspcyMapper.updatespjl(jtspcy);
    }

    @Override
    public Jtspcy queryById(String id,String zrrid) {
        return jtspcyMapper.queryById(id,zrrid);
    }

    @Override
    public void deleteJtspcyByZrrids(String id,List<String> zrrids) {
         jtspcyMapper.deleteSpcy(id,zrrids);
    }
}
