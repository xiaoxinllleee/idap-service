package org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.entity.Sjzdglpz;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.mapper.SjzdglpzMapper;
import org.cmms.modules.xddagl.xtgl.xddaglsjzdgl.service.ISjzdglpzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 数据字段管理配置
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class SjzdglpzServiceImpl extends ServiceImpl<SjzdglpzMapper, Sjzdglpz> implements ISjzdglpzService {

}
