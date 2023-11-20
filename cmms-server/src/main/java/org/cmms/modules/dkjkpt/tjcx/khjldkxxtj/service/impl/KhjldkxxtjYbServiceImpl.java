package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjYb;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.mapper.KhjldkxxtjYbMapper;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.IKhjldkxxtjYbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理贷款信息统计月报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Service
public class KhjldkxxtjYbServiceImpl extends ServiceImpl<KhjldkxxtjYbMapper, KhjldkxxtjYb> implements IKhjldkxxtjYbService {

    @Override
    public void InitData(String tjyf) {
        baseMapper.InitData(tjyf);
    }
}
