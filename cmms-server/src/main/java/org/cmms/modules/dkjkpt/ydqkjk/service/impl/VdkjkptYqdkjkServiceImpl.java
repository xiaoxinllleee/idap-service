package org.cmms.modules.dkjkpt.ydqkjk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.ydqkjk.entity.VdkjkptYqdkjk;
import org.cmms.modules.dkjkpt.ydqkjk.mapper.VdkjkptYqdkjkMapper;
import org.cmms.modules.dkjkpt.ydqkjk.service.IVdkjkptYqdkjkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 逾期贷款监控
 * @Author: cmms
 * @Date:   2019-09-12
 * @Version: V1.0
 */
@Service
@DS("dkjkpt")
public class VdkjkptYqdkjkServiceImpl extends ServiceImpl<VdkjkptYqdkjkMapper, VdkjkptYqdkjk> implements IVdkjkptYqdkjkService {

}
