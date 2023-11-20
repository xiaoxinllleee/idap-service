package org.cmms.modules.hr.yggl.ygxxgl.service.impl;

import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.mapper.HrBasStaffMapper;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Service
public class HrBasStaffServiceImpl extends ServiceImpl<HrBasStaffMapper, HrBasStaff> implements IHrBasStaffService {

    @Override
    public void updateStaffInfo() {
        baseMapper.updateStaffInfo();
    }
}
