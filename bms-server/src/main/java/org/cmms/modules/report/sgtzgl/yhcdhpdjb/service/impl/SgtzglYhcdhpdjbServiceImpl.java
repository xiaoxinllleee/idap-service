package org.cmms.modules.report.sgtzgl.yhcdhpdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.entity.SgtzglYhcdhpdjb;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.mapper.SgtzglYhcdhpdjbMapper;
import org.cmms.modules.report.sgtzgl.yhcdhpdjb.service.ISgtzglYhcdhpdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 银行承兑汇票登记薄
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglYhcdhpdjbServiceImpl extends ServiceImpl<SgtzglYhcdhpdjbMapper, SgtzglYhcdhpdjb> implements ISgtzglYhcdhpdjbService {

}
