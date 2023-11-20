package org.cmms.modules.workplace.service.impl;

import org.cmms.modules.workplace.entity.WorkPlaceSystemVersion;
import org.cmms.modules.workplace.mapper.WorkPlaceSystemVersionMapper;
import org.cmms.modules.workplace.service.IWorkPlaceSystemVersionService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 系统版本号
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Service
public class WorkPlaceSystemVersionServiceImpl extends ServiceImpl<WorkPlaceSystemVersionMapper, WorkPlaceSystemVersion> implements IWorkPlaceSystemVersionService {


    @Override
    public String getLatestTestVersion(String qydm) {
        return baseMapper.getLatestTestVersion(qydm);
    }

    @Override
    public String getLatestProdVersion(String qydm) {
        return baseMapper.getLatestProdVersion(qydm);
    }
}
