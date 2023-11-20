package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VdkjkptDhdksjtz;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper.VdkjkptDhdksjtzMapper;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IVdkjkptDhdksjtzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@Service
public class VdkjkptDhdksjtzServiceImpl extends ServiceImpl<VdkjkptDhdksjtzMapper, VdkjkptDhdksjtz> implements IVdkjkptDhdksjtzService {

    @Override
    public List<VdkjkptDhdksjtz> getListClaim(  String khmc, String zjhm) {

        return baseMapper.getListClaim(khmc, zjhm);
    }
}
