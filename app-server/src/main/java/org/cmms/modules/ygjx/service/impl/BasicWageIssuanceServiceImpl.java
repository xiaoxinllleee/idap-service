package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.BasicWageIssuance;
import org.cmms.modules.ygjx.mapper.BasicWageIssuanceMapper;
import org.cmms.modules.ygjx.service.IBasicWageIssuanceService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 岗位工资
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasicWageIssuanceServiceImpl extends ServiceImpl<BasicWageIssuanceMapper, BasicWageIssuance> implements IBasicWageIssuanceService {

}
