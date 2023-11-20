package org.cmms.modules.report.sgtzgl.glfmc.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.glfmc.entity.SgtzglGlfmc;
import org.cmms.modules.report.sgtzgl.glfmc.mapper.SgtzglGlfmcMapper;
import org.cmms.modules.report.sgtzgl.glfmc.service.ISgtzglGlfmcService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 关联方名册
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglGlfmcServiceImpl extends ServiceImpl<SgtzglGlfmcMapper, SgtzglGlfmc> implements ISgtzglGlfmcService {

}
