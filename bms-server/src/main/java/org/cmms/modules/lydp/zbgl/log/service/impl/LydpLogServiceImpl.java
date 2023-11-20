package org.cmms.modules.lydp.zbgl.log.service.impl;

import org.cmms.modules.lydp.zbgl.log.entity.LydpLog;
import org.cmms.modules.lydp.zbgl.log.mapper.LydpLogMapper;
import org.cmms.modules.lydp.zbgl.log.service.ILydpLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 浏阳大屏日期记录
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Service
public class LydpLogServiceImpl extends ServiceImpl<LydpLogMapper, LydpLog> implements ILydpLogService {

    @Override
    public Date dateMax() {
        return baseMapper.dateMax();
    }
}
