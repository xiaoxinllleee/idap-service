package org.cmms.modules.report.sgtzgl.cwbbsybb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbbsybb.entity.SgtzCwbbsybb;
import org.cmms.modules.report.sgtzgl.cwbbsybb.mapper.SgtzCwbbsybbMapper;
import org.cmms.modules.report.sgtzgl.cwbbsybb.service.ISgtzCwbbsybbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务报表损益报表
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzCwbbsybbServiceImpl extends ServiceImpl<SgtzCwbbsybbMapper, SgtzCwbbsybb> implements ISgtzCwbbsybbService {

}
