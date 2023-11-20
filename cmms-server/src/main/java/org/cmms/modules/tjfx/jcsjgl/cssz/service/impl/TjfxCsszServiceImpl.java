package org.cmms.modules.tjfx.jcsjgl.cssz.service.impl;

import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.mapper.TjfxCsszMapper;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Service
public class TjfxCsszServiceImpl extends ServiceImpl<TjfxCsszMapper, TjfxCssz> implements ITjfxCsszService {

    @Autowired
    private TjfxCsszMapper mapper;

    @Override
    public String queryCszByCsbm(String csbm) {
        return mapper.queryCszByCsbm(csbm);
    }
}
