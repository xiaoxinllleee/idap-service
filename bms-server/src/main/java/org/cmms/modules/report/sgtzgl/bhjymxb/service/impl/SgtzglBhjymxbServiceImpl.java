package org.cmms.modules.report.sgtzgl.bhjymxb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.bhjymxb.entity.SgtzglBhjymxb;
import org.cmms.modules.report.sgtzgl.bhjymxb.mapper.SgtzglBhjymxbMapper;
import org.cmms.modules.report.sgtzgl.bhjymxb.service.ISgtzglBhjymxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 保函结余明细表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglBhjymxbServiceImpl extends ServiceImpl<SgtzglBhjymxbMapper, SgtzglBhjymxb> implements ISgtzglBhjymxbService {

}
