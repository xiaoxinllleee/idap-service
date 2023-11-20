package org.cmms.modules.report.sgtzgl.txdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.txdjb.entity.SgtzglTxdjb;
import org.cmms.modules.report.sgtzgl.txdjb.mapper.SgtzglTxdjbMapper;
import org.cmms.modules.report.sgtzgl.txdjb.service.ISgtzglTxdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贴现登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglTxdjbServiceImpl extends ServiceImpl<SgtzglTxdjbMapper, SgtzglTxdjb> implements ISgtzglTxdjbService {

}
