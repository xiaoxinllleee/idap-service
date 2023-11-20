package org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.service.impl;

import org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.entity.TjfxKhbkbpyQh;
import org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.mapper.TjfxKhbkbpyQhMapper;
import org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.service.ITjfxKhbkbpyQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户背靠背评议_全行
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Service
public class TjfxKhbkbpyQhServiceImpl extends ServiceImpl<TjfxKhbkbpyQhMapper, TjfxKhbkbpyQh> implements ITjfxKhbkbpyQhService {

    @Autowired
    private TjfxKhbkbpyQhMapper tjfxKhbkbpyQhMapper;
    @Override
    public void extract(String tjyf) {
         tjfxKhbkbpyQhMapper.extract(tjyf);

    }
}
