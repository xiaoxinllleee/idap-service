package org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.entity.Xddaglcsgl;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.mapper.XddaglcsglMapper;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class XddaglcsglServiceImpl extends ServiceImpl<XddaglcsglMapper, Xddaglcsgl> implements IXddaglcsglService {

}
