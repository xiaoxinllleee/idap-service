package org.cmms.modules.xddagl.dkdagl.dhjczllr.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjczllr;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.mapper.DhjczllrMapper;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.service.IDhjczllrService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷后检查资料录入
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DhjczllrServiceImpl extends ServiceImpl<DhjczllrMapper, Dhjczllr> implements IDhjczllrService {

}
