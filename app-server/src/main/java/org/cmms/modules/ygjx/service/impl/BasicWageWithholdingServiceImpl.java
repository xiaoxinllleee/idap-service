package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.BasicWageWithholding;
import org.cmms.modules.ygjx.mapper.BasicWageWithholdingMapper;
import org.cmms.modules.ygjx.service.IBasicWageWithholdingService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 代扣代缴
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasicWageWithholdingServiceImpl extends ServiceImpl<BasicWageWithholdingMapper, BasicWageWithholding> implements IBasicWageWithholdingService {

}
