package org.cmms.modules.hr.yggl.ygxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;

/**
 * @Description: 员工信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface IHrBasStaffService extends IService<HrBasStaff> {

    void updateStaffInfo();

}
