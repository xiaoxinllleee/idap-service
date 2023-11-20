package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjJb;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.mapper.YgghdkxxtjJbMapper;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service.IYgghdkxxtjJbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工管户贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
@Service
public class YgghdkxxtjJbServiceImpl extends ServiceImpl<YgghdkxxtjJbMapper, YgghdkxxtjJb> implements IYgghdkxxtjJbService {

    @Override
    public void InitData(String tjyf) {
        baseMapper.InitData(tjyf);
    }
}
