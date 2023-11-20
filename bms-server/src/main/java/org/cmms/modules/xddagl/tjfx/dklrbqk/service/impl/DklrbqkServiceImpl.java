package org.cmms.modules.xddagl.tjfx.dklrbqk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.tjfx.dklrbqk.entity.Dklrbqk;
import org.cmms.modules.xddagl.tjfx.dklrbqk.mapper.DklrbqkMapper;
import org.cmms.modules.xddagl.tjfx.dklrbqk.service.IDklrbqkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款录入情况表
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DklrbqkServiceImpl extends ServiceImpl<DklrbqkMapper, Dklrbqk> implements IDklrbqkService {

}
