package org.cmms.modules.report.sgtzgl.khsxcx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.khsxcx.entity.SgtzglKhsxcx;
import org.cmms.modules.report.sgtzgl.khsxcx.mapper.SgtzglKhsxcxMapper;
import org.cmms.modules.report.sgtzgl.khsxcx.service.ISgtzglKhsxcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户授信查询
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Service
@DS("ads")
public class SgtzglKhsxcxServiceImpl extends ServiceImpl<SgtzglKhsxcxMapper, SgtzglKhsxcx> implements ISgtzglKhsxcxService {

}
