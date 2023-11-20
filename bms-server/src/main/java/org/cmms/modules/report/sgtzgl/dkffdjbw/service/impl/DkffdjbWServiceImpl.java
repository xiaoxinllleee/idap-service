package org.cmms.modules.report.sgtzgl.dkffdjbw.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dkffdjbw.entity.DkffdjbW;
import org.cmms.modules.report.sgtzgl.dkffdjbw.mapper.DkffdjbWMapper;
import org.cmms.modules.report.sgtzgl.dkffdjbw.service.IDkffdjbWService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款发放登记簿（周）
 * @Author: jeecg-boot
 * @Date:   2022-11-24
 * @Version: V1.0
 */
@Service
@DS("ads")
public class DkffdjbWServiceImpl extends ServiceImpl<DkffdjbWMapper, DkffdjbW> implements IDkffdjbWService {

}
