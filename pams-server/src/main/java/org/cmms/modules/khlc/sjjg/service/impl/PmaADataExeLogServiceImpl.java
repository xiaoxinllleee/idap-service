package org.cmms.modules.khlc.sjjg.service.impl;

import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import org.cmms.modules.khlc.sjjg.mapper.PmaADataExeLogMapper;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 数据加工日志
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
@Service
public class PmaADataExeLogServiceImpl extends ServiceImpl<PmaADataExeLogMapper, PmaADataExeLog> implements IPmaADataExeLogService {
    @Override
    public String getMaxTjyf(Integer zxsx) {
        return baseMapper.getMaxTjyf(zxsx);
    }
    @Override
    public String getMaxCgrq() {
        return baseMapper.getMaxCgrq();
    }
    @Override
    public String getMaxTjyfByRwid(String rwid) {
        return baseMapper.getMaxTjyfByRwid(rwid);
    }
    @Override
    public Integer getZxzxss() {
        return baseMapper.getZxzxss();
    }
}
