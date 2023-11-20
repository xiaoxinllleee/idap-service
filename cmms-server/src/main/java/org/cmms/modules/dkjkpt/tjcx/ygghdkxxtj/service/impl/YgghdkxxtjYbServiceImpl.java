package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjYb;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.mapper.YgghdkxxtjYbMapper;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service.IYgghdkxxtjYbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工管户贷款信息统计月报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
@Service
public class YgghdkxxtjYbServiceImpl extends ServiceImpl<YgghdkxxtjYbMapper, YgghdkxxtjYb> implements IYgghdkxxtjYbService {

    @Override
    public void InitData(String tjyf) {
        baseMapper.InitData(tjyf);
    }
}
