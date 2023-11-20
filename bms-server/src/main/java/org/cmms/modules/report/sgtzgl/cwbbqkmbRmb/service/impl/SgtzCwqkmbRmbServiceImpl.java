package org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.entity.SgtzCwqkmbRmb;
import org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.mapper.SgtzCwqkmbRmbMapper;
import org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.service.ISgtzCwqkmbRmbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务报表全科目表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzCwqkmbRmbServiceImpl extends ServiceImpl<SgtzCwqkmbRmbMapper, SgtzCwqkmbRmb> implements ISgtzCwqkmbRmbService {

}
