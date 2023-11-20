package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.BasicWageOther;
import org.cmms.modules.ygjx.mapper.BasicWageOtherMapper;
import org.cmms.modules.ygjx.service.IBasicWageOtherService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 其他工资
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasicWageOtherServiceImpl extends ServiceImpl<BasicWageOtherMapper, BasicWageOther> implements IBasicWageOtherService {

}
