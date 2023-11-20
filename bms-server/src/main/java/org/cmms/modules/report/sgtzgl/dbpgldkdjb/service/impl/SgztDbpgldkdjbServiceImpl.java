package org.cmms.modules.report.sgtzgl.dbpgldkdjb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity.SgztDbpgldkdjb;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.mapper.SgztDbpgldkdjbMapper;
import org.cmms.modules.report.sgtzgl.dbpgldkdjb.service.ISgztDbpgldkdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 担保品关联贷款登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgztDbpgldkdjbServiceImpl extends ServiceImpl<SgztDbpgldkdjbMapper, SgztDbpgldkdjb> implements ISgztDbpgldkdjbService {

}
