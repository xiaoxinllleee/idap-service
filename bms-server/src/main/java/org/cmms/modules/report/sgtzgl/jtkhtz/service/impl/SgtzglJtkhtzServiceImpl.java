package org.cmms.modules.report.sgtzgl.jtkhtz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.jtkhtz.entity.SgtzglJtkhtz;
import org.cmms.modules.report.sgtzgl.jtkhtz.mapper.SgtzglJtkhtzMapper;
import org.cmms.modules.report.sgtzgl.jtkhtz.service.ISgtzglJtkhtzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 集团客户台账
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglJtkhtzServiceImpl extends ServiceImpl<SgtzglJtkhtzMapper, SgtzglJtkhtz> implements ISgtzglJtkhtzService {

}
