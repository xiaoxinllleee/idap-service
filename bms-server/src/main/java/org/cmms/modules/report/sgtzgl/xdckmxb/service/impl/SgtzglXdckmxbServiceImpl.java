package org.cmms.modules.report.sgtzgl.xdckmxb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.xdckmxb.entity.SgtzglXdckmxb;
import org.cmms.modules.report.sgtzgl.xdckmxb.mapper.SgtzglXdckmxbMapper;
import org.cmms.modules.report.sgtzgl.xdckmxb.service.ISgtzglXdckmxbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 协定存款明细表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglXdckmxbServiceImpl extends ServiceImpl<SgtzglXdckmxbMapper, SgtzglXdckmxb> implements ISgtzglXdckmxbService {

}
