package org.cmms.modules.report.sgtzgl.dkhxdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkhxdjb.entity.SgtzglDkhxdjb;
import org.cmms.modules.report.sgtzgl.dkhxdjb.mapper.SgtzglDkhxdjbMapper;
import org.cmms.modules.report.sgtzgl.dkhxdjb.service.ISgtzglDkhxdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款核销登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDkhxdjbServiceImpl extends ServiceImpl<SgtzglDkhxdjbMapper, SgtzglDkhxdjb> implements ISgtzglDkhxdjbService {

}
