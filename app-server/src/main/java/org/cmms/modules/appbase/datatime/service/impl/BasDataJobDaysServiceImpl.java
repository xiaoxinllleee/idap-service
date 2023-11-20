package org.cmms.modules.appbase.datatime.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.appbase.datatime.entity.BasDataJobDays;
import org.cmms.modules.appbase.datatime.mapper.BasDataJobDaysMapper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 数据入库
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasDataJobDaysServiceImpl extends ServiceImpl<BasDataJobDaysMapper, BasDataJobDays> implements IBasDataJobDaysService {

    @Override
    public Date getMaxExtDay() {
        return baseMapper.getMaxExtDay()==null?new Date():baseMapper.getMaxExtDay();
    }

    @Override
    public Date getMaxEendDay() {
        return baseMapper.getMaxEendDay()==null?new Date():baseMapper.getMaxEendDay();
    }
}
