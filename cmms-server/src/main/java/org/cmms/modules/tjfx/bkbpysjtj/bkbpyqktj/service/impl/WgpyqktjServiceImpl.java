package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.service.impl;

import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.entity.Wgpyqktj;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.mapper.WgpyqktjMapper;
import org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj.service.IwgpyqktjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 评议情况统计(网格)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Service
public class WgpyqktjServiceImpl extends ServiceImpl<WgpyqktjMapper, Wgpyqktj> implements IwgpyqktjService {

    @Override
    public void initPyxx(String tjyf) {
        baseMapper.initPyxx(tjyf);

    }
}
