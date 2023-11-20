package org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.service.impl;

import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.entity.Tjfx_shzfsjmx_qh;
import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.mapper.Tjfx_shzfsjmx_qhMapper;
import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.service.ITjfx_shzfsjmx_qhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 商户走访数据明细统计_全行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Service
public class Tjfx_shzfsjmx_qhServiceImpl extends ServiceImpl<Tjfx_shzfsjmx_qhMapper, Tjfx_shzfsjmx_qh> implements ITjfx_shzfsjmx_qhService {


    @Autowired
    private Tjfx_shzfsjmx_qhMapper mapper;
    @Override
    public void InitDataToQh(Map<String, String> sql) {
        mapper.InitDataToQh(sql);
    }
}
