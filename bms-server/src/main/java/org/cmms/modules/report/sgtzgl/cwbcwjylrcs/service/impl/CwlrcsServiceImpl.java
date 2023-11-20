package org.cmms.modules.report.sgtzgl.cwbcwjylrcs.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.entity.Cwlrcs;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.mapper.CwlrcsMapper;
import org.cmms.modules.report.sgtzgl.cwbcwjylrcs.service.ICwlrcsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 财务部_手工台账_经营利润测算
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Service
@DS("ads")
public class CwlrcsServiceImpl extends ServiceImpl<CwlrcsMapper, Cwlrcs> implements ICwlrcsService {

}
