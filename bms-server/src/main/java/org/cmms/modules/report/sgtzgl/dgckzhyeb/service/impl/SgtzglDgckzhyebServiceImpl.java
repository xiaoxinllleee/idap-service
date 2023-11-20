package org.cmms.modules.report.sgtzgl.dgckzhyeb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.entity.SgtzglDgckzhyeb;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.mapper.SgtzglDgckzhyebMapper;
import org.cmms.modules.report.sgtzgl.dgckzhyeb.service.ISgtzglDgckzhyebService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 对公存款账户余额表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglDgckzhyebServiceImpl extends ServiceImpl<SgtzglDgckzhyebMapper, SgtzglDgckzhyeb> implements ISgtzglDgckzhyebService {

}
