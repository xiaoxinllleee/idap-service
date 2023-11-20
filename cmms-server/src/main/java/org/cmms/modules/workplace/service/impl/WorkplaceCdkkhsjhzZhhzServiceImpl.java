package org.cmms.modules.workplace.service.impl;

import org.cmms.modules.workplace.entity.WorkplaceCdkkhsjhzZhhz;
import org.cmms.modules.workplace.mapper.WorkplaceCdkkhsjhzZhhzMapper;
import org.cmms.modules.workplace.service.IWorkplaceCdkkhsjhzZhhzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 工作台-存贷款数据汇总-支行行长
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
@Service
public class WorkplaceCdkkhsjhzZhhzServiceImpl extends ServiceImpl<WorkplaceCdkkhsjhzZhhzMapper, WorkplaceCdkkhsjhzZhhz> implements IWorkplaceCdkkhsjhzZhhzService {

    @Autowired
    private WorkplaceCdkkhsjhzZhhzMapper mapper;

    @Override
    public Map<String, Object> getCdkkhsjForZhhzByZzbz(String zzbz) {
        return mapper.getCdkkhsjForZhhzByZzbz(zzbz);
    }
}
