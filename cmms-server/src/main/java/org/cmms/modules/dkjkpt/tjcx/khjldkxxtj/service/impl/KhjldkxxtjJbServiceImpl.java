package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjJb;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.mapper.KhjldkxxtjJbMapper;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service.IKhjldkxxtjJbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Service
public class KhjldkxxtjJbServiceImpl extends ServiceImpl<KhjldkxxtjJbMapper, KhjldkxxtjJb> implements IKhjldkxxtjJbService {

    @Override
    public void InitData(String tjyf) {
        baseMapper.InitData(tjyf);
    }

}
