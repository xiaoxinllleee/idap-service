package org.cmms.modules.xddagl.sytx.dhjclrtx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.sytx.dhjclrtx.entity.Dhjclrtx;
import org.cmms.modules.xddagl.sytx.dhjclrtx.mapper.DhjclrtxMapper;
import org.cmms.modules.xddagl.sytx.dhjclrtx.service.IDhjclrtxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷后检查录入提醒
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DhjclrtxServiceImpl extends ServiceImpl<DhjclrtxMapper, Dhjclrtx> implements IDhjclrtxService {

}
