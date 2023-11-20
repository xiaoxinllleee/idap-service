package org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.service.impl;

import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.mapper.Tjfx_shzfsjmx_qhMapper;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.entity.Tjfx_shsxyxtj_qh;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.mapper.Tjfx_shsxyxtj_qhMapper;
import org.cmms.modules.tjfx.tjbb.shpjtj.sxyxmx_qh.service.ITjfx_shsxyxtj_qhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Service
public class Tjfx_shsxyxtj_qhServiceImpl extends ServiceImpl<Tjfx_shsxyxtj_qhMapper, Tjfx_shsxyxtj_qh> implements ITjfx_shsxyxtj_qhService {

    @Autowired
    private Tjfx_shzfsjmx_qhMapper mapper;

    @Override
    public void InitDataToQh(Map<String, String> sql) {
        mapper.InitDataToQh(sql);
    }
}
