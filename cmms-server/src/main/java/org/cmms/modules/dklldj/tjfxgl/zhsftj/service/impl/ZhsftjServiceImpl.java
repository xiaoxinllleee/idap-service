package org.cmms.modules.dklldj.tjfxgl.zhsftj.service.impl;

import org.cmms.modules.dklldj.tjfxgl.zhsftj.entity.Zhsftj;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.mapper.ZhsftjMapper;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.service.IZhsftjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 支行上浮统计
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Service
public class ZhsftjServiceImpl extends ServiceImpl<ZhsftjMapper, Zhsftj> implements IZhsftjService {

    @Autowired
    private ZhsftjMapper mapper;

    @Override
    public void init(Map<String, String> sql) {
        mapper.init(sql);
    }
}
