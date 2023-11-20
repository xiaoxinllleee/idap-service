package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.mapper.TjfxZhbemxbMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service.ITjfxZhbemxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行表二明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-14
 * @Version: V1.0
 */
@Service
public class TjfxZhbemxbServiceImpl extends ServiceImpl<TjfxZhbemxbMapper, TjfxZhbemxb> implements ITjfxZhbemxbService {

  @Autowired
    TjfxZhbemxbMapper tjfxZhbemxbMapper;
    @Override
    public List<Map<String,Object>> csqueryckyemx ( Date tjyf,  String zdmc,  String xzc,  String code,  String zkhjl){
        return tjfxZhbemxbMapper.csqueryckyemx(tjyf,zdmc,xzc,code,zkhjl);
    }

    @Override
    public List<TjfxZhbemxb>queryckyemx( Date tjyf,  String zdmc, String xzc,  String code, String zkhjl,String qfbs ){
        return tjfxZhbemxbMapper.queryckyemx(tjyf,zdmc,xzc,code,zkhjl,qfbs);
    }

    @Override
    public List<TjfxZhbemxb>queryclhsmx( Date tjyf,  String zdmc, String xzc, String code, String zkhjl, String code2,String qfbs ){
        return tjfxZhbemxbMapper.queryclhsmx(tjyf,zdmc,xzc,code,zkhjl,code2,qfbs);
    }

    @Override
    public List<TjfxZhbemxb>queryzlhsmx( Date tjyf,  String zdmc, String xzc, String code, String zkhjl, String code2,String qfbs ){
        return tjfxZhbemxbMapper.queryzlhsmx(tjyf,zdmc,xzc,code,zkhjl,code2,qfbs);
    }


    @Override
    public List<TjfxZhbemxb>querycscqckyemx( Date tjyf,  String zdmc, String xzc,String qfbs ){
        return tjfxZhbemxbMapper.querycscqckyemx(tjyf,zdmc,xzc,qfbs);
    }

    @Override
    public List<TjfxZhbemxb>querycscqclhsmx( Date tjyf,  String zdmc, String xzc, String code,String qfbs ){
        return tjfxZhbemxbMapper.querycscqclhsmx(tjyf,zdmc,xzc,code,qfbs);

    }

    @Override
    public List<TjfxZhbemxb>querycscqzlhsmx( Date tjyf,  String zdmc, String xzc, String code,String qfbs ){
        return tjfxZhbemxbMapper.querycscqzlhsmx(tjyf,zdmc,xzc,code,qfbs);

    }




}
