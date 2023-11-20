package org.cmms.modules.report.sgtzgl.dkyeb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkyeb.entity.SgtzglDkyeb;
import org.cmms.modules.report.sgtzgl.dkyeb.mapper.SgtzglDkyebMapper;
import org.cmms.modules.report.sgtzgl.dkyeb.service.ISgtzglDkyebService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款余额表
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDkyebServiceImpl extends ServiceImpl<SgtzglDkyebMapper, SgtzglDkyeb> implements ISgtzglDkyebService {

}
