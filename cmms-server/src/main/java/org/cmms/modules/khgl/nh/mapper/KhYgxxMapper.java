package org.cmms.modules.khgl.nh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;

/**
 * @Description: 员工信息
 * @Author: cmms
 * @Date:   2019-09-23
 * @Version: V1.0
 */
public interface KhYgxxMapper extends BaseMapper<HrBasStaff> {

    public boolean deleteByMainId(String zjhm);


}
