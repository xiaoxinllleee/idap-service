package org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.service.impl;

import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.entity.Tjfx_shjrphsj_qh;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.mapper.Tjfx_shjrphsj_qhMapper;
import org.cmms.modules.tjfx.tjbb.shjrphsjhz.jrphsj_qh.service.ITjfx_shjrphsj_qhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 全行金融普惠数据
 * @Author: jeecg-boot
 * @Date:   2020-11-05
 * @Version: V1.0
 */
@Service
public class Tjfx_shjrphsj_qhServiceImpl extends ServiceImpl<Tjfx_shjrphsj_qhMapper, Tjfx_shjrphsj_qh> implements ITjfx_shjrphsj_qhService {

    @Autowired
    private Tjfx_shjrphsj_qhMapper mapper;

    @Override
    public void InitDataToQh(Map<String, String> sql) {
        mapper.InitDataToQh(sql);
    }
}
