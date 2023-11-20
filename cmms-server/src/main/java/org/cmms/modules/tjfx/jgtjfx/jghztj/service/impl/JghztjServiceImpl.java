package org.cmms.modules.tjfx.jgtjfx.jghztj.service.impl;

import org.cmms.modules.tjfx.jgtjfx.jghztj.entity.Jghztj;
import org.cmms.modules.tjfx.jgtjfx.jghztj.mapper.JghztjMapper;
import org.cmms.modules.tjfx.jgtjfx.jghztj.service.IJghztjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 机构汇总统计
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Service
public class JghztjServiceImpl extends ServiceImpl<JghztjMapper, Jghztj> implements IJghztjService {
    @Autowired
    private JghztjMapper jghztjMapper;

    public Jghztj getLatestInfo(String jgdm) {
        return jghztjMapper.getLatestInfo(jgdm);
    }
}
