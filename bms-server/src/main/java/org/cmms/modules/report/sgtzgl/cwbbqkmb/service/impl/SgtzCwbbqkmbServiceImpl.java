package org.cmms.modules.report.sgtzgl.cwbbqkmb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.entity.SgtzCwbbqkmb;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.mapper.SgtzCwbbqkmbMapper;
import org.cmms.modules.report.sgtzgl.cwbbqkmb.service.ISgtzCwbbqkmbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务报表全科目表
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzCwbbqkmbServiceImpl extends ServiceImpl<SgtzCwbbqkmbMapper, SgtzCwbbqkmb> implements ISgtzCwbbqkmbService {

}
