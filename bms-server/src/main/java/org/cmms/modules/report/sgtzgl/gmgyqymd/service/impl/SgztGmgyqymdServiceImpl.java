package org.cmms.modules.report.sgtzgl.gmgyqymd.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.gmgyqymd.entity.SgztGmgyqymd;
import org.cmms.modules.report.sgtzgl.gmgyqymd.mapper.SgztGmgyqymdMapper;
import org.cmms.modules.report.sgtzgl.gmgyqymd.service.ISgztGmgyqymdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 规模工业企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgztGmgyqymdServiceImpl extends ServiceImpl<SgztGmgyqymdMapper, SgztGmgyqymd> implements ISgztGmgyqymdService {

}
