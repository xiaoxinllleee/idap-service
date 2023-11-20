package org.cmms.modules.khjg.zhfyfp.service.impl;

import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;
import org.cmms.modules.khjg.zhfyfp.entity.ZhfyfpMx;
import org.cmms.modules.khjg.zhfyfp.mapper.ZhfyfpMxMapper;
import org.cmms.modules.khjg.zhfyfp.service.IZhfyfpMxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Service
public class ZhfyfpMxServiceImpl extends ServiceImpl<ZhfyfpMxMapper, ZhfyfpMx> implements IZhfyfpMxService {

    @Override
    public List<HrBasStaffPostZhzgry> getZhfpry(Date fpyf, Integer fylx, String zzbz) {
        return baseMapper.getZhfpry(fpyf, fylx, zzbz);
    }
}
