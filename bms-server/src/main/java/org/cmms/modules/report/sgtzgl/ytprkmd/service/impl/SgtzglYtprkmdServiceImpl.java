package org.cmms.modules.report.sgtzgl.ytprkmd.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.ytprkmd.entity.SgtzglYtprkmd;
import org.cmms.modules.report.sgtzgl.ytprkmd.mapper.SgtzglYtprkmdMapper;
import org.cmms.modules.report.sgtzgl.ytprkmd.service.ISgtzglYtprkmdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 已脱贫人口名单
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglYtprkmdServiceImpl extends ServiceImpl<SgtzglYtprkmdMapper, SgtzglYtprkmd> implements ISgtzglYtprkmdService {

}
