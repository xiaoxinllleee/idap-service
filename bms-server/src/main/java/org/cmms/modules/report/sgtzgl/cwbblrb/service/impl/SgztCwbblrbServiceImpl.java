package org.cmms.modules.report.sgtzgl.cwbblrb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbblrb.entity.SgztCwbblrb;
import org.cmms.modules.report.sgtzgl.cwbblrb.mapper.SgztCwbblrbMapper;
import org.cmms.modules.report.sgtzgl.cwbblrb.service.ISgztCwbblrbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务报表利润表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgztCwbblrbServiceImpl extends ServiceImpl<SgztCwbblrbMapper, SgztCwbblrb> implements ISgztCwbblrbService {

}
