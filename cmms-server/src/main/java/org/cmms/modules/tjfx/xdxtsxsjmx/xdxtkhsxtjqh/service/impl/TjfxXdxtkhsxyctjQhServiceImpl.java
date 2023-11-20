package org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.service.impl;

import org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.entity.TjfxXdxtkhsxyctjQh;
import org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.mapper.TjfxXdxtkhsxyctjQhMapper;
import org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.service.ITjfxXdxtkhsxyctjQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
@Service
public class TjfxXdxtkhsxyctjQhServiceImpl extends ServiceImpl<TjfxXdxtkhsxyctjQhMapper, TjfxXdxtkhsxyctjQh> implements ITjfxXdxtkhsxyctjQhService {

@Autowired
    TjfxXdxtkhsxyctjQhMapper tjfxXdxtkhsxyctjQhMapper;
@Override
    public  void extract(String tjyf){
        tjfxXdxtkhsxyctjQhMapper.extract(tjyf);
    }

}
