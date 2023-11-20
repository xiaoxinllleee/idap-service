package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.ErpWageJbgzgl;
import org.cmms.modules.ygjx.mapper.ErpWageJbgzglMapper;
import org.cmms.modules.ygjx.service.IErpWageJbgzglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 基本工资管理涟源
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class ErpWageJbgzglServiceImpl extends ServiceImpl<ErpWageJbgzglMapper, ErpWageJbgzgl> implements IErpWageJbgzglService {

}
