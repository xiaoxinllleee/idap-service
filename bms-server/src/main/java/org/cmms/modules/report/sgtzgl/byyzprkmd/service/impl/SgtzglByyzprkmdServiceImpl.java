package org.cmms.modules.report.sgtzgl.byyzprkmd.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.byyzprkmd.entity.SgtzglByyzprkmd;
import org.cmms.modules.report.sgtzgl.byyzprkmd.mapper.SgtzglByyzprkmdMapper;
import org.cmms.modules.report.sgtzgl.byyzprkmd.service.ISgtzglByyzprkmdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 边缘易致贫人口名单
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglByyzprkmdServiceImpl extends ServiceImpl<SgtzglByyzprkmdMapper, SgtzglByyzprkmd> implements ISgtzglByyzprkmdService {

}
