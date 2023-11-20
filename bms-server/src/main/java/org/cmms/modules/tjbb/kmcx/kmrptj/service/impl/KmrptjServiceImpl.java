package org.cmms.modules.tjbb.kmcx.kmrptj.service.impl;

import org.cmms.modules.tjbb.kmcx.kmrptj.entity.Kmrptj;
import org.cmms.modules.tjbb.kmcx.kmrptj.mapper.KmrptjMapper;
import org.cmms.modules.tjbb.kmcx.kmrptj.service.IKmrptjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 科目日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Service
public class KmrptjServiceImpl extends ServiceImpl<KmrptjMapper, Kmrptj> implements IKmrptjService {

    @Override
    public void pKmrptj(String tjyf) {
        baseMapper.pKmrptj(tjyf);
    }
}
