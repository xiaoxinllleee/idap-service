package org.cmms.modules.report.sgtzgl.ywdjpdcsb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.entity.SgtzYwdjpdcsb;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.mapper.SgtzYwdjpdcsbMapper;
import org.cmms.modules.report.sgtzgl.ywdjpdcsb.service.ISgtzYwdjpdcsbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 业务等级评定参数表
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzYwdjpdcsbServiceImpl extends ServiceImpl<SgtzYwdjpdcsbMapper, SgtzYwdjpdcsb> implements ISgtzYwdjpdcsbService {

}
