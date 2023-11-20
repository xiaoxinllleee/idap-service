package org.cmms.modules.report.sgtzgl.dkffdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkffdjb.entity.SgtzglDkffdjb;
import org.cmms.modules.report.sgtzgl.dkffdjb.mapper.SgtzglDkffdjbMapper;
import org.cmms.modules.report.sgtzgl.dkffdjb.service.ISgtzglDkffdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款发放登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDkffdjbServiceImpl extends ServiceImpl<SgtzglDkffdjbMapper, SgtzglDkffdjb> implements ISgtzglDkffdjbService {

}
