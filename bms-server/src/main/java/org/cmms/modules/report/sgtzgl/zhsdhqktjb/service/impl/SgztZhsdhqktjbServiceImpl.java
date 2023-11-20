package org.cmms.modules.report.sgtzgl.zhsdhqktjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.entity.SgztZhsdhqktjb;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.mapper.SgztZhsdhqktjbMapper;
import org.cmms.modules.report.sgtzgl.zhsdhqktjb.service.ISgztZhsdhqktjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支行首贷户情况统计表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgztZhsdhqktjbServiceImpl extends ServiceImpl<SgztZhsdhqktjbMapper, SgztZhsdhqktjb> implements ISgztZhsdhqktjbService {

}
