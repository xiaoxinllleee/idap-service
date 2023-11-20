package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.mapper.EtcyxxxglMapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Service
@DS("cdkyw")
public class EtcyxxxglServiceImpl extends ServiceImpl<EtcyxxxglMapper, Etcyxxxgl> implements IEtcyxxxglService {
    @Autowired
    private EtcyxxxglMapper etcyxxxglMapper;
    @Override
    public Date getMaxTjyf() {
        return etcyxxxglMapper.getMaxTjyf();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateNewTrans(Etcyxxxgl entity, Wrapper<Etcyxxxgl> updateWrapper) {
        return update(entity, updateWrapper);
    }
}
