package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglSpxx;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.mapper.EtcyxxxglSpxxMapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglSpxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: ETC营销管理审批信息
 * @Author: jeecg-boot
 * @Date:   2021-12-22
 * @Version: V1.0
 */
@Service
@DS("cdkyw")
public class EtcyxxxglSpxxServiceImpl extends ServiceImpl<EtcyxxxglSpxxMapper, EtcyxxxglSpxx> implements IEtcyxxxglSpxxService {
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean saveBatchNewTrans(List<EtcyxxxglSpxx> list) {
        return saveBatch(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EtcyxxxglSpxx> listNewTrans(Wrapper<EtcyxxxglSpxx> queryWrapper) {
        return list(queryWrapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean updateNewTrans(EtcyxxxglSpxx etcyxxxglSpxx, Wrapper<EtcyxxxglSpxx> updateWrapper) {
        return update(etcyxxxglSpxx, updateWrapper);
    }
}
