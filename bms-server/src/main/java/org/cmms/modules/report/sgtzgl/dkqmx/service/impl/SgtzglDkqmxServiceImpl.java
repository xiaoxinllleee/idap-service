package org.cmms.modules.report.sgtzgl.dkqmx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkqmx.entity.SgtzglDkqmx;
import org.cmms.modules.report.sgtzgl.dkqmx.mapper.SgtzglDkqmxMapper;
import org.cmms.modules.report.sgtzgl.dkqmx.service.ISgtzglDkqmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款全明细
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDkqmxServiceImpl extends ServiceImpl<SgtzglDkqmxMapper, SgtzglDkqmx> implements ISgtzglDkqmxService {

}
