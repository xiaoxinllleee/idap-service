package org.cmms.modules.khlc.khfajg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khlc.khfajg.entity.FakhygInfo;
import org.cmms.modules.khlc.khfajg.entity.PmaFEngineSchemeLog;
import org.cmms.modules.khlc.khfajg.mapper.PmaFEngineSchemeLogMapper;
import org.cmms.modules.khlc.khfajg.service.IPmaFEngineSchemeLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 引擎加工详细日志表
 * @Author: jeecg-boot
 * @Date:   2021-03-02
 * @Version: V1.0
 */
@Service

public class PmaFEngineSchemeLogServiceImpl extends ServiceImpl<PmaFEngineSchemeLogMapper, PmaFEngineSchemeLog> implements IPmaFEngineSchemeLogService {

    @Override
    public List<FakhygInfo> getBySchemeIdAndType(String schemeId, String type,String date) {
        return baseMapper.getBySchemeIdAndType(schemeId,type,date);
    }
}
