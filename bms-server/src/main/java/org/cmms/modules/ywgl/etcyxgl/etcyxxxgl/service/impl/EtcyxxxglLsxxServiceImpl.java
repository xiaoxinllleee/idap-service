package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglLsxx;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.mapper.EtcyxxxglLsxxMapper;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglLsxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: ETC营销信息管理历史信息
 * @Author: jeecg-boot
 * @Date:   2021-12-23
 * @Version: V1.0
 */
@Service
@DS("cdkyw")
public class EtcyxxxglLsxxServiceImpl extends ServiceImpl<EtcyxxxglLsxxMapper, EtcyxxxglLsxx> implements IEtcyxxxglLsxxService {
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean saveBatchNewTrans(List<EtcyxxxglLsxx> list) {
        return saveBatch(list);
    }
}
