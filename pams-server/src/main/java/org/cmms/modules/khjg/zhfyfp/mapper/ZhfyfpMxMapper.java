package org.cmms.modules.khjg.zhfyfp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpMx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
public interface ZhfyfpMxMapper extends BaseMapper<ZhfyfpMx> {

    public List<HrBasStaffPostZhzgry> getZhfpry(Date fpyf, Integer fylx, String zzbz);

}
