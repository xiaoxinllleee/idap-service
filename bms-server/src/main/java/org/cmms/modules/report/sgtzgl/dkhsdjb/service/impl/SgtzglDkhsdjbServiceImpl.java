package org.cmms.modules.report.sgtzgl.dkhsdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkhsdjb.entity.SgtzglDkhsdjb;
import org.cmms.modules.report.sgtzgl.dkhsdjb.mapper.SgtzglDkhsdjbMapper;
import org.cmms.modules.report.sgtzgl.dkhsdjb.service.ISgtzglDkhsdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款回收登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDkhsdjbServiceImpl extends ServiceImpl<SgtzglDkhsdjbMapper, SgtzglDkhsdjb> implements ISgtzglDkhsdjbService {

}
