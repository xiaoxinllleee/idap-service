package org.cmms.modules.workplace.service.impl;

import org.cmms.modules.workplace.entity.WorkplaceCdkkhsjhzKhjl;
import org.cmms.modules.workplace.mapper.WorkplaceCdkkhsjhzKhjlMapper;
import org.cmms.modules.workplace.service.IWorkplaceCdkkhsjhzKhjlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 工作台-存贷款数据汇总-客户经理
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
@Service
public class WorkplaceCdkkhsjhzKhjlServiceImpl extends ServiceImpl<WorkplaceCdkkhsjhzKhjlMapper, WorkplaceCdkkhsjhzKhjl> implements IWorkplaceCdkkhsjhzKhjlService {

    @Autowired
    private WorkplaceCdkkhsjhzKhjlMapper mapper;

    @Override
    public Map<String, Object> getCdkkhsjForKhjlByYggh(String yggh) {
        return mapper.getCdkkhsjForKhjlByYggh(yggh);
    }
}
