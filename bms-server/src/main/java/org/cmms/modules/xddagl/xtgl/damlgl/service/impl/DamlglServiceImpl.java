package org.cmms.modules.xddagl.xtgl.damlgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xtgl.damlgl.entity.Damlgl;
import org.cmms.modules.xddagl.xtgl.damlgl.mapper.DamlglMapper;
import org.cmms.modules.xddagl.xtgl.damlgl.service.IDamlglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 档案目录管理(参数配置)
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DamlglServiceImpl extends ServiceImpl<DamlglMapper, Damlgl> implements IDamlglService {


}
