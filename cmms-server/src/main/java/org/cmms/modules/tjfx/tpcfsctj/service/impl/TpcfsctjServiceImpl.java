package org.cmms.modules.tjfx.tpcfsctj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjfx.tpcfsctj.entity.Tpcfsctj;
import org.cmms.modules.tjfx.tpcfsctj.mapper.TpcfsctjMapper;
import org.cmms.modules.tjfx.tpcfsctj.service.ITpcfsctjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 图片重复上传统计
 * @Author: jeecg-boot
 * @Date:   2021-06-10
 * @Version: V1.0
 */
@Service
public class TpcfsctjServiceImpl extends ServiceImpl<TpcfsctjMapper, Tpcfsctj> implements ITpcfsctjService {
    @Autowired
    private TpcfsctjMapper tpcfsctjMapper;
    @Override
    public void init() {
        tpcfsctjMapper.init();
    }
}
