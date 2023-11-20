package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjNb;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.mapper.KhjldkxxtjNbMapper;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.IKhjldkxxtjNbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理贷款信息统计年报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Service
public class KhjldkxxtjNbServiceImpl extends ServiceImpl<KhjldkxxtjNbMapper, KhjldkxxtjNb> implements IKhjldkxxtjNbService {

    @Override
    public void InitData(String tjyf) {
        baseMapper.InitData(tjyf);
    }
}
