package org.cmms.modules.report.sgtzgl.cwbblrbRmb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.entity.SgtzCwbblrbRmb;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.mapper.SgtzCwbblrbRmbMapper;
import org.cmms.modules.report.sgtzgl.cwbblrbRmb.service.ISgtzCwbblrbRmbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务报表利润表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzCwbblrbRmbServiceImpl extends ServiceImpl<SgtzCwbblrbRmbMapper, SgtzCwbblrbRmb> implements ISgtzCwbblrbRmbService {

}
