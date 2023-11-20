package org.cmms.modules.khjg.zhfyfp.service;

import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpMx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
public interface IZhfyfpMxService extends IService<ZhfyfpMx> {
    public List<HrBasStaffPostZhzgry> getZhfpry(Date fpyf, Integer fylx, String zzbz);

}
