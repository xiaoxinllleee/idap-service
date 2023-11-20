package org.cmms.modules.system.sjbd.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.system.sjbd.entity.SjxfJgbd;
import org.cmms.modules.system.sjbd.mapper.SjxfJgbdMapper;
import org.cmms.modules.system.sjbd.service.ISjxfJgbdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 数据下发结果比对
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Service
public class SjxfJgbdServiceImpl extends ServiceImpl<SjxfJgbdMapper, SjxfJgbd> implements ISjxfJgbdService {

    @Override
    @DS("#ds")
    public long getCount(String tablename, String ds) {
        return baseMapper.getCount(tablename);
    }

    @Override
    @DS("#ds")
    public double getSum(String tablename, String fzzd, String hzzd, String ds) {
        return baseMapper.getSum(tablename,fzzd,hzzd);
    }

    @Override
    @DS("#ds")
    public double getAvg(String tablename, String fzzd, String hzzd, String ds) {
        return baseMapper.getAvg(tablename,fzzd,hzzd);
    }
}
