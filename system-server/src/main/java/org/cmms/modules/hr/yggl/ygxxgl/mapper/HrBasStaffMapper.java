package org.cmms.modules.hr.yggl.ygxxgl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;

/**
 * @Description: 员工信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface HrBasStaffMapper extends BaseMapper<HrBasStaff> {
    void updateStaffInfo();
}
